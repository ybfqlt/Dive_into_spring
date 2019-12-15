package com.practice.ioc.utils;

import java.lang.reflect.Field;

/**
 * @Classname ReflectionUtils
 * @Description TODO
 * @Date 19-12-15 上午11:24
 * @Created by xns
 */
public class ReflectionUtils {
    public static void injectFiled(Field filed, Object obj, Object value) throws IllegalAccessException{
        if(filed != null){
            filed.setAccessible(true);
            filed.set(obj,value);
        }
    }
}
