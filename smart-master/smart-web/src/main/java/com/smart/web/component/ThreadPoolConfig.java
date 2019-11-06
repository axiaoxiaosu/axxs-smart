package com.smart.web.component;

import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author lwq
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("smartThread")
    public ThreadPoolTaskExecutor setPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        threadPoolTaskExecutor.setCorePoolSize(10);
        // 最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(15);
        // 队列最大长度
        threadPoolTaskExecutor.setQueueCapacity(500);
        // 线程池维护线程所允许的空闲时间
        threadPoolTaskExecutor.setKeepAliveSeconds(300);
        //线程命名
        threadPoolTaskExecutor.setThreadNamePrefix("【smartThread】=");
        /*关闭线程池*/
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        //线程池对拒绝任务(无线程可用)的处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }

}
