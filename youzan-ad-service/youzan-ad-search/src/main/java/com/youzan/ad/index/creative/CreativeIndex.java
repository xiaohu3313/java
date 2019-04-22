package com.youzan.ad.index.creative;

import com.youzan.ad.index.IndexAware;
import com.youzan.ad.index.adunit.AdUnitObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CreativeIndex implements IndexAware<Long,CreativeObject> {

    private static Map<Long,CreativeObject> objectMap;
    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public CreativeObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, CreativeObject value) {
        objectMap.put(key, value);
    }

    @Override
    public void update(Long key, CreativeObject value) {
        CreativeObject creativeObject = objectMap.get(key);
        if(null == creativeObject){
            objectMap.put(key, value);
        }else{
            creativeObject.update(value);
        }
    }

    @Override
    public void delete(Long key,CreativeObject value) {
        objectMap.remove(key);
    }
}
