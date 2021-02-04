package com.tony.imagelink.ret;

/**
 * @ClassName Description TODO
 * @Author hzf
 * @Date 2021/2/4 18:47
 */
public enum CodeEnum {
    /* 成功 */

    SUCCESS_CODE(0,"success"), //操作成功
    FAILURE_CODE(1,"fail"),  ////操作失败
    CACHE_FAILURE_CODE(2,"无数据或未生效，请10分钟后再重试"),
    INTERNAL_SERVER_ERROR(20100,"INTERNAL_SERVER_ERROR"),//服务器内部错误
    INVALID_REQUEST_PARAMS(20101,"INVALID_REQUEST_PARAMS"),//请求参数错误
    BAD_REQUEST(400,"请求参数有误"), //具体参数由每个接口进行详细填充
    CONFLICT_REQUEST(409,"当前请求资源的状态存在冲突"), //操作不允许


    /* 失败 */
    SYSTEM_ERROR_CODE(-1,"系统异常"), //操作失败
    SYSTEM_DEFEND(-2,"系统维护中"), //操作失败
    OTHER_ERROR_CODE(112,"other error"), //其它错误
    AUTOTIME_ERROR(701,"autoTime error"),

    PARAMS_LACK(222, "参数缺失"), //缺少公共必传参数或者业务必传参数
    PARAMS_ILLEGAL(223, "param illegal");  //参数内容不合法，反垃圾不通过或者不符合规则


    private int value;
    private String message;
    CodeEnum(int value, String message){
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
