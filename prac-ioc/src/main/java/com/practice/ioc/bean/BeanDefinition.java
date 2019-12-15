package com.practice.ioc.bean;


import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @Classname BeanDefinition
 * @Description TODO
 * @Date 19-12-15 上午11:05
 * @Created by xns
 */
@Data
@ToString
public class BeanDefinition {

    private String name;

    private String className;

    private String interfaceName;

    private List<ConstructorArg> constructorArgs;

    private List<PropertyArg> propertyArgs;
}
