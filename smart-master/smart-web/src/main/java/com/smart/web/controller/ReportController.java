package com.smart.web.controller;

import com.common.result.Msg;
import com.smart.web.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api("统计相关api")
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @ApiOperation("查询访问情况")
    @GetMapping("/ip")
    public Msg reportIp() {
        return new Msg(reportService.reportIp());
    }
}
