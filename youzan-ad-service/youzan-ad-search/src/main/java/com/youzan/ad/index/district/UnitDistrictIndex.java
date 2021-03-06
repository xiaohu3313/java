package com.youzan.ad.index.district;

import com.youzan.ad.index.IndexAware;
import com.youzan.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 地域限制索引，倒插索引
 */
@Component
@Slf4j
public class UnitDistrictIndex implements IndexAware<String, Set<Long>> {

    //<地域（省-市），Set  unitId>
    private static Map<String, Set<Long>> districtUnitMap;

    //<UnitId, Set 地域（省-市）>
    private static Map<Long, Set<String>> unitDistrictMap;

    static {
        districtUnitMap = new ConcurrentHashMap<>();

        unitDistrictMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        return districtUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        CommonUtils.getorCreate(key,districtUnitMap,ConcurrentSkipListSet::new).addAll(value);

        value.forEach(
                i->CommonUtils.getorCreate(i, unitDistrictMap, ConcurrentSkipListSet::new).add(key)
        );

    }

    @Override
    public void update(String key, Set<Long> value) {

        log.error("no support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {

        CommonUtils.getorCreate(key, districtUnitMap, ConcurrentSkipListSet::new).removeAll(value);

        value.forEach(
                i->CommonUtils.getorCreate(i, unitDistrictMap, ConcurrentSkipListSet::new).remove(key)
        );
    }
}
