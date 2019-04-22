package com.youzan.ad.handler;

import com.youzan.ad.dump.table.*;
import com.youzan.ad.index.DataTable;
import com.youzan.ad.index.IndexAware;
import com.youzan.ad.index.adplan.AdPlanIndex;
import com.youzan.ad.index.adplan.AdPlanObject;
import com.youzan.ad.index.adunit.AdUnitIndex;
import com.youzan.ad.index.adunit.AdUnitObject;
import com.youzan.ad.index.creative.CreativeIndex;
import com.youzan.ad.index.creative.CreativeObject;
import com.youzan.ad.index.creativeunit.CreativeUnitIndex;
import com.youzan.ad.index.creativeunit.CreativeUnitObject;
import com.youzan.ad.index.district.UnitDistrictIndex;
import com.youzan.ad.index.district.UnitDistrictObject;
import com.youzan.ad.index.interest.UnitItIndex;
import com.youzan.ad.index.interest.UnitItObject;
import com.youzan.ad.index.keyword.UnitKeyWordIndex;
import com.youzan.ad.index.keyword.UnitKeyWordObject;
import com.youzan.ad.index.mysql.constant.OpType;
import com.youzan.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;

@Slf4j

public class AdLevelDataHandler {

    /**
     * 推广计划
     * @param adPlanTable
     * @param type
     */
    public static void handleLevel2(AdPlanTable adPlanTable, OpType type){
        AdPlanObject planObject = new AdPlanObject(
                adPlanTable.getPlanId(),
                adPlanTable.getUserId(),
                adPlanTable.getPlanStatus(),
                adPlanTable.getStartDate(),
                adPlanTable.getEndDate()
        );
        handleBinLongEvent(
                DataTable.of(AdPlanIndex.class),
                planObject.getPlanId(),
                planObject,
                type
        );

    }

    /**
     * 创意
     * @param adCreativeTbale
     * @param type
     */
    public static void handlevel2(AdCreativeTbale adCreativeTbale, OpType type){
        CreativeObject creativeObject = new CreativeObject(
                adCreativeTbale.getAdId(),
                adCreativeTbale.getName(),
                adCreativeTbale.getType(),
                adCreativeTbale.getMaterialType(),
                adCreativeTbale.getHeight(),
                adCreativeTbale.getWidth(),
                adCreativeTbale.getAuditStatus()
        );

        handleBinLongEvent(
                DataTable.of(CreativeIndex.class),
                creativeObject.getAdId(),
                creativeObject,
                type
        );
    }

    /**
     * 推广单元
     * @param adUnitTable
     * @param type
     */
    public static void handlelevel3(AdUnitTable adUnitTable, OpType type){

        //判断是否有该推广计划
        AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(adUnitTable.getPlanId());
        if(null == adPlanObject){
            log.error("this planId is invalidate");
            return;
        }


        AdUnitObject adUnitObject = new AdUnitObject(
                adUnitTable.getUnitId(),
                adUnitTable.getUnitStatus(),
                adUnitTable.getPositionType(),
                adUnitTable.getPlanId()
        );

        handleBinLongEvent(
                DataTable.of(AdUnitIndex.class),
                adUnitObject.getUnitId(),
                adUnitObject,
                type
        );
    }

    /**
     * 创意，推广单元中间表
     * @param adCreativeUnitTbale
     * @param type
     */
    public static void handlevel3(AdCreativeUnitTbale adCreativeUnitTbale,OpType type){
        if(OpType.UPDATE==type){
            log.error("no support update");
            return;
        }

        CreativeObject creativeObject = DataTable.of(CreativeIndex.class).get(adCreativeUnitTbale.getAdId());
        if(null == creativeObject){
            log.error("this Adid is invalidate");
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adCreativeUnitTbale.getUnitId());
        if(null == adUnitObject){
            log.error("this unitId is invalidate");
            return;
        }

        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(
                adCreativeUnitTbale.getUnitId(),
                adCreativeUnitTbale.getAdId()
        );

        handleBinLongEvent(
                DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(creativeUnitObject.getAdId().toString(),
                        creativeUnitObject.getUnitId().toString()),
                creativeUnitObject,
                type
        );

    }

    /**
     * 地域限制
     * @param adUnitDistrictTable
     * @param type
     */
    public static void handleLevel4(AdUnitDistrictTable adUnitDistrictTable,OpType type){
        if(OpType.UPDATE == type){
            log.error("no support update");
            return;
        }

        //判断单元是否存在
        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitDistrictTable.getUnitId());
        if(null == adUnitObject){
            log.error("this unitId is invalidate");
            return;
        }

        UnitDistrictObject unitDistrictObject = new UnitDistrictObject(
                adUnitDistrictTable.getUnitId(),
                adUnitDistrictTable.getProvince(),
                adUnitDistrictTable.getCity()
        );

        handleBinLongEvent(
                DataTable.of(UnitDistrictIndex.class),
                CommonUtils.stringConcat(
                        unitDistrictObject.getCity(),
                        unitDistrictObject.getProvince()
                ),
                new HashSet<>(
                        Collections.singleton(unitDistrictObject.getUnitId())
                ),
                type
                
        );

    }

    /**
     * 兴趣限制
     * @param adUnitItTable
     * @param type
     */
    public static void handlLevel4(AdUnitItTable adUnitItTable, OpType type){
        if(OpType.UPDATE==type){
            log.error("no support update");
        }
        
        //判断单元是否存在
        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitItTable.getUnitId());
        if(adUnitObject==null){
            log.error("this unitId is invalidate");
            return;
        }

        UnitItObject unitItObject = new UnitItObject(
                adUnitItTable.getUnitId(),
                adUnitItTable.getItTag()
        );
        
        handleBinLongEvent(
                DataTable.of(UnitItIndex.class),
                CommonUtils.stringConcat(
                        unitItObject.getItTag()
                ),
                new HashSet<>(
                        Collections.singleton(unitItObject.getUnitId())
                ),
                type
        );
    }

    /**
     * 关键词限制
     * @param adUnitKeywordTable
     * @param type
     */
    public static void handLevel4(AdUnitKeywordTable adUnitKeywordTable,OpType type){
        if(OpType.UPDATE==type){
            log.error("no support update");
            return;
        }
        
        //判断单元是否存在
        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitKeywordTable.getUnitId());
        if(null == adUnitObject){
            log.error("this unitId is invalidate");
            return;
        }

        UnitKeyWordObject unitKeyWordObject = new UnitKeyWordObject(
                adUnitKeywordTable.getUnitId(),
                adUnitKeywordTable.getKeyword()
        );

        handleBinLongEvent(
                DataTable.of(UnitKeyWordIndex.class),
                unitKeyWordObject.getKeyWord(),
                new HashSet<>(
                        Collections.singleton(
                                unitKeyWordObject.getUnitId()
                        )
                ),
                type
        );
    }
    

    private static <K,V> void handleBinLongEvent(
            IndexAware<K,V> indexAware,
            K key,
            V value,
            OpType type
    ){
        switch (type){
            case ADD:
                indexAware.add(key,value);
                break;
            case UPDATE:
                indexAware.update(key,value);
                break;
            case DELETE:
                indexAware.delete(key,value);
                break;
            default:
                break;
        }
    }

}
