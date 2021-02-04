package com.tony.imagelink.ret;

/**
 * @ClassName BasicException
 * Description TODO
 * @Author hzf
 * @Date 2021/2/4 18:54
 */
public class BasicException extends  Exception{


    protected int code = -1;

    private static final long serialVersionUID = 1L;

    protected ExceptionConstants exEnum = ExceptionConstants.DEFAULT_CODE;

    /**
     *
     */
    public BasicException(){}

    /**
     * @param message String 错误信息
     */
    public BasicException(final String message){
        super(ExceptionConstants.DEFAULT_CODE.message + ":" + message);
    }

    /**
     * @param code int  错误码
     * @param message Stirng 错误信息
     * */
    public BasicException(int code, final String message){
        super(ExceptionConstants.DEFAULT_CODE.message + ":" + message);
        this.code = code;
    }

    /**
     * @param exEnum 异常枚举
     */
    public BasicException(ExceptionConstants exEnum){
        super(exEnum.message);
        this.code = exEnum.code;
        this.exEnum = exEnum;
    }

    /**
     * @return int 错误码
     */
    public int getCode() {
        return code;
    }

    /**
     * @return {@linkplain ExceptionConstants ExceptionConstants}
     */
    public ExceptionConstants getExEnum() {
        return exEnum;
    }

    /**
     * @param exEnum
     */
    public void setExEnum(ExceptionConstants exEnum) {
        this.exEnum = exEnum;
    }

}
