package com.project.core.base;

import com.project.core.base.page.ConditionTypeEnum;
import com.project.core.json.JsonMapper;
import org.codehaus.jackson.type.TypeReference;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName test
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/5/25  14:29
 * @Version 1.0
 **/
public class test {
    public static void main(String[] args){
        Map<ConditionTypeEnum,String> map = new HashMap<>();
        map.put(ConditionTypeEnum.EQUALS,"fanhaoming");
        map.put(ConditionTypeEnum.LIKE,"男");
        String str = JsonMapper.obj2String(map);
        Map<ConditionTypeEnum,String> o = JsonMapper.string2Obj(str, Map.class);
        System.out.println(str);
        System.out.println(o);
    }
}
