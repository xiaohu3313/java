package com.youzan.ad.index.adplan;

import com.youzan.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class AdPlanIndex implements IndexAware<Long, AdPlanObject> {

    private static Map<Long, AdPlanObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdPlanObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdPlanObject value) {
        log.info("add before", objectMap);
        objectMap.put(key,value);
        log.info("add after", objectMap);
    }

    @Override
    public void update(Long key, AdPlanObject value) {
        log.info("update before", objectMap);
        AdPlanObject adPlanObject = objectMap.get(key);
        if(null==adPlanObject){
            objectMap.put(key,value);
        }else{
            adPlanObject.update(value);
        }
        log.info("update after", objectMap);
    }

    @Override
    public void delete(Long key,AdPlanObject value) {
        log.info("delete before", objectMap);
        objectMap.remove(key);
        log.info("delete after", objectMap);
    }
}
