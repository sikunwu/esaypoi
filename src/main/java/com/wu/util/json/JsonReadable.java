package com.wu.util.json;

/**
 * @author zps
 * @date 2018/9/10重写各类Model的toString进行格式化
 */
public class JsonReadable {

    @Override
    public String toString() {
        return ObjectMapperUtil.toString(this);
    }
}
