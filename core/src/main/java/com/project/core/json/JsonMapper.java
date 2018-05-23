package com.project.core.json;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * @ClassName JsonMapper
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/5/23  17:20
 * @Version 1.0
 **/
@Slf4j
public class JsonMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();


    static {
        //在反序列化的时候如果json数据中出现实体没有的字段，则忽略不报错
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

        //遇到空对象则忽略不报错
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
        //
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));

        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);

    }

    public static <T> String obj2String(T t){
        if(t == null){
            return null;
        }
        try{
            return t instanceof String ?(String)t:objectMapper.writeValueAsString(t);
        }catch (Exception e){
            log.warn("parse object to String exception, error:{}", e);
            return null;
        }

    }


    public static <T> T string2Obj(String src, TypeReference<T> typeReference) {
        if (src == null || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src, typeReference));
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", src, typeReference.getType(), e);
            return null;
        }
    }
}
