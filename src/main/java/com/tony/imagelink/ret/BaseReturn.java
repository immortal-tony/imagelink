package com.tony.imagelink.ret;


/**
 * @ClassName Description TODO
 * @Author hzf
 * @Date 2021/2/4 18:44
 */
public class BaseReturn<Integer, T> extends BaseEntity {
    private static final long serialVersionUID = -5511012338032458427L;
    private int code = 0;

    private String message;

    private Object ret;

    public BaseReturn(int code, Object ret) {
        this.code = code;
        this.ret = ret;
    }

    public Object getRet() {
        return ret;
    }

    public void setRet(T ret) {
        this.ret = ret;
    }

    public BaseReturn() {
    }

    public BaseReturn(int code) {
        this.code = code;
    }

    public BaseReturn(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseReturn(CodeEnum codeEnum) {
        this.code = codeEnum.getValue();
        this.message = codeEnum.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
