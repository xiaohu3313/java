package com.youzan.ad.index.creativeunit;

import com.youzan.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@Slf4j
public class CreativeUnitIndex implements IndexAware<String, CreativeUnitObject> {

    private static Map<String, CreativeUnitObject> objectMap;

    //<adId,unit set>
    private static Map<Long, Set<Long>> creativeUnitMap;

    //<unitId,adId  set>
    private static Map<Long, Set<Long>> unitCraetiveMap;

    static {
        objectMap = new ConcurrentHashMap<>();
        creativeUnitMap = new ConcurrentHashMap<>();
        unitCraetiveMap = new ConcurrentHashMap<>();
    }

    @Override
    public CreativeUnitObject get(String key) {
        return objectMap.get(key);
    }

    @Override
    public void add(String key, CreativeUnitObject value) {
        objectMap.put(key,value);
        Set<Long> unitSet = creativeUnitMap.get(value.getAdId());
        if(CollectionUtils.isEmpty(unitSet)){
            unitSet = new ConcurrentSkipListSet<>();
            creativeUnitMap.put(value.getUnitId(),unitSet);
        }
        unitSet.add(value.getUnitId());

        Set<Long> adIdSet = unitCraetiveMap.get(value.getUnitId());
        if(CollectionUtils.isEmpty(adIdSet)){
            adIdSet = new ConcurrentSkipListSet<>();
            unitCraetiveMap.put(value.getAdId(),adIdSet);
        }
        adIdSet.add(value.getAdId());
    }

    @Override
    public void update(String key, CreativeUnitObject value) {
        log.error("no support update");
    }

    @Override
    public void delete(String key,CreativeUnitObject value) {
        objectMap.remove(key);
        Set<Long> unitSet = creativeUnitMap.get(value.getAdId());
        if(!CollectionUtils.isEmpty(unitSet)){
            unitSet.remove(value.getUnitId());
        }

        Set<Long> adIdSet = unitCraetiveMap.get(value.getUnitId());
        if(!CollectionUtils.isEmpty(adIdSet)){
            adIdSet.remove(value.getAdId());
        }
    }
}
