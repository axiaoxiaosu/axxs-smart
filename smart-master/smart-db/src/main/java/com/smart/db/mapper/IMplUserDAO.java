package com.smart.db.mapper;

import com.smart.db.model.MplUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * MplUserDAO继承基类
 */
public interface IMplUserDAO {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名唯一
     */
    MplUser selectByUserName(@Param("username") String username);

    Integer findUserByName(@Param("username") String username);

    /**
     * 用户名+密码查询用户信息
     * @param username
     * @param password
     * @return
     */
    Integer findUserByNamePwd(@Param("username") String username, @Param("password") String password);

    Integer updatePassword(MplUser mplUser);

    Integer resetPassword(@Param("userId") String userId, @Param("password") String password);

    Integer selectByRoId(@Param("roleId") Long[] roleId);
    /**
     * 获取用户信息
     * @param username 用户姓名
     * @return
     */
    List<MplUser> getUsersPage(@Param("username") String username);

    /**
     * 获取用户信息
     * @param userIds 用户id
     * @return
     */
    Integer selectByUserId(@Param("userIds") Long[] userIds);

    /**
     * 批量修改用户状态
     * @param userIds
     * @param statusCode
     * @return
     */
    Integer updateUserType(@Param("userIds") Long[] userIds,@Param("statusCode") Integer statusCode);
}
