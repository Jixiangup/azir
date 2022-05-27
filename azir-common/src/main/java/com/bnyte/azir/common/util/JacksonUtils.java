package com.bnyte.azir.common.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bnyte
 * @since 2020-01-13 23:12
 **/
public class JacksonUtils {

    private static final Logger log = LoggerFactory.getLogger(JacksonUtils.class);

    /**
     * 初始化mapper对象，jackson需要
     */
    private  static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    /**
     * 将Java对象转换为json字符串
     * @param data 需要被转换的对象
     * @return 返回转换之后的json字符串，如果对象为空则此时直接返回null
     */
    public static String toJSONString(Object data) {
        if (data == null) return null;
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static  <T> T parse(String json, Class<T> target) {
        try {
            return mapper.readValue(json, target);
        } catch (JsonProcessingException e) {
            log.error("[{}] parse error for {}", JacksonUtils.class, e.getMessage());
        }
        return null;
    }



}
