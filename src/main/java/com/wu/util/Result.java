package com.wu.util;



import com.wu.exception.FwWebError;

import java.util.HashMap;

/**
 * @author zps
 * @date 2018/9/10
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private static final Integer SUCCESS_CODE = 2000;
    private static final String SUCCESS_INFO = "Success!";
    //    private static final Integer ERROR_CODE = 6000;
    //    private static final String ERROR_INFO = "Fail！";

    public Result() {
        put("statusCode", SUCCESS_CODE);
        put("info", SUCCESS_INFO);
    }

    public Result(Object data) {
        put("statusCode", SUCCESS_CODE);
        put("info", SUCCESS_INFO);
        put("data", data);
    }

    @Deprecated
    public Result(int code, String msg, Object data) {
        put("statusCode", code);
        put("info", msg);
        put("data", data);
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result error() {
        return error(FwWebError.COMMON_ERROR);
    }

    public static Result error(String info) {
        Result result = error(FwWebError.COMMON_ERROR);
        result.put("info", info);
        return result;
    }

    public static Result error(FwWebError fwWebError) {
        Result result = new Result();
        result.put("statusCode", fwWebError.statusCode);
        result.put("info", fwWebError.info);
        return result;
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("statusCode", code);
        result.put("info", msg);
        return result;
    }

  /*
  public static Result error() {
      return error(ERROR_CODE, ERROR_INFO);
  }

  public static Result error(String msg) {
      return error(ERROR_CODE, msg);
  }

  public static Result error(int code, String msg) {
      Result result = new Result();
      result.put("statusCode", code);
      result.put("info", msg);
      return result;
  }



  public static Result ok(String msg) {
      Result result = new Result();
      result.put("info", msg);
      return result;
  }

  public static Result ok(Map<String, Object> map) {
      Result result = new Result();
      result.putAll(map);
      return result;
  }*/

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
