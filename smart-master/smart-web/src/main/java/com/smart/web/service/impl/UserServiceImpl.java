package com.smart.web.service.impl;

import com.common.constants.Constants;
import com.common.enums.UserEnum;
import com.common.exception.SmartException;
import com.common.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.smart.db.dao.MplUserDAO;
import com.smart.db.model.MplUser;
import com.smart.web.model.MyUserDetail;
import com.smart.web.reponse.LoginReponse;
import com.smart.web.request.AddUserRequest;
import com.smart.web.request.GetUserRequest;
import com.smart.web.request.LoginRequest;
import com.smart.web.request.RegisterRequest;
import com.smart.web.request.UpdatePasswordRequest;
import com.smart.web.service.UserService;
import com.smart.web.util.JwtTokenUtil;
import com.smart.web.util.SmartUtil;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lwq
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MplUserDAO userDAO;
    @Value("${smart.password}")
    private String password;

    /**
     * 登录
     *
     * @param vo 用户信息
     * @return token
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginReponse login(LoginRequest vo) {
        //创建登陆token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                vo.getUsername(), vo.getPassword());
        //鉴定用户信息
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //生成登陆信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //根据当前用户生成token
        String token = JwtTokenUtil.generateToken(vo.getUsername());
        //获取用户信息
        MyUserDetail userDetail = (MyUserDetail) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        //返回用户信息+用户资源+用户token
        return new LoginReponse().setToken(token).setMenu(userDetail.getMplMenu())
                .setUser(userDetail.getUser().setPassword("").setPassword1("").setPassword2(""));
    }

    /**
     * 查询用户
     *
     * @param username 用户名
     * @param password 密码
     */
    @Override
    public MplUser findUser(String username, String password) {
        // return userDao.findUser(username, password);
        return null;
    }

    /**
     * 修改用户登录时间
     *
     * @param userId 用户id
     */
    @Override
    public void updateTime(Long userId) {

    }

    /**
     * 用户注册
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean register(RegisterRequest user) {
        int count = userDAO.findUserByName(user.getUsername());
        if (count > 0) {
            throw new SmartException(Constants.FAILCODE, "用户名已存在");
        }
        MplUser mplUser = new MplUser();
        mplUser.setUsername(user.getUsername());
        mplUser.setPassword(DigestUtils.md5Hex(user.getPassword()));
        mplUser.setStatus(UserEnum.STATU_0.getCode());
        return SmartUtil.isSucceed(userDAO.insert(mplUser));
    }

    /**
     * 修改密码
     *
     * @param vo 用户信息
     * @return 1 用户不存在 2 该密码存在于最近的三次修改 3 4
     */
    @Override
    public boolean UpdatePassword(MyUserDetail userDetail,UpdatePasswordRequest vo) {
        int count = userDAO
                .findUserByNamePwd(vo.getUsername(), DigestUtils.md5Hex(vo.getPassword()));
        if (count == 0) {
            throw new SmartException(Constants.FAILCODE, "该用户不存在");
        }
        MplUser mplUser = new MplUser();
        mplUser.setUsername(vo.getUsername());
        mplUser.setPassword(DigestUtils.md5Hex(vo.getNewpassword()));
        mplUser.setPassword1(DigestUtils.md5Hex(vo.getPassword()));
        mplUser.setUpdateUser(userDetail.getUsername());
        mplUser.setUpdateTime(new Date());
        //修改密码
        return SmartUtil.isSucceed(userDAO.updateByPrimaryKeySelective(mplUser));
    }

    /**
     * 重置密码
     *
     * @param userId 需要就给的id
     */
    @Override
    public String resetPassword(String userId) {
        MplUser mplUser = userDAO.selectByPrimaryKey(Long.parseLong(userId));
        if (mplUser == null) {
            throw new SmartException(Constants.FAILCODE, "未找到该用户");
        }
        //简单的生成一个密码
        String password = RandomUtil.generatePassword();
        String md5Password = DigestUtils.md5Hex(password);
        int conut = userDAO.resetPassword(userId, md5Password);
        return conut > 0 ? password : null;
    }

    /**
     * 获取用户信息
     *
     * @param myUserDetail 用户
     * @param getUserRequest 请求参数
     */
    @Override
    public List<MplUser> getUsersPage(MyUserDetail myUserDetail, GetUserRequest getUserRequest) {
        PageHelper.startPage(getUserRequest.getPageNum(), getUserRequest.getPageSize());
        return userDAO.getUsersPage(getUserRequest.getUsername());
    }

    /**
     * 添加用户
     *
     * @param myUserDetail 用户
     * @param addUserRequest 请求参数
     */
    @Override
    public boolean addUser(MyUserDetail myUserDetail, AddUserRequest addUserRequest) {
        //查询改用户名是否被占用
        Integer mark = userDAO.findUserByName(addUserRequest.getUsername());
        if (mark != null && mark > 0) {
            throw new SmartException(777, "此用户名已经被使用了,换一个吧");
        }
        MplUser user = new MplUser();
        BeanUtils.copyProperties(addUserRequest, user);
        //简单的密码处理
        user.setPassword(DigestUtils.md5Hex(addUserRequest.getPassword()));
        user.setCreateTime(new Date());
        user.setCreateUser(myUserDetail.getUsername());
        return SmartUtil.isSucceed(userDAO.insertSelective(user));
    }

    @Override
    public boolean deleteUser(MyUserDetail myUserDetail, Long[] userIds) {
        //查询是否有菜单依赖于该项
        Integer count = userDAO.selectByUserId(userIds);
        if (count == null || count == 0) {
            throw new SmartException(777, "未找到用户信息");
        }
        if (count != userIds.length) {
            throw new SmartException(777, "有未知用户存在请重新选择");
        }
        return SmartUtil.isSucceed(userDAO.updateUserType(userIds,
                com.smart.db.beanenum.MplUser.STATUS_3.getCode()));
    }

    @Override
    public boolean updateUser(MyUserDetail myUserDetail, AddUserRequest addUserRequest) {
        MplUser mplUser = new MplUser();
        BeanUtils.copyProperties(addUserRequest, mplUser);
        mplUser.setUpdateTime(new Date());
        mplUser.setUpdateUser(myUserDetail.getUsername());
        return SmartUtil.isSucceed(userDAO.updateByPrimaryKeySelective(mplUser));
    }


}
