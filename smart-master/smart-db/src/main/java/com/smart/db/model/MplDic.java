package com.smart.db.model;

import java.io.Serializable;
import java.util.Date;

/**
 * mpl_dic
 * @author 
 */
public class MplDic implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 字典显示文本
     */
    private String text;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字典名称
     */
    private String code;

    /**
     * 字典值
     */
    private String value;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}