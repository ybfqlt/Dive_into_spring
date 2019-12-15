package com.practice.ioc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Classname JsonUtils
 * @Description TODO
 * @Date 19-12-15 上午11:23
 * @Created by xns
 */
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String objectToJson(Object data){
        try{
            String string = MAPPER.writeValueAsString(data);
            return string;
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToPojo(String jsonData,Class<T> beanType){
        try{
            T t = MAPPER.readValue(jsonData,beanType);
            return t;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> jsonToList(String jsonData,Class<T> beanType){
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class,beanType);
        try{
            List<T> list = MAPPER.readValue(jsonData,javaType);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T readValue(InputStream is, TypeReference<T> type){
        try{
            return MAPPER.readValue(is,type);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
