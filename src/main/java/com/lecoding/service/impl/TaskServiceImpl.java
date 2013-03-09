package com.lecoding.service.impl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-8 下午5:12
 */
@Service
public class TaskServiceImpl {
    @Scheduled(cron = "0 1 * * * *")
    public void work() {
        Logger.getLogger(this.getClass()).log(Level.INFO, new Date().getTime());
    }
}
