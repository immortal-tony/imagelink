package com.tony.imagelink.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @ClassName JobProcessor
 * Description TODO
 * @Author hzf
 * @Date 2020/12/7 20:52
 */
public class JobProcessor implements PageProcessor {

    //解析页面
    @Override
    public void process(Page page) {
        //解析返回的数据page，并且把解析的结果放到ResultItems中
        //css选择器
        page.putField("div1",page.getHtml().css("h2.logo_subtit").all());

        //XPath
        page.putField("div2",page.getHtml().xpath("//div[@id='settleup']/div/a"));

        //正则表达式
        page.putField("div3",page.getHtml().css("div#J_cate a").regex(".*家.*").all());

        //处理结果API
        page.putField("div4",page.getHtml().css("div#J_cate a").regex(".*家.*").get());
        page.putField("div5",page.getHtml().css("div#J_cate a").regex(".*家.*").toString());

        //获取链接
        page.addTargetRequests(page.getHtml().css("div#J_cate").links().all());
        page.putField("url",page.getHtml().css("div#channel").all());
    }

    private Site site = Site.me();

    public Site getSite() {
        return site;
    }

    //主函数执行爬虫
    public static void main(String[] args) {
        Spider.create(new JobProcessor())
                .addUrl("https://www.jd.com/")   //设置爬去数据的页面
                .addPipeline(new FilePipeline("/home/zzdreamz/Desktop/test"))	//指定数据输出位置，注释该行就会输出到控制台
                .thread(10)     //设置线程个数来执行程序
                .run();     //执行爬虫
    }
}
