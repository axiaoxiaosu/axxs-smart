package com.smart.quartz.util;

import com.smart.db.model.MplTask;
import org.quartz.JobExecutionContext;

/**
 * @author lwq
 */
public class NotConcurrentJob extends AbstractJob {

    @Override
    protected void toExecute(JobExecutionContext context, MplTask mplTask) throws Exception {
        JobInvokeUtil.invokeMethod(mplTask);
    }
}
