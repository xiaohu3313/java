package com.youzan.ad.index;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataTable implements ApplicationContextAware, PriorityOrdered {

    private static ApplicationContext applicationContext;

    private static Map<Class,Object> dataTableMap =new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DataTable.applicationContext = applicationContext;
    }


    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }

    public static <T> T of(Class<T> clazz){
        T instance = (T)dataTableMap.get(clazz);
        if(null!=instance){
            return instance;
        }
        dataTableMap.put(clazz,bean(clazz));
        return (T)dataTableMap.get(clazz);
    }

    public static <T> T bean(Class clazz){
        return (T)applicationContext.getBean(clazz);
    }
}
