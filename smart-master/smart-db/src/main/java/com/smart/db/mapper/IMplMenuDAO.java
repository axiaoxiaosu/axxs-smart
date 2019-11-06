package com.smart.db.mapper;

import com.smart.db.beanenum.MplMenuEnum;
import com.smart.db.model.MplMenu;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IMplMenuDAO {

    List<MplMenu> selectMplMenuByUserName(@Param("username") String username);


    List<MplMenu> selectMplMenu(@Param("username") String username, @Param("menuName") String menuName,@Param("types")List<String> types);

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
    Integer deleteMenu(@Param("ids") Long[] ids);

    /**
     * 查询该资源是否重复添加
     */
    List<MplMenu> selectByNameUrlPar(@Param("menuName") String menuName, @Param("url") String url,
                                     @Param("parendId") Long parendId);

    /**
     * 根据id查询资源
     */
    Integer seleteByIds(@Param("ids") Long[] ids);

    /**
     * 用戶名+url获取按钮
     * @param username
     * @param menuId
     * @return
     */
    List<MplMenu> selectByUserNameAndUrl(@Param("username") String username, @Param("menuId")  String menuId,
                                         @Param("type") int type);
}