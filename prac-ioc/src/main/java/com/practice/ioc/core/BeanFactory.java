package com.practice.ioc.core;

/**
 * @Classname BeanFactory
 * @Description TODO
 * @Date 19-12-15 上午11:22
 * @Created by xns
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
