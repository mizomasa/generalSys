package com.general.demo.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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

    public static <T,K> List<T> copyList(List<K> sources, Class<T> type){
        List<T> list = new ArrayList<T>();
        sources.forEach(e->list.add(copy(e,type)));
        return list;
    }
}
