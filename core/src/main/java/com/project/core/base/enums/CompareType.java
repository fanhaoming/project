package com.project.core.base.enums;

import com.project.core.base.BaseEnum;

public enum CompareType implements BaseEnum{
    EQUAL(1),
    NOT_EQUAL(2),
    LIKE(3),
    LEFT_LIKE(4),
    RIGHT_LIKE(5),
    GREATER_EQUAL(6),
    LESS_EQUAL(7),
    GREATER(8),
    LESS(9),
    IN(10),
    NONE(11),
    NOT_NONE(12)
    ;

    private int value;

    private CompareType(int value){
        this.value = value;
    }
}
