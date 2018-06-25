package com.project.core.base.page;

import com.project.core.base.enums.CompareType;

/**
 * @ClassName SearchCondition
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/6/7  9:35
 * @Version 1.0
 **/
public class SearchCondition {
    private String columnName;

    private String compareType;

    private Object value;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getCompareType() {
        return compareType;
    }

    public void setCompareType(String compareType) {
        this.compareType = compareType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
