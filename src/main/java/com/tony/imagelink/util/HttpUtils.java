package com.tony.imagelink.util;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @ClassName HttpUtils
 * Description TODO
 * @Author hzf
 * @Date 2020/12/7 12:05
 */
@Component
public class HttpUtils {

    //使用连接池管理
    private PoolingHttpClientConnectionManager cm;

    // 设置请求下载的地址
    private String url = "https://www.nvshens.org/";

    //初始化连接池
    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        this.cm.setMaxTotal(100);
        //设置每个主机的最大连接数
        this.cm.setDefaultMaxPerRoute(10);
    }

    //根据请求地址下载页面数据
    public String doGetHtml(String url) {

        //获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        //创建HttpGet请求对象，设置url地址
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(getConfig());

//        httpGet.setHeader("Cookie", "自己的实际值");
        httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("Accept-Charset", "utf-8");
        httpGet.setHeader("Accept-Language", "en-US,en");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
        //使用HttpClient发起请求，获取响应
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 304) {
                //判断响应体Entity是否不为空，如果不为空就可以使用EntityUtils
                if (response.getEntity() != null) {
                    String content = EntityUtils.toString(response.getEntity());
                    return content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //返回空串
        return "";
    }


    /**
     * 下载图片
     * @param url
     * @return
     */
    public String doGetImage(String url) {
        //获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.getConfig());
        //设置请求头
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");

        //使用HttpClient发起请求，获取响应
        CloseableHttpResponse response = null;
        String userName = "未知";
        String pathName = "/imagelink/picture/";
        String picName;
        File file;
        try {
            response = httpClient.execute(httpGet);
            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                //判断响应体Entity是否不为空，如果不为空就可以使用EntityUtils
                if (response.getEntity() != null) {
                    //下载图片
                    String extName = url.substring(url.lastIndexOf("."));
                    //创建图片名，重命名图片 TODO 那个专栏的图片放在UUID的地方 ,匹配汉子
                    while(true) {
                        picName = userName + "_" + System.currentTimeMillis() + extName;
                        //下载图片
                        file = new File(pathName + picName);
                        FileUtils.ensureDirectory(pathName);
                        if (file.exists()) {
                            OutputStream outputStream = new FileOutputStream(file);
                            response.getEntity().writeTo(outputStream);
                            return picName;
                        }
                        Thread.sleep(0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //返回空串
        return "";
    }


    /**
     * 配置请求的信息
     */
    private RequestConfig getConfig() {
        //设置链接响应时长,数据传输时长，链接存在时长
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
                .setSocketTimeout(5000).build();
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000)    //创建链接的最长时间
                .setConnectionRequestTimeout(500)   //获取连接的最长时间
                .setSocketTimeout(10000)    //数据传输的最长时间
                .build();

        return config;
    }
}