package com.tony.imagelink.Job1;

import com.tony.imagelink.mapper.GalleryFeaturesMapper;
import com.tony.imagelink.mapper.ModelMapper;
import com.tony.imagelink.mapper.entity.GalleryFeatures;
import com.tony.imagelink.mapper.entity.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName JobLink
 * Description TODO
 * @Author hzf
 * @Date 2020/12/13 11:32
 */
@Service
public class JobLink extends TimerTask implements PageProcessor, ApplicationContextAware {
    private static Logger log = LoggerFactory.getLogger(JobLink.class);

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        JobLink.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clz) throws BeansException {
        return (T) applicationContext.getBean(clz);
    }

    private ModelMapper modelMapper;

    private GalleryFeaturesMapper galleryFeaturesMapper;

    public volatile AtomicInteger count = new AtomicInteger(0);

    //下载的指定图集地址
    public static final String PIC_MAIN = "https://www.nvshens.org/tag/new/";

    public static final String PIC_COLL = "https://www\\.nvshens\\.org/tag/new/\\d*";
    public static final String PIC_COLLECTION = "https://www\\.nvshens\\.org/tag/new/\\d+";

    public static final String Model_collection = "https://www.nvshens.org/girl/\\d*";

    public static final String Model_PIC = "https://www.nvshens.org/g/\\d*";

    public static final String Model_PIC_Detail = "https://www.nvshens.org/g/\\d*/\\d*\\.html";

    //解析页面
    @Override
    public void process(Page page) {
        GalleryFeatures galleryFeatures;
        List<Integer> collectionId = null;
        List<String> tag;
        Model model;
        String url = "";
        try {
            if (page.getUrl().regex(PIC_COLL).match()) {
                // 模特集合页面
                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"listdiv\"]").links().regex(PIC_COLLECTION).all());//只能定位到当前元素页的的标签定位
                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"post_rank\"]/div[5]/div[1]/div[3]/div[1]/ul").links().regex(Model_collection).all());
                page.addTargetRequest("https://www.nvshens.org/girl/28093/");
                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"listdiv\"]/div[3]/div/a").links().all());
            } else if (page.getUrl().regex(Model_collection).match()) {
                // 获取模特信息
                Map<String, String> info = null;
                if (null != page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]")) {
                    modelMapper = getBean(ModelMapper.class);
                    int modelId = Integer.valueOf(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[3]/a/img/@src").toString().split("/")[4]);
                    String collectionIds = null;
                    if (null != page.getHtml().xpath("//*[@id=\"post\"]/div[8]")) {
                        collectionId = new ArrayList<>();
                        for (Selectable item : page.getHtml().xpath("//*[@id=\"post\"]/div[8]/div/div[3]/ul/li").nodes()) {
                            collectionId.add(Integer.valueOf(item.xpath("li/div[2]/a/@href").toString().split("/")[2]));
                            // 正则匹配后面不能加斜杠
                            page.addTargetRequests(item.xpath("li/div[2]").links().regex(Model_PIC).all());
                        }
                        collectionIds = StringUtils.join(collectionId.toArray(), ", ");
                    }
                    List<Integer> ecistsCollectionIds = modelMapper.checkModel(modelId, collectionIds);
                    if (!(ecistsCollectionIds.size() == collectionId.size())) {
                        collectionId.removeAll(ecistsCollectionIds);
                        for (Integer item1 : collectionId) {
                            model = new Model();
                            model.setModelId(modelId);
                            model.setName(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[1]/h1/text()").toString());//模特名
                            model.setCoverUrl(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[3]/a/img/@src").toString());//模特图片
                            model.setAge(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[1]/td[2]/text()").toString());
                            model.setBirth(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[2]/td[2]/text()").toString());
//                    String statureStr = StringUtils.isNotBlank(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[4]/td[2]/text()").toString())?page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[4]/td[2]/text()").toString():"0";
//                    model.setStature(Integer.valueOf(statureStr));
                            model.setConstellation(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[3]/td[2]/text()").toString());
                            model.setComment(page.getHtml().xpath("//*[@id=\"post\"]/div[5]/div/div[1]/div[2]/p/text()").toString());
                            //这一块采用for循环.添加女的图集id字段，模特特点标签页。 TODO 存在空值匹配不上，错误匹配的事情
                            info = new HashMap<>();
                            for (Selectable item : page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr").nodes()) {
                                // 引用对象放值放不进去
                                String property = item.$("td").nodes().get(0).toString().split(">")[1].split("<")[0];
                                String value = item.$("td").nodes().get(1).toString().split(">")[1].split("<")[0];
                                info.put(property, value);
                                // 这种取元素为null
//                        System.out.println(item.xpath("//tr/td[1]/text()").toString());
                            }
                            model.setAge(info.get("年 龄："));
                            model.setBirth(info.get("生 日："));
                            model.setBirtAddress(info.getOrDefault("出 生：", "0"));
                            model.setBeatyTag(info.getOrDefault("三 围：", "无"));
                            model.setWeight(info.getOrDefault("体 重：", "无"));
                            model.setStature(Integer.parseInt(info.getOrDefault("身 高：", "0")));
                            model.setConstellation(info.getOrDefault("星 座：", "无"));
//                    model.setBirtAddress(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[6]/td[2]/text()").toString());
//                    model.setBeatyTag(page.getHtml().xpath("//*[@id=\"post\"]/div[2]/div/div[4]/table/tbody/tr[5]/td[2]/text()").toString());
                            // 数据库插入模特信息 , item1 为
                            model.setUrl(String.format("https://t1.onvshen.com:85/gallery/%s/%s/cover/0.jpg", model.getModelId(), String.valueOf(item1)));
                            model.setCollectionId(String.valueOf(item1));
                            log.info("模特信息 <{}>", model.toString());
                            modelMapper.insert(model);
                        }
                    }
                }
            } else if (page.getUrl().regex(Model_PIC).match()) {
                // 模特图片详情页面
                if (null != page.getHtml().xpath("//*[@id=\"high\"]/div")) {
                    Integer collection_Id = null;
                    String tag1 = null;
                    for (Selectable item1 : page.getHtml().xpath("//*[@id=\"hgallery\"]/img").nodes()) {
                        // 模特一张一张图片数据插入数据库
                        galleryFeaturesMapper = getBean(GalleryFeaturesMapper.class);
                        url = item1.xpath("img/@src").toString();
                        if (!(galleryFeaturesMapper.checkUrl(url).size() > 0 || galleryFeaturesMapper.checkHotUrl(url).size() > 0)) {

                            galleryFeatures = new GalleryFeatures();
                            galleryFeatures.setTitle(page.getHtml().xpath("//*[@id=\"htilte\"]/text()").toString().trim());
                            galleryFeatures.setComments(page.getHtml().xpath("//*[@id=\"ddesc\"]/text()").toString().trim());
                            galleryFeatures.setPicNum(page.getHtml().xpath("//*[@id=\"dinfo\"]/span/text()").toString().trim());
                            tag = new ArrayList<>();
                            for (Selectable item : page.getHtml().xpath("//*[@id=\"utag\"]/li").nodes()) {
                                // 模特的特性Tag
                                tag.add(item.xpath("li/a/text()").toString());
                            }
                            tag1 = StringUtils.join(tag.toArray(), ",");
                            galleryFeatures.setFeature(tag1);
//                            url = item1.xpath("img/@src").toString();
                            galleryFeatures.setUrl(url);
                            galleryFeatures.setModelId(Integer.parseInt(url.split("/")[4]));
                            collection_Id = Integer.valueOf(url.split(":")[2].split("/")[3]);
                            galleryFeatures.setCollectionId(collection_Id);
                            String aa = page.getHtml().xpath("//*[@id=\"dinfo\"]/text()").toString();

                            SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
                            SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
                            String day = aa.substring(aa.indexOf("在 ") + 2, aa.indexOf(" 创"));
                            galleryFeatures.setDate(sf1.format(sf.parse(day)));
                            String count = aa.substring(aa.indexOf("了 ") + 2, aa.indexOf(" 次"));
                            galleryFeatures.setViewNum(count);
                            log.info("模特的图片链接信息 <{}>", galleryFeatures.toString());
                            // 去重插入 TODO
                            int ret;
                            if (galleryFeatures.getViewNum().compareTo("100000") >= 0) {
                                // 插入热门表
                                ret = galleryFeaturesMapper.insertHotGalleryFeatures(galleryFeatures);
                            } else {
                                ret = galleryFeaturesMapper.insert(galleryFeatures);
                            }
                        }
//                        if(1 == ret){
//                            count.incrementAndGet();
//                            if(count.intValue()%100 == 0){
//                                log.info("插入的图片连接数目达到: <{}> 百.", count.intValue()/100);
//                            }
//                        }
                    }
                    if (!(galleryFeaturesMapper.checkModelAndCollection(collection_Id, tag1).size() > 0)) {
                        // 插入, 模特标签和图集ID
                        galleryFeaturesMapper.insertCollection(collection_Id, Arrays.asList(tag1.split(",")));
                    }
                }
                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"page\"]").links().all());
                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"pages\"]").links().all());
            }
        } catch (Exception e) {
            log.error("出现错误 error <{}> ", e.getMessage());
            e.printStackTrace();
        }
    }

    //主函数执行爬虫
    @Override
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
            .setSleepTime(4000)
            .setTimeOut(6000)
            .setRetryTimes(2)
            .setDomain("nvshens.org")
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
}
