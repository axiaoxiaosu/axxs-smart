package com.smart.quartz.util;

import com.smart.db.model.MplTask;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * @author lwq DisallowConcurrentExecution 禁止同一个job同时有2个实例
 */
@DisallowConcurrentExecution
public class ConcurrentJob extends AbstractJob {

    @Override
    protected void toExecute(JobExecutionContext context, MplTask mplTask) throws Exception {
        JobInvokeUtil.invokeMethod(mplTask);
    }
}
