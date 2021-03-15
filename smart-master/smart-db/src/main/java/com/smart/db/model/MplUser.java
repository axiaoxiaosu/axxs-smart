package com.smart.db.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ToString
public class MplUser implements Serializable {
    /**
     * id
     *
     * @mbggenerated
     */
    private Long id;
    /**
     * 用户名
     *
     * @mbggenerated
     */
    private String username;
    /**
     * 用户密码
     *
     * @mbggenerated
     */
    private String password;
    /**
     * 用户上次密码
     *
     * @mbggenerated
     */
    private String password1;
    /**
     * 用户上上次密码
     *
     * @mbggenerated
     */
    private String password2;
    /**
     * 用户昵称
     *
     * @mbggenerated
     */
    private String nickName;
    /**
     * 用户头像
     *
     * @mbggenerated
     */
    private String portrait;
    /**
     * 用户邮箱
     *
     * @mbggenerated
     */
    private String email;
    /**
     * 用户最后一次登录时间
     *
     * @mbggenerated
     */
    private Date lastTime;
    /**
     * 用户是否有效(0有效,1冻结,2,无效)
     *
     * @mbggenerated
     */
    private Integer status;
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
    private String remark;
}