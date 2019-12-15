package com.practice.ioc.core;

import com.practice.ioc.bean.BeanDefinition;
import com.practice.ioc.bean.ConstructorArg;
import com.practice.ioc.utils.BeanUtils;
import com.practice.ioc.utils.ClassUtils;
import com.practice.ioc.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname BeanFactoryimpl
 * @Description TODO
 * @Date 19-12-15 上午11:22
 * @Created by xns
 */
public class BeanFactoryimpl implements BeanFactory {

    /**
     * BeanMap用于保存beanName实例化之后的对象。
     */
    private static final ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 存储对象名称和对象对应的数据结构的映射。
     */
    private static final ConcurrentHashMap<String, BeanDefinition> beanDefineMap = new ConcurrentHashMap<>();

    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<String>());


    @Override
    public Object getBean(String name) throws Exception {
        /**
         * 查找对象是否已经实例过
         */
        Object bean = beanMap.get(name);
        if(bean != null){
            return bean;
        }
        /**
         * 如果没有实例化，那就需要调用createBean来创建对象
         */
        bean = createBean(beanDefineMap.get(name));
        if(bean != null){
            /**
             * 注入对象需要的参数
             */
            populatebean(bean);
            /**
             * 把对象存入map中方便下次使用
             */
            beanMap.put(name,bean);
        }
        /**
         * 结束返回
         */
        return bean;
    }


    protected void registerBean(String name,BeanDefinition bd){
        beanDefineMap.put(name,bd);
        beanNameSet.add(name);
    }

    private Object createBean(BeanDefinition beanDefinition) throws Exception{
        String beanName = beanDefinition.getClassName();
        Class clz = ClassUtils.loadClass(beanName);
        if(clz == null){
            throw  new Exception("can not find bean by beanName");
        }
        List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
        if(constructorArgs !=null&& !constructorArgs.isEmpty()){
            List<Object> objects = new ArrayList<>();
            for(ConstructorArg constructorArg:constructorArgs){
                objects.add(constructorArg);
            }
            return BeanUtils.instanceByCglib(clz,clz.getConstructor(),objects.toArray());
        }else{
            return BeanUtils.instanceByCglib(clz,null,null);
        }
    }

    public void populatebean(Object bean) throws Exception{
        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
        if(fields != null && fields.length > 0){
            for(Field field:fields){
                String beanName = field.getName();
                /**
                 * 字符串首字母转小写
                 */
                beanName = StringUtils.uncapitalize(beanName);
                if(beanNameSet.contains(field.getName())) {
                    Object fieldBean = getBean(beanName);
                    if (fieldBean != null) {
                        ReflectionUtils.injectFiled(field,bean,fieldBean);
                    }
                }
            }
        }
    }
}
