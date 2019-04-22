package com.youzan.ad.service;

import com.alibaba.fastjson.JSON;
import com.youzan.ad.Application;
import com.youzan.ad.constant.CommonStatus;
import com.youzan.ad.dao.AdCreativeRepository;
import com.youzan.ad.dao.AdPlanRepository;
import com.youzan.ad.dao.AdUnitRepository;
import com.youzan.ad.dao.unit_condition.AdCreativeUnitRepository;
import com.youzan.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.youzan.ad.dao.unit_condition.AdUnitItRepository;
import com.youzan.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.youzan.ad.dump.table.*;
import com.youzan.ad.entity.AdCreative;
import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.entity.AdUnit;
import com.youzan.ad.entity.unit_condition.AdCreativeUnit;
import com.youzan.ad.entity.unit_condition.AdUnitDistrict;
import com.youzan.ad.entity.unit_condition.AdUnitIt;
import com.youzan.ad.entity.unit_condition.AdUnitKeyword;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {

    @Autowired
    private AdPlanRepository adPlanRepository;
    @Autowired
    private AdUnitRepository adUnitRepository;
    @Autowired
    private AdCreativeRepository adCreativeRepository;
    @Autowired
    private AdCreativeUnitRepository adCreativeUnitRepository;
    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;
    @Autowired
    private AdUnitItRepository adUnitItRepository;
    @Autowired
    private AdUnitKeywordRepository adUnitKeywordRepository;


    /**
     * 推广计划
     * @param fileName
     * @throws IOException
     */
    private void dumpAdPlanTable(String fileName) throws IOException {
        List<AdPlan> adplans = adPlanRepository.findAllByPlanStatus(
                CommonStatus.VALID.getStatus()
        );
        ArrayList<AdPlanTable> adPlanTables = new ArrayList<>();
        adplans.forEach(
                i -> adPlanTables.add(
                        new AdPlanTable(
                                i.getId(),
                                i.getUserId(),
                                i.getPlanStatus(),
                                i.getStartTime(),
                                i.getEndTime()
                        )
                )
        );

        final Path path = Paths.get(fileName);


        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (AdPlanTable adPlanTable : adPlanTables){
                bufferedWriter.write(JSON.toJSONString(adPlanTable));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

    /**
     * 推广单元
     * @param fileName
     */
    private void dumpUnitTable(String fileName){
        List<AdUnit> adUnits = adUnitRepository.findAllByUnitStatus(
                CommonStatus.VALID.getStatus()
        );
        List<AdUnitTable> adUnitTables = new ArrayList<>();

        adUnits.forEach(
                adUnit -> adUnitTables.add(
                        new AdUnitTable(
                                adUnit.getId(),
                                adUnit.getUnitStatus(),
                                adUnit.getPositionType(),
                                adUnit.getPlanId()
                        )
                )
        );

        Path path = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitTable adUnitTable:adUnitTables){
                writer.write(JSON.toJSONString(adUnitTable));
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

    /**
     * 创意
     * @param fileName
     */
    private void dumpCreativeTable (String fileName){
        List<AdCreative> creativeList = adCreativeRepository.findAll();
        List<AdCreativeTbale> creativeTbales = new ArrayList<>();

        creativeList.forEach(
                adCreative -> creativeTbales.add(
                        new AdCreativeTbale(
                                adCreative.getId(),
                                adCreative.getName(),
                                adCreative.getType(),
                                adCreative.getMaterialType(),
                                adCreative.getHeight(),
                                adCreative.getWidth(),
                                adCreative.getAuditStatus()
                        )
                )
        );

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdCreativeTbale creativeTbale : creativeTbales){
                writer.write(JSON.toJSONString(creativeTbale));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 创意推广单元中间表
     * @param fileName
     */
    private void dumpCreativeUnitTable(String fileName){
        List<AdCreativeUnit> creativeUnits = adCreativeUnitRepository.findAll();
        List<AdCreativeUnitTbale> creativeUnitTbales = new ArrayList<>();
        creativeUnits.forEach(
                adCreativeUnit -> creativeUnitTbales.add(
                        new AdCreativeUnitTbale(
                                adCreativeUnit.getUnitId(),
                                adCreativeUnit.getId()
                        )
                )
        );

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdCreativeUnitTbale creativeUnitTbale:creativeUnitTbales){
                writer.write(JSON.toJSONString(creativeUnitTbale));
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 地域限制
     * @param fileName
     */
    private void dumpCreativeDistrictTable (String fileName) {
        List<AdUnitDistrict> unitDistricts = adUnitDistrictRepository.findAll();
        List<AdUnitDistrictTable> unitDistrictTables = new ArrayList<>();
        unitDistricts.forEach(
                adUnitDistrict -> unitDistrictTables.add(
                        new AdUnitDistrictTable(
                                adUnitDistrict.getUnitId(),
                                adUnitDistrict.getProvince(),
                                adUnitDistrict.getCity()
                        )
                )
        );

        Path path = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitDistrictTable unitDistrictTable:unitDistrictTables){
                writer.write(JSON.toJSONString(unitDistrictTable));
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 兴趣限制
     */
    private void dumpCreativeItTable (String fileName){
        List<AdUnitIt> unitIts = adUnitItRepository.findAll();
        List<AdUnitItTable> unitItTables = new ArrayList<>();
        unitIts.forEach(
                adUnitIt -> unitItTables.add(
                        new AdUnitItTable(
                                adUnitIt.getUnitId(),
                                adUnitIt.getItTag()
                        )
                )
        );

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitItTable unitItTable:unitItTables){
                writer.write(JSON.toJSONString(unitItTable));
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * 关键词限制
     * @param fileName
     */
    private void dumpCreativeKeyWord(String fileName){
        List<AdUnitKeyword> unitKeywords = adUnitKeywordRepository.findAll();
        List<AdUnitKeywordTable> unitKeywordTables = new ArrayList<>();
        unitKeywords.forEach(
                adUnitKeyword -> unitKeywordTables.add(
                        new AdUnitKeywordTable(
                                adUnitKeyword.getUnitId(),
                                adUnitKeyword.getKeyword()
                        )
                )
        );

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for (AdUnitKeywordTable unitKeywordTable:unitKeywordTables){
                writer.write(JSON.toJSONString(unitKeywordTable));
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
