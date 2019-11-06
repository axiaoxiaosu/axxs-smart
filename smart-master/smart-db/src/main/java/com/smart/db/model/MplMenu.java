package com.smart.db.model;

import com.common.annotationoo.DicMark;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * mpl_menu
 *
 * @author
 */
@Data
public class MplMenu implements Serializable {

    /**
     * 用来关联数据字典的
     */
    private final String tableName = "mpl_menu";
    private static final long serialVersionUID = 1L;
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
    @DicMark()
    private String type;
    /**
     * 是否显示
     */
    @DicMark
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
    private String ramark;
    private List<MplMenu> children;
    private Integer level ;

    /**
     * 标题
     */
    private String title ;

    /**
     * 按鈕類型 1.独立操作  2,每行生成一个需要绑定id 3批量操作
     */
    @DicMark
    private String btnType;


    public String getTableName() {
        return tableName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


    public List<MplMenu> getChildren() {
        return children;
    }

    public void setChildren(List<MplMenu> children) {
        this.children = children;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getRamark() {
        return ramark;
    }

    public void setRamark(String ramark) {
        this.ramark = ramark;
    }
}