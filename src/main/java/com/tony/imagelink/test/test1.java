package com.tony.imagelink.test;

/**
 * @ClassName test1
 * Description TODO
 * @Author hzf
 * @Date 2020/12/13 17:21
 */
public class test1 {
    public static void main(String[] args) {
        String a = "a,s,df,你，发送，注";
        String[] b = a.split(",|，|\\s+", 1);
        System.out.println(b.toString());
        int c = 34567;

    }

}
