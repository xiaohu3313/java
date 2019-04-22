package com.youzan.ad.controller;

import com.alibaba.fastjson.JSON;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.IAdUnitService;
import com.youzan.ad.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unit")
@Slf4j
public class UnitController {

    @Autowired
    private IAdUnitService unitService;

    /**
     * 创建推广单元
     * @param unitRequest
     * @return
     * @throws AdException
     */
    @PostMapping
    public AdUnitResponse createUnit(@RequestBody AdUnitRequest unitRequest) throws AdException {

        log.info("youzan-ad-sponsor:createUnit ->{}", JSON.toJSONString(unitRequest));
        return unitService.createUnit(unitRequest);
    }

    /**
     * 创建推广单元 关键字限制
     * @param unitKeyWordRequest
     * @return
     * @throws AdException
     */
    @PostMapping("/createUnitKeyword")
    public AdUnitKeywordResponse createUnitKeyword(@RequestBody AdUnitKeyWordRequest unitKeyWordRequest) throws AdException{
        log.info("youzan-ad-sponsor:createUnitKeyword ->{}", JSON.toJSONString(unitKeyWordRequest));
        return unitService.createUnitKeyword(unitKeyWordRequest);
    }

    /**
     * 创建推广单元  兴趣限制
     * @param unitItRequest
     * @return
     * @throws AdException
     */
    @PostMapping("/createUnitIt")
    public AdUnitItResponse createUnitIt(@RequestBody AdUnitItRequest unitItRequest) throws AdException {
        log.info("youzan-ad-sponsor:createUnitIt ->{}", JSON.toJSONString(unitItRequest));
        return unitService.createUnitIt(unitItRequest);
    }

    /**
     * 创建推广单元  地域限制
     * @param unitDistrictRequest
     * @return
     * @throws AdException
     */
    @PostMapping("/createUnitDistrict")
    public AdUnitDistrictResponse createUnitDistrict(@RequestBody AdUnitDistrictRequest unitDistrictRequest) throws AdException{
        log.info("youzan-ad-sponsor:createUnitDistrict ->{}", JSON.toJSONString(unitDistrictRequest));
        return unitService.createUnitDistrict(unitDistrictRequest);
    }

    /**
     * 创建创意
     * @param creativeUnitRequest
     * @return
     * @throws AdException
     */
    @PostMapping("/createCreativeUnit")
    public CreativeUnitResponse createCreativeUnit(@RequestBody CreativeUnitRequest creativeUnitRequest) throws AdException{
        log.info("youzan-ad-sponsor:createCreativeUnit ->{}", JSON.toJSONString(creativeUnitRequest));
        return unitService.createCreativeUnit(creativeUnitRequest);
    }
}
