package com.project.core.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName JsonObjectMapper
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/6/4  8:50
 * @Version 1.0
 **/
public class JsonObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = -189284858226396323L;

    public JsonObjectMapper() {
        super();
        // 空值处理为空串
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.setSerializerFactory(this.getSerializerFactory().withSerializerModifier(new CustomBeanSerializerModifier()));
    }
}
