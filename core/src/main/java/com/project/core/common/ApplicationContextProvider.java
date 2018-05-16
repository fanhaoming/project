package com.project.core.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
* @Author 范灏铭
* @Description 
* @Date 2018/5/11 14:42
**/
public class ApplicationContextProvider implements ApplicationContextAware{

    public static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
    * @Description 获取Spring容器中的实体
    * @Date 2018/5/11 14:43
    * @Param [clazz] 需要获取的类
    * @Return T 返回的类实例
    **/
    public static <T> T popBean(Class<T> clazz){
        if(context == null){
            return null;
        }
        return context.getBean(clazz);
    }


    /**
    * @Author Administrator
    * @Description
    * @Date 2018/5/11 14:44
    * @Param [name, clazz] Spring容器中的类名，类
    * @Return T Spring容器中的类实例
    **/
    public static <T> T popBean(String name,Class<T> clazz){
        if(context == null){
            return null;
        }
        return context.getBean(name,clazz);
    }
}
