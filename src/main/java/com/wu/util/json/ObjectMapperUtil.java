package com.wu.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

/**
 * @author zps
 * @date 2018/9/10
 */
public class ObjectMapperUtil {
    private static final Log log = LogFactory.getLog(ObjectMapperUtil.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T fromString(String content, Class<T> valueType) throws IOException {
        return MAPPER.readValue(content, valueType);
    }

    public static String toString(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
