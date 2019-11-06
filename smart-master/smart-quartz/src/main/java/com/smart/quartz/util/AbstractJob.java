package com.smart.quartz.util;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.common.constants.QuartzConstant;
import com.smart.db.dao.MplTaskDAO;
import com.smart.db.dao.MplTaskLogDAO;
import com.smart.db.model.MplTask;
import com.smart.db.model.MplTaskLog;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Job基类
 *
 * @author lwq
 */
@Slf4j
public abstract class AbstractJob implements Job {

    /**
     * 线程变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();
    @Autowired
    MplTaskDAO mplTaskDAO;
    @Autowired
    MplTaskLogDAO mplTaskLogDAO;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //记录时间
        before();
        //获取quartz中需要执行的key
        MplTask mplTask = new MplTask();
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(QuartzConstant.TASK_JOB_SMART), mplTask);
        try {
            before();
            toExecute(context, mplTask);
            after(context, mplTask);
        } catch (Exception e) {
            MplTaskLog mplTaskLog = new MplTaskLog();
            BeanUtils.copyProperties(mplTask, mplTaskLog);
            mplTaskLog.setMsg("执行异常")
                    .setError(ExceptionUtil.getMessage(e));
            mplTaskLogDAO.insertSelective(mplTaskLog);
            log.error("任务执行异常{}", e);
            after(context, mplTask);
        }

    }


    /**
     * 执行前
     */
    public void before() {
        //记录当前线程加入时间
        threadLocal.set(new Date());
    }


    /**
     * 执行后
     */

    @Transactional(rollbackFor = Exception.class)
    public void after(JobExecutionContext context, MplTask mplTask) {
        long markLong = System.currentTimeMillis() - threadLocal.get().getTime() / 1000;
        mplTask.setStatus("0");
        //执行次数+1
        mplTask.setCount(mplTask.getCount() + 1);
        mplTaskDAO.updateByPrimaryKeySelective(mplTask);

        MplTaskLog mplTaskLog = new MplTaskLog();
        BeanUtils.copyProperties(mplTask, mplTaskLog);
        mplTaskLog.setTimeConsuming(markLong)
                .setMsg("执行成功");
        mplTaskLogDAO.insertSelective(mplTaskLog);

        log.warn("耗时-{}秒", markLong);
    }

    /**
     * 走子类实现
     */
    protected abstract void toExecute(JobExecutionContext context, MplTask mplTask) throws Exception;
}
