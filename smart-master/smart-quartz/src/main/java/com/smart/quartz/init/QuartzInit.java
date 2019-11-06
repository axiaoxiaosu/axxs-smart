package com.smart.quartz.init;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.smart.db.dao.MplTaskDAO;
import com.smart.db.model.MplTask;
import com.smart.quartz.util.ScheduleUtils;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author lwq
 */
@Component
@Slf4j
public class QuartzInit implements ApplicationRunner {


    @Autowired
    private Scheduler scheduler;

    @Autowired
    private MplTaskDAO mplTaskDAO;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.warn("init Quartz ...");
        //删除历史任务并根据列表 添加添加job
        try {
            List<MplTask> mplTaskList = mplTaskDAO.selectJobByName("");
            if (mplTaskList != null && mplTaskList.size() > 0) {
                for (MplTask mplTask : mplTaskList) {
                    JobKey jobKey = ScheduleUtils.getJobKey(mplTask.getJobId(), mplTask.getJobGroup());
                    if (scheduler.checkExists(jobKey)) {
                        //防止数据有误 先删除在重新加载
                        scheduler.deleteJob(jobKey);
                        ScheduleUtils.createScheduleJob(scheduler, mplTask);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("init Quartz err{}", ExceptionUtil.getMessage(e));
        }
        log.warn("init Quartz success");
    }
}
