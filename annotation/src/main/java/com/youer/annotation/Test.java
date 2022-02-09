package com.youer.annotation;

import java.lang.reflect.Field;

/**
 * @author youer
 * @date 2022/2/9
 */
public class Test {
    @Range(min = 5, max = 50)
    public int num;

    public void checkNum() throws IllegalArgumentException, ReflectiveOperationException {
        for (Field field : getClass().getFields()) {
            // 获取到对应的注解类
            Range range = field.getAnnotation(Range.class);
            // 判断field是否用了range注解
            if (range != null) {
                Object value = field.get(this);
                if (value instanceof Integer) {
                    int v = (int)value;
                    // 判断值是否满足@Range的min/max:
                    if (v < range.min() || v > range.max()) {
                        throw new IllegalArgumentException("Invalid field: " + field.getName());
                    }
                }
            }
        }
    }
} 