package com.smart.quartz.service.impl;

import cn.hutool.core.net.NetUtil;
import com.common.exception.SmartException;
import com.common.util.SmartUtil;
import com.github.pagehelper.PageHelper;
import com.smart.db.dao.MplTaskDAO;
import com.smart.db.model.MplTask;
import com.smart.quartz.request.AddTaskRequest;
import com.smart.quartz.request.GetTaskListRequest;
import com.smart.quartz.service.TaskService;
import com.smart.quartz.util.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lwq
 */
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Resource
    MplTaskDAO mplTaskDAO;
    @Autowired
    private Scheduler scheduler;

    public static void main(String[] args) {
        System.out.println(NetUtil.longToIpv4(3085521380L));
    }

    @Override
    public List<MplTask> getTaskList(GetTaskListRequest getTaskListRequest) {
        PageHelper.startPage(getTaskListRequest.getPageNum(), getTaskListRequest.getPageSize());
        return mplTaskDAO.selectJobByName(getTaskListRequest.getJobName());
    }

    @Override
    public boolean addTask(AddTaskRequest mplTask) {
        MplTask mplTaskPo = new MplTask();
        BeanUtils.copyProperties(mplTask, mplTaskPo);
        int mack = mplTaskDAO.insertSelective(mplTaskPo);
        if (mack > 0) {
            try {
                ScheduleUtils.createScheduleJob(scheduler, mplTaskPo);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("添加任务失败 异常:{}", e.getMessage());
            }
        }
        return SmartUtil.isSucceed(mack);
    }

    @Override
    public boolean deleteTask(String jobId) {
        Long jobIdL = Long.parseLong(jobId);
        MplTask mplTask = mplTaskDAO.selectByPrimaryKey(jobIdL);
        if (mplTask == null) {
            throw new SmartException(-1,"2");
        }
        int mark = mplTaskDAO.deleteByPrimaryKey(jobIdL);
        if (mark > 0) {
            try {
                scheduler.deleteJob(ScheduleUtils.getJobKey(jobIdL, mplTask.getJobGroup()));
            } catch (Exception e) {
                throw new SmartException(-2,"3");
            }
        }
        return true;
    }
}
