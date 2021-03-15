package com.smart.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MenuData implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 菜单url
     */
    private String url;
    /**
     * 菜单名称
     */
    private String menuName;
    private String label;
    /**
     * 类型(1模块  2菜单)
     */
    private String type;
    /**
     * 是否显示
     */
    private String isShow;
    /**
     * 父菜单id
     */
    private Long parentId;
    /**
     * 图标
     */
    private String icon;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 子资源
     */
    private List<MenuData> children;

    /**
     * 标题
     */
    private String title;

    /**
     * 按鈕類型 1.独立操作  2,每行生成一个需要绑定id 3批量操作
     */
    private String btnType;


}
