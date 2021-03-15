package com.smart.quartz.controller;

import com.common.result.CommonResult;
import com.common.result.Msg;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.smart.db.model.MplMenu;
import com.smart.quartz.request.AddTaskRequest;
import com.smart.quartz.request.GetTaskListRequest;
import com.smart.quartz.service.impl.TaskServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author lwq
 */
@Api("定时任务")
@RestController()
@RequestMapping("/jobs")
public class TaskController {

    @Autowired
    private TaskServiceImpl jobsService;

    @ApiOperation("根据条件查询定时任务")
    @PostMapping("/getTaskList")
    public Msg getTaskList(
            @ApiParam("job名称")
            @RequestBody(required = false) GetTaskListRequest getTaskListRequest) {
        return new CommonResult(jobsService.getTaskList(getTaskListRequest));
    }

    public static void main(String[] args) {
        List<String> li = new ArrayList<>();
        li.add("userid");
        li.add("channel");
        li.add("keycode");
        Collections.sort(li);

        System.out.println(li);

    }


    @ApiOperation("添加一个定时任务")
    @PostMapping("/addTask")
    public Msg addTask(@RequestBody AddTaskRequest mplTask) {
        return new Msg(jobsService.addTask(mplTask));
    }


    @ApiOperation("删除一个定时任务")
    @DeleteMapping("/deleteTask")
    public Msg deleteTask(@RequestParam()
                          @ApiParam("通过id删除定时任务") String jobId) {
        return new Msg(jobsService.deleteTask(jobId));
    }

}
