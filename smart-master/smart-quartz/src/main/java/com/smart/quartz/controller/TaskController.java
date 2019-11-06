package com.smart.quartz.controller;

import com.common.result.Msg;
import com.common.result.PageRequest;
import com.smart.quartz.request.TaskAddRequest;
import com.smart.quartz.service.impl.TaskServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lwq
 */
@Api("定时任务")
@RestController("jobs")
public class TaskController {

    @Autowired
    private TaskServiceImpl jobsService;

    @ApiOperation("根据条件查询定时任务")
    @GetMapping("/getTaskList")
    public Msg getTaskList(
            @ApiParam("job名称")
            @RequestParam(required = false, value = "jobName") String jobName,
            @ModelAttribute PageRequest pageRequest) {
        return new Msg(jobsService.getTaskList(jobName, pageRequest));
    }


    @ApiOperation("添加一个定时任务")
    @PostMapping("/addTask")
    public Msg addTask(@ModelAttribute TaskAddRequest mplTask) {
        return new Msg(jobsService.addTask(mplTask));
    }

    @ApiOperation("删除一个定时任务")
    @PostMapping("/deleteTask")
    public Msg deleteTask(@RequestParam()
                      @ApiParam("通过id删除定时任务") String jobId) {
        return new Msg(null);
    }

}
