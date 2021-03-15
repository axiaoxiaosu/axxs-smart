package com.smart.api.request;

import com.common.result.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lwq
 */
@Data
@ApiModel("查询菜单条件")
public class GetMenusRequest extends PageRequest {

    @ApiModelProperty("菜单名称")
    private String title;


    @ApiModelProperty("是否显示隐藏菜单")
    private Boolean isShow = false;


    @ApiModelProperty("数据结构(默认row)")
    private String dataType = "row";


    @ApiModelProperty("资源类型")
    private List<String> types;
}
