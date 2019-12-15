package com.practice.ioc.utils;

/**
 * @Classname ClassUtils
 * @Description Java类的加载
 * @Date 19-12-15 上午11:23
 * @Created by xns
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoder(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class loadClass(String className){
        try{
            return getDefaultClassLoder().loadClass(className);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
