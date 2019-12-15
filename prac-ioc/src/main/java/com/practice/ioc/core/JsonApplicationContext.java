package com.practice.ioc.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.practice.ioc.bean.BeanDefinition;
import com.practice.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @Classname JsonApplicationContext
 * @Description 读取配置文件，将配置文件转换为容器能够理解的BeanDefination
 * @Date 19-12-15 上午11:23
 * @Created by xns
 */
public class JsonApplicationContext extends BeanFactoryimpl {
    private String fileName;
    public JsonApplicationContext(String fileName){
        this.fileName = fileName;
    }
    public void init(){
        loadFile();
    }
    private void loadFile(){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        List<BeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<BeanDefinition>>() {});
        if(beanDefinitions != null && !beanDefinitions.isEmpty()){
            for(BeanDefinition beanDefinition : beanDefinitions){
                registerBean(beanDefinition.getName(),beanDefinition);
            }
        }
    }
}
