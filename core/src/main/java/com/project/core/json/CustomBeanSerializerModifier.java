package com.project.core.json;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.project.core.dictionary.DictAnnotation;
import com.project.core.dictionary.DictJsonSerializer;
import com.project.core.dictionary.DictJsonSerializerInstance;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName CustomBeanSerializerModifier
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/5/31  17:36
 * @Version 1.0
 **/
public class CustomBeanSerializerModifier extends BeanSerializerModifier {
    private JsonSerializer<Object> stringNullJsonSerializer = new StringNullJsonSerializer();
    private JsonSerializer<Object> arrayNullJsonSerializer = new ArrayNullJsonSerializer();

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        Iterator<BeanPropertyWriter> iterator = beanProperties.iterator();

        while(iterator.hasNext()){
            BeanPropertyWriter writer = iterator.next();
            // 判断字段的类型，如果是array，list，set则注册nullSerializer
            if (isStringType(writer)) {
                // 给writer注册一个自己的nullSerializer
                writer.assignNullSerializer(this.defaultNullStringJsonSerializer());
            }else if(isArrayType(writer)){
                writer.assignNullSerializer(this.defaultNullArrayJsonSerializer());
            }
            //获取字段的数据字典注解
            DictAnnotation annotation = writer.getAnnotation(DictAnnotation.class);
            if (annotation != null) {
                DictJsonSerializer jsonSerializer = DictJsonSerializerInstance.getInstance().getByAnnoation(annotation);
                writer.assignSerializer(jsonSerializer);
            }
        }
        return super.changeProperties(config, beanDesc, beanProperties);
    }

    protected boolean isStringType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(String.class);
    }

    protected boolean isArrayType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Set.class) || clazz.equals(List.class) || clazz.isArray();
    }

    protected JsonSerializer<Object> defaultNullStringJsonSerializer() {
        return stringNullJsonSerializer;
    }
    protected JsonSerializer<Object> defaultNullArrayJsonSerializer() {
        return arrayNullJsonSerializer;
    }
}
