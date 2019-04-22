package com.youzan.ad.service;

import com.youzan.ad.exception.AdException;
import com.youzan.ad.vo.*;

public interface IAdUnitService {

    /**
     * 创建推广单元
     * @param unitRequest
     * @return
     * @throws AdException
     */
    AdUnitResponse createUnit(AdUnitRequest unitRequest) throws AdException;


    /**
     * 创建推广单元  关键词限制
     * @param unitKeyWordRequest
     * @return
     */
    AdUnitKeywordResponse createUnitKeyword(AdUnitKeyWordRequest unitKeyWordRequest) throws AdException;


    /**
     * 创建推广单元  兴趣限制
     * @param unitItRequest
     * @return
     * @throws AdException
     */
    AdUnitItResponse createUnitIt(AdUnitItRequest unitItRequest) throws AdException;


    /**
     * 创建推广单元   地域限制
     * @param unitDistrictRequest
     * @return
     * @throws AdException
     */
    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest unitDistrictRequest) throws AdException;


    /**
     * 创建创意
     * @param creativeUnitRequest
     * @return
     * @throws AdException
     */
    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest creativeUnitRequest) throws AdException;
}
