package com.smart.db.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * mpl_menu
 *
 * @author
 */
@Data
public class MplMenu implements Serializable {
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
    private Integer type;
    /**
     * 是否显示
     */
    private String isShow;
    /**
     * 父菜单id
     */
    private Long parentId;
    private String parentUrl;
    private String parentName;
    /**
     * 图标
     */
    private String icon;
    /**
     * 显示顺序
     */
    private String sort;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 备注
     */
    private String remark;
    private List<MplMenu> children;
    private Integer level;

    /**
     * 标题
     */
//    private String title;

    /**
     * 按鈕類型 1.独立操作  2,每行生成一个需要绑定id 3批量操作
     */
    private String btnType;
}