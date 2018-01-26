package com.ms.cas.schedule;

import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySchedule {

    // corn中是秒 分 小时 日 月 年
//    @Scheduled(cron = "0/5 * * * * *")
    public void clock() {
        String content = "cas 模块" + Calendar.getInstance().getTimeInMillis();
        System.out.println(content);
    }
}
