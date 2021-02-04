package com.tony.imagelink.ret;

/**
 * @ClassName Description TODO
 * @Author hzf
 * @Date 2021/2/4 18:43
 */
public class CommonReturn extends BaseEntity{

    protected int code = 0;

    protected String message = "\u64cd\u4f5c\u6210\u529f";// 操作成功

    /**
     * 无参构造函数
     */
    public CommonReturn() {
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

    public CommonReturn(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public CommonReturn(BasicException e) {
        super();
        this.message = e.getMessage();
        this.code = e.getCode();
    }

    public CommonReturn(ExceptionConstants ec) {
        super();
        this.code = ec.code;
        this.message = ec.message;
    }

}