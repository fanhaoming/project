package com.project.core.json;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/10.
 */
@Getter
@Setter
public class JsonData {
    //返回状态
    private boolean status;

    private String msg;

    private Object data;

    public JsonData(boolean status){
        this.status = status;
    }

    public static JsonData success(){
        return new JsonData(true);
    }


    public static JsonData success(Object object){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    public static JsonData success(Object object,String msg){
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    public static JsonData fail(String msg){
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String,Object> toMap(){
        HashMap map = new HashMap();
        map.put("status",status);
        map.put("data",data);
        map.put("msg",msg);
        return map;
    }
}
