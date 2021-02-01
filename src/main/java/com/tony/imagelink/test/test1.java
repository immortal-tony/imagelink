package com.tony.imagelink.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName test1
 * Description TODO
 * @Author hzf
 * @Date 2020/12/13 17:21
 */
public class test1 {
    public static void main(String[] args) throws ParseException {
        String a = "a,s,df,你，发送，注";
        String[] b = a.split(",|，|\\s+", 1);
        System.out.println(b.toString());
        int c = 34567;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        String aa = "该图集包含，在 2020/12/2 创建，被浏览了 92754 次";
        String day = aa.substring(aa.indexOf("在 ")+2, aa.indexOf(" 创"));
        System.out.println(sf1.format(sf.parse(day)));
        int count = Integer.parseInt(aa.substring(aa.indexOf("了 ")+2, aa.indexOf(" 次")));
        System.out.println(count);
        if("100001".compareTo("100000")>0){
            System.out.println("大于");
        }
        List<Integer> bb = new ArrayList<>();
        bb.add(1);
        bb.add(2);
        bb.add(3);
        bb.add(4);
        List<Integer> bb1 = new LinkedList<>();
        bb1.add(3);
        bb1.add(4);
        bb1.add(5);
        bb.removeAll(bb1);
        System.out.println(bb);

        System.out.println(String.format("https://www.nvshens.org/g/%s/",String.valueOf(89934)));

    }

}
