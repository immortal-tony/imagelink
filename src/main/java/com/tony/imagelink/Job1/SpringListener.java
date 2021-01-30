package com.tony.imagelink.Job1;

import com.tony.imagelink.mapper.GalleryFeaturesMapper;
import com.tony.imagelink.mapper.ModelMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpringListener
 * Description TODO
 * @Author hzf
 * @Date 2020/12/14 10:17
 */
@Component
public class SpringListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private JobLink jobLink;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // 启动爬虫
        System.out.println("启动爬虫线程任务");
//        Thread my = new Thread(jobLink, "我的线程");
//        my.run();
        jobLink.run();
    }
}
