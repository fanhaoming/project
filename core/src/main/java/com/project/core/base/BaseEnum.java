package com.project.core.base;

import com.fasterxml.jackson.annotation.JsonValue;
import com.project.core.common.GetField;

import java.lang.reflect.Field;

/**
 * @ClassName BaseEnum
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/6/12  10:12
 * @Version 1.0
 **/
public interface BaseEnum {

    @JsonValue
    default int getValue() {
        Field field = GetField.getInstance().getByName(this.getClass(), "value");

        if (field == null)
            return 0;

        try {
            return (int)field.get(this);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }
}
