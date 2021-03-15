package com.smart.api.service;

import com.smart.api.model.MyUserDetail;
import com.smart.api.request.AddUserRequest;
import com.smart.api.request.GetUserRequest;
import com.smart.api.request.RegisterRequest;
import com.smart.api.request.UpdatePasswordRequest;
import com.smart.db.model.MplUser;

import java.util.List;

public interface UserService {

    /**
     * 登录
     *
     * @param vo 用户信息
     * @return token
     */
    /*LoginReponse login(LoginRequest vo);*/

    /**
     * 查询用户
     *
     * @param username 用户名
     * @param password 密码
     */
    MplUser findUser(String username, String password);

    /**
     * 修改用户登录时间
     *
     * @param userId 用户id
     */
    void updateTime(Long userId);

    /**
     * 用户注册
     */
    boolean register(RegisterRequest user);

    /**
     * 修改密码
     *
     * @param vo 用户信息
     */
    boolean UpdatePassword(MyUserDetail myUserDetail, UpdatePasswordRequest vo);

    /**
     * 重置密码
     */
    String resetPassword(String userId);

    /**
     * 分页查询用户信息
     */
    List<MplUser> getUsersPage(MyUserDetail myUserDetail, GetUserRequest getUserRequest);

    /**
     * 添加用户
     */
    boolean addUser(MyUserDetail myUserDetail, AddUserRequest addUserRequest);

    boolean deleteUser(MyUserDetail myUserDetail, List<Long> userIds);

    /**
     * 修改用户
     */
    boolean updateUser(MyUserDetail myUserDetail, AddUserRequest addUserRequest);
}
