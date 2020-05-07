package com.wu.util;

/**
 * @Author : liyongjie
 * @Date : 2018/9/13 0013
 */
public enum RespCode {

    ////
    GET_OK("200","查询成功！"),
    CREATE_OK("201","添加成功!"),
    UPDATE_OK("201","更新成功！"),
    DELETE_OK("204","删除数据成功！"),

    BADREQUEST("400","提交参数不合法"),
    UNAUTHORIZED("401","未登录"),
    NO_PERSSIOM("403","没有权限访问"),
    NOT_FOUND("404","资源不存在"),
    INTERNAL_SERVER_ERROR("500","服务器端错误，请联系管理员！");

    public final String code;
    public final String msg;

    RespCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
