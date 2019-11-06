package com.smart.quartz.service;

import com.common.result.PageRequest;
import com.smart.db.model.MplTask;
import com.smart.quartz.request.TaskAddRequest;
import java.util.List;

/**
 * @author lwq
 */
public interface TaskService {

    List<MplTask> getTaskList(String jobName, PageRequest pageRequest);

    boolean addTask(TaskAddRequest mplTask);
}
