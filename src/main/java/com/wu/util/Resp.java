package com.wu.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author : liyongjie
 * @Date : 2018/9/12 0012
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resp {

    private String msg;
    private Object obj;
    private Boolean success;
    private Object attributes;
    private Integer total;
    private String code;
    private String error;

    public Resp(){
        this.msg = "操作成功！";
        this.attributes = "";
        this.success = true;
        this.total = 0;
        this.code = "200";
        this.obj = "";
    }

    public Resp(String msg, Object obj, Boolean success, Object attributes, Integer total,String code) {
        this.msg = msg;
        this.obj = obj;
        this.success = success;
        this.attributes = attributes;
        this.total = total;
        this.code = code;
    }

    public Resp(String msg,Boolean success,Integer total) {
        this.msg = msg;
        this.success = success;
        this.total = total;
    }

    public Resp(String error,String code){
        this.code = code;
        this.error = error;
    }

    public static Resp get(String msg, Object obj, Boolean success, Object attributes, Integer total){
        Resp resp = new Resp();
        return resp.setObj(obj).setMsg(msg).setAttributes(attributes).setSuccess(success).setTotal(total);
    }

    public static Resp ok(){
        return new Resp();
    }

    public static Resp ok(RespCode respCode){
        Resp resp = new Resp();
        resp.setCode(respCode.code);
        resp.setMsg(respCode.msg);
        return resp;
    }

    public static Resp ok(Object obj,Integer total){
        Resp resp = new Resp();
        return resp.setTotal(total).setObj(obj);
    }

    public static Resp ok(String msg){
        Resp resp = new Resp();
        return resp.setMsg(msg);
    }

    public static Resp error(String error,String code){
        return new Resp(error,code);
    }

    public static Resp error(RespCode respCode){
        return new Resp(respCode.msg,respCode.code);
    }
}
