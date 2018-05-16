package com.project.core.base;

import java.util.List;

public interface BaseService <T extends BaseDomain, DaoT extends BaseDao<T>>{
    void delete(String var1);

    T delete(T var1);

    T save(T var1);

    T saveWithId(T var1);

    List<T> saves(List<T> var1);

    T get(String var1);

    T update(T var1);

    T updateAll(T var1);

    T edit(T var1, T var2);

    List<T> listByIds(List<String> var1);

    List<T> listAll();
}
