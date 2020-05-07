package com.wu.exception;


import com.wu.util.RespCode;

/**
 * @author zps
 * @date 2018/9/10
 */
public class ResultReturnException extends RuntimeException {
    private static final long serialVersionUID = 1L;

//    private String info = FwWebError.COMMON_ERROR.info;
//    private int statusCode = FwWebError.COMMON_ERROR.statusCode;

    private String info = RespCode.BADREQUEST.msg;
    private String statusCode = RespCode.BADREQUEST.code;

//    public ResultReturnException(FwWebError fwWebError) {
//        super(fwWebError.info);
//        this.info = fwWebError.info;
//        this.statusCode = fwWebError.statusCode;
//    }

    public ResultReturnException(RespCode respCode){
        super(respCode.msg);
        this.info = respCode.msg;
        this.statusCode = respCode.code;
    }

    public ResultReturnException(String info) {
        super(info);
        this.info = info;
    }

    public ResultReturnException(String info, Throwable e) {
        super(info, e);
        this.info = info;
    }

    @Deprecated
    public ResultReturnException(String msg, String code) {
        super(msg);
        this.info = msg;
        this.statusCode = code;
    }

    @Deprecated
    public ResultReturnException(String msg, String code, Throwable e) {
        super(msg, e);
        this.info = msg;
        this.statusCode = code;
    }

    public String getInfo() {
        return info;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
