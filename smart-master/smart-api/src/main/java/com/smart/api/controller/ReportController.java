package com.smart.api.controller;

import com.common.result.Msg;
import com.smart.api.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api("统计相关api")
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @ApiOperation("查询访问情况")
    @GetMapping("/ip")
    public Msg reportIp(@AuthenticationPrincipal UserDetails myUserDetail) {
        List<SimpleGrantedAuthority> list = (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (list.isEmpty()) return null;
        return new Msg(reportService.reportIp());
    }

}
