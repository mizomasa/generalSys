package com.general.demo.util;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

public class Utils {

    public static <T,K>  T copy(K m, Class<T> type)  {
        if(m==null) return null;
        T model;
        try {
            model = type.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            return null;
        }
        BeanUtils.copyProperties(m,model);
        return model;
    }
}
