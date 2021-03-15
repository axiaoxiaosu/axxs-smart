package com.smart.db;

import com.smart.db.model.MenuData;
import com.smart.db.model.MplMenu;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertBean {
    public static List<MplMenu> toTreeUpdateParma(List<MplMenu> list, Integer level, Long parent_id) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<MplMenu> mplMenus = list.stream().map(e -> {
            e.setLabel(e.getMenuName());
            return e;
        }).filter(e -> e.getParentId().equals(parent_id))
                .collect(Collectors.toList());

        Integer le = level + 1;
        for (MplMenu m : mplMenus) {
            m.setChildren(toTreeUpdateParma(list, le, m.getId()));
            m.setLevel(level);
        }
        return mplMenus;
    }


    public static List<MenuData> toTreeUpdateParma2(List<MenuData> list, Integer level, Long parent_id) {
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
            m.setChildren(toTreeUpdateParma2(list, le, m.getId()));
            m.setLevel(level);
        }
        return mplMenus;
    }
}
