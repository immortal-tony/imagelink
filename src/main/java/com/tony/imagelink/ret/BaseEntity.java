package com.tony.imagelink.ret;

import com.google.gson.Gson;

/**
 * @ClassName Description TODO
 * @Author hzf
 * @Date 2021/2/4 18:43
 */
public class BaseEntity {
    /**
     * 默认以json格式打印
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
