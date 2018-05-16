package com.project.core.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseService
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/5/14  17:08
 * @Version 1.0
 **/
public class BaseServiceImpl<T extends BaseDomain, BaseDaoT extends BaseDao<T>> implements BaseService<T, BaseDaoT>  {
    @Autowired
    BaseDao<T> baseDao;


    public BaseServiceImpl() {
    }

    public T save(T t) {
        this.baseDao.save(t);
        return t;
    }

    public T update(T t) {
        this.baseDao.update(t);
        return t;
    }

    public T get(String id) {
        return (T) this.baseDao.get(id);
    }

    public void delete(String id) {
        this.baseDao.delete(id);
    }

    public List<T> listByIds(List<String> ids) {
        return (List)(ids != null && ids.size() != 0 ? this.baseDao.listByIds(ids) : new ArrayList());
    }


    public T edit(T edit, T update) {
        this.baseDao.update(edit);
        return edit;
    }

    public T delete(T t) {
        this.baseDao.delete(t.getId());
        return t;
    }

    public T updateAll(T t) {
        this.baseDao.updateAll(t);
        return t;
    }

    public List<T> listAll() {
        return this.baseDao.listAll();
    }

    public List<T> saves(List<T> list) {
        this.baseDao.saves(list);
        return list;
    }


    public T saveWithId(T t) {
        this.baseDao.saveWithId(t);
        return t;
    }
}
