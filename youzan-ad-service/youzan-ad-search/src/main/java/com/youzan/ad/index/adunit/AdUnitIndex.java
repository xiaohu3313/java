package com.youzan.ad.index.adunit;

import com.youzan.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Slf4j
public class AdUnitIndex implements IndexAware<Long, AdUnitObject> {

    private static Map<Long, AdUnitObject> objectMap;
    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdUnitObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdUnitObject value) {
        log.info("add before",objectMap);
        objectMap.put(key,value);
        log.info("add after",objectMap);
    }

    @Override
    public void update(Long key, AdUnitObject value) {
        log.info("update before",objectMap);
        AdUnitObject adUnitObject = objectMap.get(key);
        if(null == adUnitObject){
            objectMap.put(key,value);
        }else{
            adUnitObject.update(value);
        }
        log.info("update after",objectMap);
    }

    @Override
    public void delete(Long key,AdUnitObject value) {
        log.info("delete before",objectMap);
        objectMap.remove(key);
        log.info("delete after",objectMap);
    }
}
