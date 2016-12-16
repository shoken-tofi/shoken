package com.bsuir.shoken.iam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
class ServiceConfiguration {

    private static final int POOL_SIZE = 1000;

    @Bean
    public TaskScheduler taskScheduler() {

        final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(POOL_SIZE);
        taskScheduler.setDaemon(true);

        return taskScheduler;
    }
}
