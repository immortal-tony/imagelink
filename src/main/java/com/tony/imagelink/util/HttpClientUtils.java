package com.tony.imagelink.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName HttpClientUtils
 * Description TODO
 * @Author hzf
 * @Date 2020/12/7 14:53
 */
public class HttpClientUtils {
    private static Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * post默认超时时间为3秒
     *
     * @param surl
     * @return
     * @throws java.io.IOException
     */
    public static String post(String surl) {
        try {
            URL url = new URL(surl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setConnectTimeout(10000);
            urlCon.setReadTimeout(30000);
            BufferedReader Read = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            StringBuffer line = new StringBuffer();
            String result = null;
            while ((result = Read.readLine()) != null) {
                line.append(result);
            }
            result = line.toString();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String sendHttpPostJson(String url, String body) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setEntity(new StringEntity(body));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode() + "\n");
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
        //System.out.println(responseContent);

        response.close();
        httpClient.close();
        return responseContent;
    }


    public static String get(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
