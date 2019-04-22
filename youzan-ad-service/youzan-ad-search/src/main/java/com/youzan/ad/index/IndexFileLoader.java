package com.youzan.ad.index;


import com.alibaba.fastjson.JSON;
import com.youzan.ad.dump.DConstant;
import com.youzan.ad.dump.table.*;
import com.youzan.ad.handler.AdLevelDataHandler;
import com.youzan.ad.index.mysql.constant.OpType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全量加载索引的实现
 */
@Component
@DependsOn("dataTable")
public class IndexFileLoader {

    @PostConstruct
    public void init(){
        /**
         * 推广计划
         */
        List<String> adPlanString = loadDumpData(
                String.format(
                        "%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_PLAN
                )
        );
        adPlanString.forEach(
                adPlan->AdLevelDataHandler.handleLevel2(
                        JSON.parseObject(adPlan, AdPlanTable.class),
                        OpType.ADD
                )
        );

        /**
         * 创意
         */
        List<String> adCreativeString = loadDumpData(
                String.format(
                        "%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CREATIVE
                )
        );
        adCreativeString.forEach(
                adCreative->AdLevelDataHandler.handlevel2(
                        JSON.parseObject(adCreative, AdCreativeTbale.class),
                        OpType.ADD
                )
        );

        /**
         * 推广单元
         */
        loadDumpData(
                String.format(
                        "%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT
                )
        ).forEach(
                adUnit->AdLevelDataHandler.handlelevel3(
                        JSON.parseObject(adUnit, AdUnitTable.class),
                        OpType.ADD
                )
        );

        /**
         * 单元创意中间表
         */
        loadDumpData(
                String.format(
                        "%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CREATIVE_UNIT
                )
        ).forEach(
                adCreativeUnit->AdLevelDataHandler.handlevel3(
                        JSON.parseObject(adCreativeUnit, AdCreativeUnitTbale.class),
                        OpType.ADD
                )
        );

        /**
         * 地域限制
         */
        loadDumpData(
                String.format(
                        "%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_DISTRICT
                )
        ).forEach(
                adUnitDistrict->AdLevelDataHandler.handleLevel4(
                        JSON.parseObject(adUnitDistrict, AdUnitDistrictTable.class),
                        OpType.ADD
                )
        );

        /**
         * 兴趣限制
         */
        loadDumpData(
                String.format(
                        "%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_IT
                )
        ).forEach(
                adUnitIt->AdLevelDataHandler.handlLevel4(
                        JSON.parseObject(adUnitIt, AdUnitItTable.class),
                        OpType.ADD
                )
        );

        /**
         * 关键词限制
         */
        loadDumpData(
                String.format(
                        "%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_KEYWORD
                )
        ).forEach(
                adUnitKeyword->AdLevelDataHandler.handLevel4(
                        JSON.parseObject(adUnitKeyword, AdUnitKeywordTable.class),
                        OpType.ADD
                )
        );


    }

    private List<String> loadDumpData(String fileName){

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))){
            return reader.lines().collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
