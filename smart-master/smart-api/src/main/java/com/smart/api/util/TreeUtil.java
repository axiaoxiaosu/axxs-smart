package com.smart.api.util;

import com.smart.db.model.MenuData;
import com.smart.db.model.MplMenu;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lwq
 * @data 2019/8/16
 */
public class TreeUtil {

    /**
     * 转换
     *
     * @param menus
     * @param level
     * @param parent_id
     * @return
     */
    public static List<MplMenu> toTreeUpdateParam(List<MplMenu> menus, Integer level, Long parent_id) {
        if (menus == null || menus.isEmpty()) {
            return null;
        }
        List<MplMenu> mplMenus = menus.stream().map(e -> {
            e.setLabel(e.getMenuName());
            return e;
        }).filter(e -> e.getParentId().equals(parent_id))
                .collect(Collectors.toList());

        Integer le = level + 1;
        for (MplMenu m : mplMenus) {
            m.setChildren(toTreeUpdateParam(menus, le, m.getId()));
            m.setLevel(level);
        }
        return mplMenus;
    }


    public static List<MenuData> toTreeUpdateParam2(List<MenuData> list, Integer level, Long parent_id) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<MenuData> mplMenus = list.stream().map(e -> {
            e.setLabel(e.getMenuName());
            return e;
        }).filter(e -> e.getParentId().equals(parent_id))
                .collect(Collectors.toList());

        Integer le = level + 1;
        for (MenuData m : mplMenus) {
            m.setChildren(toTreeUpdateParam2(list, le, m.getId()));
            m.setLevel(level);
        }
        return mplMenus;
    }


}
