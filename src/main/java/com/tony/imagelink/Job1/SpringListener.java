package com.tony.imagelink.Job1;

import com.tony.imagelink.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * @ClassName SpringListener
 * Description TODO
 * @Author hzf
 * @Date 2020/12/14 10:17
 */
public class SpringListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private JobLink jobLink;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // 启动爬虫
        jobLink.run();
    }
}
