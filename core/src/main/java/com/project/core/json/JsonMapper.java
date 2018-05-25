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

    /**
    * @Author fhm
    * @Description  对象转换成Json字符串
    * @Date 2018/5/24  9:07
    * @Param [t]
    * @Return java.lang.String
    **/
    public static <T> String obj2String(T t){
        if(t == null){
            return null;
        }
        try{
            return t instanceof String ?(String)t:objectMapper.writeValueAsString(t);
        }catch (Exception e){
            log.warn("{} 转换成JSON字符串时发生错误, 错误信息:{}",t.getClass().getName(), e);
            return null;
        }

    }


    /**
    * @Author fhm
    * @Description JSON字符串转换成对象
    * @Date 2018/5/24  9:08
    * @Param [src, typeReference]
    * @Return T
    **/
    public static <T> T string2Obj(String src, Class<T> clazz) {
        if (src == null || clazz == null) {
            return null;
        }
        try {
            return objectMapper.readValue(src, clazz);
        } catch (Exception e) {
            log.warn("parse String to Object exception, String:{}, Class<T>:{}, error:{}", src, clazz.getName(), e);
            return null;
        }
    }
}
