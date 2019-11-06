package com.smart.web.util;

import com.smart.db.model.MplMenu;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lwq
 * @data 2019/8/16
 */
public class TreeUtil {

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

}
