package com.smart.api.controller;

import com.common.base.BaseController;
import com.common.result.CommonResult;
import com.common.result.Msg;
import com.smart.api.model.MyUserDetail;
import com.smart.api.request.AddMenuRequest;
import com.smart.api.request.GetMenusRequest;
import com.smart.api.service.MenuService;
import com.smart.api.util.TreeUtil;
import com.smart.db.model.MplMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author lwq
 * @data 2019/8/16
 */
@Api("资源api")
@RestController
@RequestMapping("/menus")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("获取自己名下全部资源信息")
    @PostMapping("/getMenus")
    public Msg getMenus(@AuthenticationPrincipal UserDetails myUserDetail) {
        List<MplMenu> result = menuService.getMenus(myUserDetail.getUsername(), null);
        //转换为tree结构
        result = TreeUtil.toTreeUpdateParam(result, 0, 0L);
        return new Msg(result);
    }

    @ApiOperation("获取自己名下(分页查询)资源信息")
    @PostMapping(value = "/getMenusPage")
    public CommonResult<MplMenu> getMenusPage(@AuthenticationPrincipal UserDetails myUserDetail,
                                              @Validated @RequestBody(required = false) GetMenusRequest getRolesRequest) {
        List<MplMenu> result = menuService.getMenus(myUserDetail.getUsername(), getRolesRequest);
        //转换为tree结构
        if (getRolesRequest != null && getRolesRequest.getDataType().equalsIgnoreCase("tree")) {
            result = TreeUtil.toTreeUpdateParam(result, 0, 0L);
        }
        return new CommonResult<>(result);
    }

    @ApiOperation("添加资源")
    @PostMapping("/addMenu")
    public Msg addMenu(@AuthenticationPrincipal MyUserDetail myUserDetail,
                       @RequestBody AddMenuRequest addMenuRequest) {
        return new Msg(menuService.addMenu(myUserDetail, addMenuRequest));
    }

    @ApiOperation("删除资源")
    @PostMapping("/deleteMenu")
    public Msg deleteMenu(
            @NotBlank @ApiParam("资源id(多个资源用,隔开)") @RequestParam String menusIds) {
        return new Msg(menuService.deleteMenu(convertId(menusIds)));
    }

    @ApiOperation("修改资源")
    @PostMapping("/updateMenu")
    public Msg updateMenu(
            @AuthenticationPrincipal MyUserDetail myUserDetail,
            @ApiParam("修改内容") @RequestBody AddMenuRequest addMenuRequest) {
        return new Msg(menuService.updateMenu(addMenuRequest));
    }

    @ApiOperation("获取操作按钮")
    @PostMapping("/getBtu")
    public Msg getBtu(
            @AuthenticationPrincipal MyUserDetail myUserDetail,
            @ApiParam("菜单id") @RequestParam String menuId) {
        return new Msg(menuService.getBtu(myUserDetail, menuId));
    }

}
