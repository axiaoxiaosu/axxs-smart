package com.smart.web.request;

import com.common.annotationoo.DicMark;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lwq
 */
@Data
@ApiModel
@Accessors(chain = true)
public class AddMenuRequest {


    /**
     * id
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 类型(1模块  2菜单)
     */
    @DicMark
    private String type;
    /**
     * 1显示  非1 隐藏
     */
    @DicMark
    private String isShow = "1";
    /**
     * 0代表顶级菜单
     */
    private Long parentId = 0L;
    /**
     * 对应组件的path
     */
    private String url;
    /**
     * icon图标 需要定义好的component
     */
    private String icon;
    /**
     * 显示顺序
     */
    private String sort;
    /**
     * 备注
     */
    private String ramark;

    /**
     * 按钮类型
     */
    @DicMark
    private String btnType;




}
