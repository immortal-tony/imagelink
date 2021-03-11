package com.tony.imagelink.Job1;

import com.tony.imagelink.mapper.GalleryFeaturesMapper;
import com.tony.imagelink.mapper.ModelMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * @ClassName SpringListener
 * Description TODO
 * @Author hzf
 * @Date 2020/12/14 10:17
 */
@Component
@Slf4j
public class SpringListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private JobLink jobLink;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // 启动爬虫
        System.out.println("启动爬虫线程任务");
//        Thread my = new Thread(jobLink, "我的线程");
//        my.run();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 10); // 控制时
        c.set(Calendar.MINUTE, 58); // 控制分
        c.set(Calendar.SECOND, 0); // 控制秒
        Date time = c.getTime(); // 得到执行任务的时间,此处为当天的10：58：00
        Timer timer = new Timer();
        // 八天抓取一次
        timer.scheduleAtFixedRate(jobLink, time, 8 * 24 * 60 * 60 * 1000);

//        addVideoUrl();
    }

    /**
     * 添加上视频文件的方法函数
     */
    private void addVideoUrl() {
        ClassPathResource resource = new ClassPathResource("requestUrl_20210311.txt");
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        String today = dft.format(new Date());
        // 历史的就不插入了
        if (resource.getFilename().equals("requestUrl_" + today)) {
            try {
                DataInputStream ds = new DataInputStream(resource.getInputStream());
                String str = null;
//                if((str = ds.read()) != null){
//
//                }
            } catch (Exception e) {
                log.info("导入视频的地址失败", e);
                e.printStackTrace();
            }

        }
    }
}
