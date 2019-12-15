package com.practice.ioc.bean;


import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @Classname BeanDefinition
 * @Description 核心数据结构，用于描述我们需要IOC管理的对象
 * @Date 19-12-15 上午11:05
 * @Created by xns
 */
@Data
@ToString
public class BeanDefinition {

    private String name;

    private String className;

    private String interfaceName;

    /**
     * 构造函数的传参列表
     */
    private List<ConstructorArg> constructorArgs;

    /**
     * 需要注入的参数列表
     */
    private List<PropertyArg> propertyArgs;
}
