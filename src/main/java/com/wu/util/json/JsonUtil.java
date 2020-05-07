package com.wu.util.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wu.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

/**
 * Json工具类
 *
 * @author z-yc
 * @date 2017-07-10 10:58:18
 */
public class JsonUtil {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

	static {
		// 设置返回日期格式
		MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		MAPPER.setDateFormat(new SimpleDateFormat(DateUtil.PATTERN_DEFAULT));
	}

	/**
	 * Jackson 空值处理
	 * 
	 * @param value
	 * @return
	 */
	public static String getJsonValue(Object value) {

		try {

			return MAPPER.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("Jackson write error: ", e);
		}

		return JSONObject.toJSONString(value);
	}
	
	/**
	 * fastjson 不返回空字段
	 * 
	 * com.alibaba.fastjson.JSONObject
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj) {
		return JSONObject.toJSONString(obj, SerializerFeature.WriteNullBooleanAsFalse);
	}

}
