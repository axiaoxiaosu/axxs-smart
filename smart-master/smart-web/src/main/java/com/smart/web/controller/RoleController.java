package com.smart.web.controller;

import static com.common.enums.SmartExceptionEunms.PARAMETER_NULL;

import com.common.base.BaseController;
import com.common.exception.SmartException;
import com.common.result.CommonResult;
import com.common.result.Msg;
import com.smart.db.model.MplRole;
import com.smart.web.codefragment.ControllerCode;
import com.smart.web.model.MyUserDetail;
import com.smart.web.request.GetRolesRequest;
import com.smart.web.request.SetUserRoleRequest;
import com.smart.web.service.RoleService;
import com.smart.web.util.SmartUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwq
 * @data 2019/7/16
 */
@Api("角色管理")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取自己的用户角色
     *
     * @param myUserDetail 用户信息
     */
    @ApiOperation("根据token获取角色信息")
    @PostMapping("/getRole")
    public Msg getRole(@AuthenticationPrincipal MyUserDetail myUserDetail) {
        if (myUserDetail.getMplRole() != null) {
            return new Msg(myUserDetail.getMplRole());
        }
        MplRole userRole = roleService.getRole(myUserDetail.getUsername());
        return new Msg(userRole);
    }

    /**
     * 添加角色
     */
    @ApiOperation("添加用户角色")
    @PostMapping("/addRole")
    public Msg addRole(
            @AuthenticationPrincipal MyUserDetail myUserDetail, @RequestParam String roleName) {
        if (StringUtils.isEmpty(roleName)) {
            throw new SmartException(PARAMETER_NULL);
        }
        return new Msg(roleService.addRole(myUserDetail, roleName) ? "操作成功" : "操作失败");
    }

    /**
     * 绑定角色和用户
     */
    @ApiOperation("绑定角色和用户")
    @PostMapping("/setUserRole")
    public Msg setUserRole(
            @AuthenticationPrincipal MyUserDetail myUserDetail,
            @ModelAttribute SetUserRoleRequest setUserRoleRequest) {
        return new Msg(roleService.setUserRole(myUserDetail, setUserRoleRequest) ? "操作成功" : "操作失败");
    }

    @ApiOperation("获取全部角色信息")
    @PostMapping("/getRoles")
    public CommonResult getRoles(
            @AuthenticationPrincipal MyUserDetail userDetails,
            @ModelAttribute GetRolesRequest getRolesRequest) {
        return new CommonResult(roleService.getRoles(getRolesRequest));
    }

    @ApiOperation("根据id删除角色数据")
    @PostMapping("/deleteRole")
    public Msg deleteRole(
            @AuthenticationPrincipal MyUserDetail myUserDetail, @NotBlank @RequestParam String roleId) {
        if (!SmartUtil.isSuperUser(myUserDetail.getMplRole().getRoleName())) {
            throw new SmartException(500, "您不具有此操作权限");
        }
        return new Msg(roleService.deleteRoles(ControllerCode.convertId(roleId)));
    }

    @ApiOperation("修改角色信息")
    @PostMapping("/updateRole")
    public Msg updateRole(
            @AuthenticationPrincipal MyUserDetail myUserDetail,
            @NotBlank @RequestParam @ApiParam("角色id") Long id,
            @NotBlank @RequestParam @ApiParam("角色名称") String roleName) {
        if (!SmartUtil.isSuperUser(myUserDetail.getMplRole().getRoleName())) {
            throw new SmartException(500, "您不具有此操作权限");
        }
        return new Msg(roleService.updateRole(myUserDetail, id, roleName));
    }

    @ApiOperation("查询角色所拥有的资源")
    @PostMapping("/mensByRole")
    public Msg mensByRole(@NotBlank @ApiParam("角色id") Long id) {
        return new Msg(roleService.mensByRole(id));
    }


    @ApiOperation("关联角色和菜单")
    @PostMapping("/roleBindMent")
    public Msg roleBindMent(@NotBlank @RequestParam @ApiParam("角色id") String roId,
                            @RequestParam @ApiParam("资源id") String mendIds) {
        return new Msg(roleService.roleBindMent(mendIds.split(","), roId));
    }
}
