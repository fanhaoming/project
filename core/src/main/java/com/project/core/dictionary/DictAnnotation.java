package com.project.core.dictionary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @ClassName DictAnnotation
 * @Description TODO 字典字段注解
 * @Author fanhaoming
 * @Date 2018/6/4  9:25
 * @Version 1.0
 **/
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DictAnnotation {
    String code() default "";
}
