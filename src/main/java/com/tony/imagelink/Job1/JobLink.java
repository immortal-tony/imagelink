package com.tony.imagelink.Job1;

import com.tony.imagelink.mapper.GalleryFeaturesMapper;
import com.tony.imagelink.mapper.ModelMapper;
import com.tony.imagelink.mapper.entity.GalleryFeatures;
import com.tony.imagelink.mapper.entity.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName JobLink
 * Description TODO
 * @Author hzf
 * @Date 2020/12/13 11:32
 */
@Service
public class JobLink implements PageProcessor, Runnable {
    private static Logger log = LoggerFactory.getLogger(JobLink.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GalleryFeaturesMapper galleryFeaturesMapper;

    public volatile AtomicInteger count = new AtomicInteger(0);

    //下载的指定图集地址
    public static final String PIC_MAIN = "https://www.nvshens.org/tag/new/";

    public static final String PIC_COLL = "https://www\\.nvshens\\.org/tag/new/";
    public static final String PIC_COLLECTION = "https://www\\.nvshens\\.org/tag/new/\\d+";

    public static final String Model_collection = "https://www.nvshens.org/girl/\\d+";

    public static final String Model_PIC = "https://www.nvshens.org/g/\\d*";

//    public static final String Model_PIC_Detail = "https://www.nvshens.org/g/\\d*/\\d*\\.html";

    //解析页面
    @Override
    public void process(Page page) {
        GalleryFeatures galleryFeatures;
        List<Integer> collectionId = null;
        List<String> tag;
        Model model;
        String url = "";
        try {
            System.out.println("运行显示！");
            if (page.getUrl().regex(PIC_COLL).match()) {
                // 模特集合页面
                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"listdiv\"]").links().regex(PIC_COLLECTION).all());
                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"post_rank\"]/div[5]/div[1]/div[3]/div[1]/ul").links().regex(Model_collection).all());
                page.addTargetRequest("https://www.nvshens.org/girl/28093/");
            } else if (page.getUrl().regex(Model_collection).match()) {
                // 获取模特信息
                if (null != page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]")) {
                    model = new Model();
                    model.setName(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[1]/h1/text()").toString());//模特名
                    model.setUrl(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[3]/a/img/@src").toString());//模特图片
                    model.setAge(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[1]/td[2]/text()").toString());
                    model.setBeatyTag(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[6]/td[2]/text()").toString());
                    model.setBirtAddress(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[5]/td[2]/text()").toString());
                    model.setBirth(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[2]/td[2]/text()").toString());
                    model.setConstellation(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[3]/td[2]/text()").toString());
                    model.setStature(Integer.valueOf(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[4]/td[2]/text()").toString()));
                    model.setComment(page.getHtml().xpath("//*[@id=\"post\"]/div[5]/div/div[1]/div[2]/p/text()").toString());
                    if (null != page.getHtml().xpath("//*[@id=\"post\"]/div[8]")) {
                        collectionId = new ArrayList<>();
                        for (Selectable item : page.getHtml().xpath("//*[@id=\"post\"]/div[8]/div/div[3]/ul/li").nodes()) {
                            collectionId.add(Integer.valueOf(item.xpath("li/div[2]/a/@href").toString().split("/")[2]));
                            // 正则匹配后面不能加斜杠
                            page.addTargetRequests(item.xpath("li/div[2]").links().regex(Model_PIC).all());
                        }
                        String collectionIds = StringUtils.join(collectionId.toArray(), ", ");
                        model.setCollectionId(collectionIds);
                    }
                    log.info("模特信息 <{}>", model.toString());
                    // 数据库插入模特信息
                    modelMapper.insert(model);

                }
            } else if (page.getUrl().regex(Model_PIC).match()) {
                // 模特图片详情页面
                if (null != page.getHtml().xpath("//*[@id=\"high\"]/div")) {
                    for (Selectable item1 : page.getHtml().xpath("//*[@id=\"hgallery\"]/img").nodes()) {

                        galleryFeatures = new GalleryFeatures();
                        galleryFeatures.setTitle(page.getHtml().xpath("//*[@id=\"htilte\"]/text()").toString().trim());
                        galleryFeatures.setComments(page.getHtml().xpath("//*[@id=\"ddesc\"]/text()").toString().trim());
                        galleryFeatures.setPicNum(page.getHtml().xpath("//*[@id=\"dinfo\"]/span/text()").toString().trim());
                        tag = new ArrayList<>();
                        for (Selectable item : page.getHtml().xpath("//*[@id=\"utag\"]/li").nodes()) {
                            // 模特的特性Tag
                            tag.add(item.xpath("li/a/text()").toString());
                        }
                        String tag1 = StringUtils.join(tag.toArray(), ",");
                        galleryFeatures.setFeature(tag1);
                        url = item1.xpath("img/@src").toString();
                        galleryFeatures.setUrl(url);
                        galleryFeatures.setCollectionId(Integer.valueOf(url.split(":")[2].split("/")[3]));
                        String num = page.getHtml().xpath("//*[@id=\"dinfo\"]/text()").toString();
                        galleryFeatures.setViewNum(num);

                        log.info("模特的图片链接信息 <{}>", galleryFeatures.toString());

                        // 模特一张一张图片数据插入数据库
                        int ret = galleryFeaturesMapper.insert(galleryFeatures);
                        if(1 == ret){
                            count.incrementAndGet();
                            if(count.intValue()%100 == 0){
                                log.info("插入的图片连接数目达到: <{}> 百.", count.intValue()/100);
                            }
                        }
                    }
                }
                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"page\"]").links().all());
                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"pages\"]").links().all());
            }
        } catch (Exception e) {
            log.error("出现错误 error <{}> ", e.getMessage());
        }
    }

    //主函数执行爬虫
    public void run() {
        Spider.create(new JobLink())
                .addUrl(PIC_MAIN)   //设置爬去数据的页面
                .addPipeline(new FilePipeline("D:\\graph"))    //指定数据输出位置，注释该行就会输出到控制台
                .thread(10)     //设置线程个数来执行程序
                .run();     //执行爬虫
    }

    @Override
    public Site getSite() {
        return site;
    }

    private Site site = Site
            .me()
            .setSleepTime(5000)
            .setDomain("nvshens.org")
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
}
