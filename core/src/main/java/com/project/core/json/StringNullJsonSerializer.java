package com.project.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @ClassName StringNullJsonSerializer
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/6/4  9:25
 * @Version 1.0
 **/

public class StringNullJsonSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // TODO Auto-generated method stub
        if (value == null) {
            gen.writeString("");
        } else {
            gen.writeObject(value);
        }


    }

}