package com.smart.db.model;

import com.common.annotationoo.DicMark;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * mpl_role
 *
 * @author
 */
@Data
public class MplRole implements Serializable {

    private final String tableName = "mpl_role";
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 是否启用(0有效,1冻结,2,无效)
     */
    @DicMark
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUser;
    private String createName;
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
}