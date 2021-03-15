package com.smart.api.service.impl;

import com.common.annotationoo.SmartDic;
import com.common.exception.SmartException;
import com.common.util.SmartUtil;
import com.github.pagehelper.PageHelper;
import com.smart.api.model.MyUserDetail;
import com.smart.api.request.AddMenuRequest;
import com.smart.api.request.GetMenusRequest;
import com.smart.api.service.MenuService;
import com.smart.db.beanenum.MplMenuEnum;
import com.smart.db.dao.MplMenuDAO;
import com.smart.db.dao.MplRoleMenuDAO;
import com.smart.db.model.MplMenu;
import com.smart.db.model.MplRoleMenu;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lwq
 * @data 2019/8/16
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Resource
    public MplMenuDAO mplMenuDAO;

    @Resource
    public MplRoleMenuDAO mplRoleMenuDAO;

//    @Autowired
//    public DisComponent disComponent;


   /* @Override
    public List<MplMenu> getMenus(String username) {
        List<MplMenu> menuList = mplMenuDAO.selectMplMenuByUserName(username);
        if (menuList != null && !menuList.isEmpty()) {
            return TreeUtil.toTreeUpdateParma(menuList, 0L);
            List<MplMenu> list = TreeUtil.toTreeUpdateParma(menuList, 0L);
            return list.stream().map(e -> {
                if (e.getChildren() != null && e.getChildren().size() == 1 && !e.getChildren().get(0).getUrl().equalsIgnoreCase(e.getUrl())) {
                    String jsonStr = JSON.toJSONString(e);
                    MplMenu menu = JSON.parseObject(jsonStr, MplMenu.class);
                    menu.getChildMenus().clear();
                    menu.setParentId(-1L);
                    e.getChildMenus().add(menu);
                }
                return e;
            }).collect(Collectors.toList());
        }
        return null;
    }*/

    @SmartDic
    @Override
    public List<MplMenu> getMenus(String username, GetMenusRequest getMenusRequest) {
        String menuName = null;
        List<String> types = null;
        boolean isShow = false;
        if (getMenusRequest != null) {
            PageHelper.startPage(getMenusRequest.getPageNum(), getMenusRequest.getPageSize());
            menuName = getMenusRequest.getTitle();
            if (getMenusRequest.getTypes() != null && !getMenusRequest.getTypes().isEmpty()) {
                types = getMenusRequest.getTypes();
            }
            isShow = getMenusRequest.getIsShow();
        }
        List<MplMenu> newList = null;
        //手动排序下
        List<MplMenu> menuList = mplMenuDAO.selectMplMenu(username, isShow, menuName, types);
        if (menuList != null && !menuList.isEmpty()) {
            //按照目录和菜单手动排序下
            newList = menuList.stream().filter(e -> e.getType() == MplMenuEnum.MENUTYPE_1.getValue())
                    .sorted(
                            Comparator.comparing(MplMenu::getSort).reversed()
                    ).collect(Collectors.toList());

            newList.addAll(
                    menuList.stream().filter(e -> e.getType() == MplMenuEnum.MENUTYPE_2.getValue())
                            .sorted(
                                    Comparator.comparing(MplMenu::getSort).reversed()
                            ).collect(Collectors.toList()));

            newList.addAll(
                    menuList.stream().filter(e -> e.getType() == MplMenuEnum.MENUTYPE_3.getValue())
                            .sorted(
                                    Comparator.comparing(MplMenu::getSort).reversed()
                            ).collect(Collectors.toList()));
            //重新赋值 因为需要携带page参数
            menuList.clear();
            menuList.addAll(newList);
        }
        return menuList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addMenu(MyUserDetail myUserDetail, AddMenuRequest addMenuRequest) {
        MplMenu mplMenu = new MplMenu();
        List<MplMenu> menuList = mplMenuDAO.selectByNameUrlPar(addMenuRequest.getMenuName(), addMenuRequest.getUrl(),
                addMenuRequest.getParentId());
        if (menuList != null && !menuList.isEmpty()) {
            new SmartException(3333, "该资源已经添加过了");
        }
        BeanUtils.copyProperties(addMenuRequest, mplMenu);
        mplMenu.setParentId((addMenuRequest.getParentId() == null ? 0 : addMenuRequest.getParentId()));
        mplMenu.setCreateUser(myUserDetail.getUsername());
        mplMenu.setCreateTime(new Date());
        if (StringUtils.isBlank(addMenuRequest.getUrl())) {
            mplMenu.setUrl(mplMenu.getMenuName());
        }
        mplMenuDAO.insertSelective(mplMenu);
        MplRoleMenu mplRoleMenu = new MplRoleMenu();
        mplRoleMenu.setMenuId(mplMenu.getId());
        mplRoleMenu.setRoleId(myUserDetail.getMplRole().getId());
        return SmartUtil.isSucceed(mplRoleMenuDAO.insert(mplRoleMenu));
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteMenu(List<Long> ids) {
        //查询是否有菜单依赖于该项
        Integer count = mplMenuDAO.seleteByIds(ids);
        if (count != null && count > 0) {
            throw new SmartException(777, "资源有依赖项无法删除");
        }
        mplRoleMenuDAO.deleteByMenuId(ids);
        return SmartUtil.isSucceed(mplMenuDAO.deleteMenu(ids));
    }

    @Override
    public boolean updateMenu(AddMenuRequest addMenuRequest) {
        MplMenu mplMenu = new MplMenu();
        //将字典数据还原
        // disComponent.dicTextToCode(addMenuRequest, mplMenu.getTableName());
        BeanUtils.copyProperties(addMenuRequest, mplMenu);
        return SmartUtil.isSucceed(mplMenuDAO.updateByPrimaryKeySelective(mplMenu));
    }

    @Override
    public List<MplMenu> getBtu(MyUserDetail myUserDetail, String menuId) {
        return mplMenuDAO.selectByUserNameAndUrl(myUserDetail.getUsername(), menuId, MplMenuEnum.MENUTYPE_3.getValue());
    }
}
