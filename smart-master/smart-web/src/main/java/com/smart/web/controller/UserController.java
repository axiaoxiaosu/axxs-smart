package com.smart.web.controller;

import com.common.base.BaseController;
import com.common.exception.SmartException;
import com.common.result.CommonResult;
import com.common.result.Msg;
import com.smart.web.codefragment.ControllerCode;
import com.smart.web.model.MyUserDetail;
import com.smart.web.request.AddUserRequest;
import com.smart.web.request.GetUserRequest;
import com.smart.web.request.LoginRequest;
import com.smart.web.request.RegisterRequest;
import com.smart.web.request.UpdatePasswordRequest;
import com.smart.web.service.IprecordService;
import com.smart.web.service.UserService;
import com.smart.web.util.SmartUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwq
 */

@Api(description = "用户基本操作")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    private IprecordService iprecordService;


    @ApiOperation(value = "登陆接口")
    @PostMapping(value = "/login")
    public Msg login(@RequestBody LoginRequest vo, HttpServletRequest request) {
        //记录下访问用户
        iprecordService.ipAddress(request);
        return new Msg(userService.login(vo));
    }

    @PostMapping("/logout")
    @ApiOperation(value = "登出接口")
    public Msg logout() {
        return new Msg("");
    }


    @ApiOperation(value = "获取用户信息")
    @GetMapping("/info")
    public Msg info(@AuthenticationPrincipal MyUserDetail myUserDetail) {
        return new Msg(myUserDetail.getUser());
    }

    @ApiOperation(value = "获取用户资源")
    @PostMapping("/resource")
    public Msg getResource(@AuthenticationPrincipal MyUserDetail userDetail) {
        log.info("用户{}获取了资源({})", userDetail.getUsername(), userDetail.getMplMenu());
        return new Msg(userDetail.getMplMenu());
    }

    @ApiOperation(value = "注册接口")
    @PostMapping(value = "register")
    public Msg register(@RequestBody RegisterRequest user) {
        return new Msg(userService.register(user));
    }


    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/updatePassword")
    public Msg updatePassword(
            @AuthenticationPrincipal MyUserDetail myUserDetail,
            @RequestBody UpdatePasswordRequest vo) {
        return new Msg(userService.UpdatePassword(myUserDetail, vo));
    }

    @ApiOperation(value = "重置用户密码")
    @GetMapping("/resetPassword")
    public Msg resetPassword(@AuthenticationPrincipal MyUserDetail myUserDetail,
                             @RequestParam("userId") String userId) {
        if (!SmartUtil.isSuperUser(myUserDetail.getMplRole().getRoleName())) {
            throw new SmartException(500, "您不具有此操作权限");
        }
        return new Msg(userService.resetPassword(userId));
    }


    @ApiOperation(value = "分页查询用户信息")
    @PostMapping("/getUsersPage")
    public Msg getUsersPage(@AuthenticationPrincipal MyUserDetail myUserDetail,
                            @ModelAttribute GetUserRequest getUserRequest) {
        if (!SmartUtil.isSuperUser(myUserDetail.getMplRole().getRoleName())) {
            throw new SmartException(500, "您不具有此操作权限");
        }
        return new CommonResult(userService.getUsersPage(myUserDetail, getUserRequest));
    }


    @ApiOperation(value = "添加用户记录")
    @PostMapping("/addUser")
    public Msg addUser(@AuthenticationPrincipal MyUserDetail myUserDetail,
                       @ModelAttribute AddUserRequest addUserRequest) {
        if (!SmartUtil.isSuperUser(myUserDetail.getMplRole().getRoleName())) {
            throw new SmartException(500, "您不具有此操作权限");
        }
        return new Msg(userService.addUser(myUserDetail, addUserRequest));
    }


    @ApiOperation(value = "删除用户信息")
    @PostMapping("/deleteUser")
    public Msg deleteUser(@AuthenticationPrincipal MyUserDetail myUserDetail,
                          @RequestParam @ApiParam("用户id") String userIds) {
        if (!SmartUtil.isSuperUser(myUserDetail.getMplRole().getRoleName())) {
            throw new SmartException(500, "您不具有此操作权限");
        }
        return new Msg(userService.deleteUser(myUserDetail, ControllerCode.convertId(userIds)));
    }


    @ApiOperation(value = "修改用户信息")
    @PostMapping("/updateUser")
    public Msg updateUser(@AuthenticationPrincipal MyUserDetail myUserDetail,
                          @ModelAttribute AddUserRequest addUserRequest) {
        if (!SmartUtil.isSuperUser(myUserDetail.getMplRole().getRoleName())) {
            throw new SmartException(500, "您不具有此操作权限");
        }
        return new Msg(userService.updateUser(myUserDetail, addUserRequest));
    }
}
