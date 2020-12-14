package com.tony.imagelink.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @ClassName csdnSpider
 * Description TODO
 * @Author hzf
 * @Date 2020/12/13 12:15
 */
public class csdnSpider implements PageProcessor {
    public static final String URL_LIST = "https://blog\\.csdn\\.net/qq_41061437/article/list/\\d";

    public static final String URL_POST = "https://blog\\.csdn\\.net/qq_41061437/article/details/\\d{1,}";
    private Site site = Site
            .me()
            .setDomain("blog.csdn.net")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[2]").links().regex(URL_POST).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            //文章页
        } else {
            page.putField("title", page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1"));
            page.putField("content", page.getHtml().xpath("//*[@id=\"article_content\"]"));

        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new csdnSpider()).addUrl("https://blog.csdn.net/qq_41061437/article/list/1")
                .run();
    }

}
