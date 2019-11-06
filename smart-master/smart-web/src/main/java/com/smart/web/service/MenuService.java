package com.smart.web.service;

import com.smart.db.model.MplMenu;
import com.smart.web.model.MyUserDetail;
import com.smart.web.request.AddMenuRequest;
import com.smart.web.request.GetMenusRequest;
import java.util.List;

/**
 * @author lwq
 * @data 2019/8/16
 */
public interface MenuService {

    /**
     * 用来分配资源 故不需要分页
     * @param username
     * @return
     */
    // List<MplMenu> getMenus(String username);

    /**
     * 分页查询资源
     */
    List<MplMenu> getMenus(String username, GetMenusRequest getRolesRequest);

    boolean addMenu(MyUserDetail myUserDetail, AddMenuRequest addMenuRequest);

    boolean deleteMenu(Long[] menusIds);

    boolean updateMenu(AddMenuRequest addMenuRequest);

    List<MplMenu> getBtu(MyUserDetail myUserDetail, String url);
}
