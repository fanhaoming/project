package com.project.core.base;

import com.project.core.base.page.Pager;

import java.util.List;

/**
 * @ClassName BaseDao
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/5/14  17:07
 * @Version 1.0
 **/
public interface BaseDao<T> {
    int delete(String var1);

    int save(T var1);

    int saveWithId(T var1);

    void saves(List<T> var1);

    T get(String var1);

    int update(T var1);

    int updateAll(T var1);

    List<T> listAll(Pager pager);

    List<T> listByIds(List<String> var1);


}
