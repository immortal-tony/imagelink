package com.tony.imagelink.other;

import org.apache.commons.io.IOUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.hibernate.result.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName Learning
 * Description TODO
 * @Author hzf
 * @Date 2020/12/9 14:23
 */
public class Learning {

    private static Logger log = LoggerFactory.getLogger(Learning.class);

    private void tec() {
        // 转义用法
        String htmlContent = "你好";
        htmlContent = StringEscapeUtils.unescapeHtml4(htmlContent);

        byte[] pdfByte = null;
        HttpURLConnection conn = null;
        int statusCode = 0;
        InputStream is = null;

//        copyPropertiesToBean，int到Integer的值之间的赋值是过不了的

        // 移除失效的session
//        securityManager.get.getSessionDAO().delete(SecurityUtils.getSubject().getSession());
        // 移除线程里面的subject
//        ThreadContext.remove(ThreadContext.SUBJECT_KEY);

//        流的优雅关闭的一种简写。Apache的相关内容
        IOUtils.closeQuietly();

        String newLine = System.getProperty("line.separator");
        newLine.getBytes();

//        javac -encoding utf-8 Output.java.  编译指定编码
//        自己写的getter，setter优先于其他。
        try {
            is = conn.getInputStream();
            //3. 根据输入流中的字节数创建byte数组；
            pdfByte = readInputStream(is);
            //4.把数据读取到数组中；
            is.read(pdfByte);
        } catch (Exception e) {
            log.error("");
        }
    }

    public static void main(String[] args) {

        Learning learning = new Learning();
//        learning.tec();
//        learning.tec1();

//        Integer与Integer相互比较，数据在-128-127范围内，就会从缓存中拿去数据，
//        比较就相等；如果不在这个范围，就会直接新创建一个Integer对象，使用 == 判断
//        的是两个内存的应用地址，所以自然不相等。  int和Integer 自动拆箱，int存在栈

//        byte,short,int,long,float,double,boolean,char 基本类型存于栈。
        int a1 = 1000;
        Integer b1 = 1000;
        if(a1 == b1 ){
            System.out.println("a1等于b1");
        }else{
            System.out.println("a1不等于b1");
        }
    }

    private void tec1() {
        ConcurrentLinkedQueue<String> con = new ConcurrentLinkedQueue<>();
        for(int i = 0;i<=200;i++){
            con.add("你好" + i);
        }
        new ArrayList<>().subList(0, 10);// 截取原列表不能动
    }


    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}
