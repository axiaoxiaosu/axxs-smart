package com.smart.db.dao;

import com.smart.db.model.MenuData;
import com.smart.db.model.MplMenu;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;
@Resource
public interface MplMenuDAO {

    int deleteByPrimaryKey(Long id);

    int insert(MplMenu record);

    int insertSelective(MplMenu record);

    MplMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MplMenu record);

    int updateByPrimaryKey(MplMenu record);

    List<MenuData> selectMplMenuByUserName(@Param("username") String username);


    List<MplMenu> selectMplMenu(@Param("username") String username, @Param("isShow") boolean isShow, @Param("menuName") String menuName,
                                @Param("types") List<String> types);

    /**
     * 查询角色所拥有的资源
     */
    List<MplMenu> mensByRole(@Param("roleId") Long id);


    /**
     * 绑定菜单到角色
     */
    Integer roleBindMent(@Param("ids") Long[] ids, @Param("roid") String roId);

    /**
     * 删除角色下的相关菜单
     */
    Integer deleteByRoId(@Param("roId") Long[] roId);

    /**
     * 删除资源
     */
    Integer deleteMenu(@Param("ids") List<Long> ids);

    /**
     * 查询该资源是否重复添加
     */
    List<MplMenu> selectByNameUrlPar(@Param("menuName") String menuName, @Param("url") String url,
                                     @Param("parendId") Long parendId);

    /**
     * 根据id查询资源
     */
    Integer seleteByIds(@Param("ids") List<Long> ids);

    /**
     * 用戶名+url获取按钮
     */
    List<MplMenu> selectByUserNameAndUrl(@Param("username") String username, @Param("menuId") String menuId,
                                         @Param("type") int type);

}