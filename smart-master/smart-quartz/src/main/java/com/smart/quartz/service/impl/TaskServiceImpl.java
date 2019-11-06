package com.smart.quartz.service.impl;

import com.common.result.PageRequest;
import com.github.pagehelper.PageHelper;
import com.smart.db.dao.MplTaskDAO;
import com.smart.db.model.MplTask;
import com.smart.quartz.request.TaskAddRequest;
import com.smart.quartz.service.TaskService;
import com.smart.quartz.util.ScheduleUtils;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lwq
 */
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    MplTaskDAO mplTaskDAO;
    @Autowired
    private Scheduler scheduler;

    @Override
    public List<MplTask> getTaskList(String jobName, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        return mplTaskDAO.selectJobByName(jobName);
    }

    @Override
    public boolean addTask(TaskAddRequest mplTask) {
        MplTask mplTaskPo = new MplTask();
        BeanUtils.copyProperties(mplTask, mplTaskPo);
        int mack = mplTaskDAO.insertSelective(mplTaskPo);
        if (mack > 0) {
            try {
                ScheduleUtils.createScheduleJob(scheduler, mplTaskPo);
            } catch (Exception e) {
                log.error("添加任务失败 异常:{}", e.getMessage());
            }
        }
        return mack > 0;
    }
}
