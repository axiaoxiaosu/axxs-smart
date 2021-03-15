package com.smart.quartz.util;

import com.common.constants.QuartzConstant;
import com.common.enums.QuartzEnums;
import com.common.exception.SmartException;
import com.smart.db.model.MplTask;
import org.quartz.*;

/**
 * quartz操作
 *
 * @author ruoyi
 */
public class ScheduleUtils {

    /**
     * 得到quartz任务类
     *
     * @param mplTask 执行计划
     * @return 具体执行任务类
     */
    private static Class<? extends Job> getQuartzJobClass(MplTask mplTask) {
        boolean isConcurrent = "0".equals(mplTask.getConcurrency());
        return isConcurrent ? NotConcurrentJob.class : ConcurrentJob.class;
    }

    /**
     * 构建任务触发对象
     */
    public static TriggerKey getTriggerKey(Long jobId, String jobGroup) {
        return TriggerKey.triggerKey(QuartzConstant.TASK_JOB_SMART + jobId, jobGroup);
    }

    /**
     * 构建任务键对象
     */
    public static JobKey getJobKey(Long jobId, String jobGroup) {
        return JobKey.jobKey(QuartzConstant.TASK_JOB_SMART + jobId, jobGroup);
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, MplTask mplTask) throws Exception {
        Long jobId = null;
        String jobGroup = null;
        JobDetail jobDetail = null;
        CronTrigger trigger = null;
        try {
            Class<? extends Job> jobClass = getQuartzJobClass(mplTask);
            // 构建job信息
            jobId = mplTask.getJobId();
            jobGroup = mplTask.getJobGroup();

            //构建任务详情
            jobDetail = JobBuilder.newJob(jobClass).withIdentity(getJobKey(jobId, jobGroup))
                    .build();

            // 表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                    .cronSchedule(mplTask.getCronExpression());
            cronScheduleBuilder = handleCronScheduleMisfirePolicy(mplTask, cronScheduleBuilder);

            // 按新的cronExpression表达式构建一个新的trigger
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(jobId, jobGroup))
                    .withSchedule(cronScheduleBuilder).build();

            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(QuartzConstant.TASK_JOB_SMART, mplTask);

            // 判断是否存在
            if (scheduler.checkExists(getJobKey(jobId, jobGroup))) {
                // 防止创建时存在数据问题 先移除，然后在执行创建操作
                scheduler.deleteJob(getJobKey(jobId, jobGroup));
            }
        } catch (Exception e) {
            throw new SmartException(-4, "-3");
        }

        scheduler.scheduleJob(jobDetail, trigger);
        // 暂停任务
        if (mplTask.getStatus().equals(QuartzEnums.QUARTZ_STATUS_1.getCode())) {
            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
    }

    /**
     * 设置定时任务策略
     */
    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(MplTask mplTask,
                                                                      CronScheduleBuilder cb)
            throws Exception {
        switch (mplTask.getMisfirePolicy()) {
            case QuartzConstant.MISFIRE_DEFAULT:
                return cb;
            case QuartzConstant.MISFIRE_IGNORE_MISFIRES:
                return cb.withMisfireHandlingInstructionIgnoreMisfires();
            case QuartzConstant.MISFIRE_FIRE_AND_PROCEED:
                return cb.withMisfireHandlingInstructionFireAndProceed();
            case QuartzConstant.MISFIRE_DO_NOTHING:
                return cb.withMisfireHandlingInstructionDoNothing();
            default:
                throw new SmartException(500, "未知的执行策略");
        }
    }
}