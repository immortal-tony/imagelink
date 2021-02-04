package com.tony.imagelink.ret;

/**
 * @ClassName Description TODO
 * @Author hzf
 * @Date 2021/2/4 18:45
 */
public enum ExceptionConstants {

    DEFAULT_CODE(1000, "系统错误"),
    PARAM_INVALID_ERROR(1001, "参数异常"),
    NOT_ENOUGHT_PARAM_ERROR(1002, "所需参数不足"),
    HTTP_CALL_ERROR(1003, "调用远程接口失败"),

    GET_USER_INFO_ERROR(2000, "获取用户信息失败");

    /**
     * 错误码
     */
    public int code;

    /**
     * 错误信息
     */
    public String message;

    ExceptionConstants(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return this.code + ":" + this.message;
    }
}

