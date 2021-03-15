package com.smart.db.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
public class MplRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     *
     * @mbggenerated
     */
    private Long id;
    /**
     * 角色id
     *
     * @mbggenerated
     */
    private Long roleId;
    /**
     * 菜单id
     *
     * @mbggenerated
     */
    private Long menuId;

}