package com.practice.ioc.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @Classname BeanUtils
 * @Description 处理对象的实例化
 * @Date 19-12-15 上午11:23
 * @Created by xns
 */
public class BeanUtils {
    public static <T> T instanceByCglib(Class<T> clz, Constructor ctr,Object[] args){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(NoOp.INSTANCE);
        if(ctr == null){
            return (T)enhancer.create();
        }else{
            return (T)enhancer.create(ctr.getParameterTypes(),args);
        }
    }
}
