package com.project.core.base.page;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;


public enum ConditionTypeEnum {
    EQUALS(1),
    LIKE(2);
    int code;
    private ConditionTypeEnum(int code){
        this.code = code;
    }
    @JsonCreator
    public static ConditionTypeEnum getItem(int code){
        for(ConditionTypeEnum item : values()){
            if(item.getCode() == code){
                return item;
            }
        }
        return null;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
