package com.project.core.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018/5/10.
 */
@Getter
@Setter
public class JsonData {
    //返回状态
    private boolean ret;

    private String msg;

    private Object data;

    public JsonData(boolean ret){
        this.ret = ret;
    }
    public static JsonData success(Object object){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    public static JsonData success(){
        JsonData jsonData = new JsonData(true);
        return jsonData;
    }

    public static JsonData fail(String msg){
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }
}
