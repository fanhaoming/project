package com.project.core.base;

/**
 *
 * Created by Administrator on 2018/5/13.
 */
public interface BaseMapper{
    <T> T save(T data);

    <T> T update(T data);


}
