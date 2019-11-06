package com.smart.db.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class MplBtu implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     *
     * @mbggenerated
     */
    private Long id;
    /**
     * 菜单id
     *
     * @mbggenerated
     */
    private Long menuId;
    /**
     * url
     *
     * @mbggenerated
     */
    private String url;
    /**
     * 按钮名称
     *
     * @mbggenerated
     */
    private String btnName;
    /**
     * 按钮图标
     *
     * @mbggenerated
     */
    private String icon;
    /**
     * 显示顺序
     *
     * @mbggenerated
     */
    private String sequence;
    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;
    /**
     * 创建人
     *
     * @mbggenerated
     */
    private String createUser;
    /**
     * 修改时间
     *
     * @mbggenerated
     */
    private Date updateTime;
    /**
     * 修改人
     *
     * @mbggenerated
     */
    private String updateUser;
    /**
     * 备注
     *
     * @mbggenerated
     */
    private String ramark;

}