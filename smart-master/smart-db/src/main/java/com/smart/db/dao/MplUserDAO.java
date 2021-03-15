package com.smart.db.dao;

import com.smart.db.model.MplUser;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

/**
 * MplUserDAO继承基类
 */
@Resource
public interface MplUserDAO {

    int deleteByPrimaryKey(Integer id);

    int insert(MplUser record);

    int insertSelective(MplUser record);

    MplUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MplUser record);

    int updateByPrimaryKey(MplUser record);


    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名唯一
     */
    MplUser selectByUserName(@Param("username") String username);

    Integer findUserByName(@Param("username") String username);

    /**
     * 用户名+密码查询用户信息
     */
    Integer findUserByNamePwd(@Param("username") String username, @Param("password") String password);

    Integer updatePassword(MplUser mplUser);

    Integer resetPassword(@Param("userId") String userId, @Param("password") String password);

    Integer selectByRoId(@Param("roleId") List<Long> roleId);

    /**
     * 获取用户信息
     *
     * @param username 用户姓名
     */
    List<MplUser> getUsersPage(@Param("username") String username);

    /**
     * 获取用户信息
     *
     * @param userIds 用户id
     */
    Integer selectByUserId(@Param("userIds") List<Long> userIds);

    /**
     * 批量修改用户状态
     */
    Integer updateUserType(@Param("userIds") List<Long> userIds, @Param("statusCode") Integer statusCode);

}
