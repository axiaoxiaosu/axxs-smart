package com.smart.quartz.service;

import com.smart.db.model.MplTask;
import com.smart.quartz.request.AddTaskRequest;
import com.smart.quartz.request.GetTaskListRequest;

import java.util.List;

/**
 * @author lwq
 */
public interface TaskService {

    /**
     * 获取定时任务列表
     */
    List<MplTask> getTaskList(GetTaskListRequest getTaskListRequest);

    /**
     * 添加一个定时任务
     */
    boolean addTask(AddTaskRequest mplTask);

    /**
     * 删除任务
     */
    boolean deleteTask(String jobId);
}
