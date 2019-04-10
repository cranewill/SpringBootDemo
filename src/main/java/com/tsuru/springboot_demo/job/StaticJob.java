package com.tsuru.springboot_demo.job;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName StaticJob
 * @Description 静态定时任务
 * @Author Tsuru
 **/
@Component
@Log4j2
public class StaticJob {

    private final long SECOND = 1000;

    /**
     * 上次执行完毕之后10s再执行
     */
    @Scheduled(fixedDelay = 10 * SECOND)
    public void fixedDelayJob() {
        log.debug("fixed delay job executed.");
    }

    /**
     * 上次执行开始之后10s再执行
     */
    @Scheduled(fixedRate = 10 * SECOND)
    public void fixedRateJob() {
        log.debug("fixed rate job executed.");
    }

    /**
     * 根据cron表达式定义
     */
    @Scheduled(cron = "*/3 * * * * *")
    public void cronJob() {
        log.debug("cron job executed.");
    }
}
