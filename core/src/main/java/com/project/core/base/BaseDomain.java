package com.project.core.base;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/13.
 */
public class BaseDomain implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
