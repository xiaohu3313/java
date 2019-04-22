package com.youzan.ad.service.impl;

import com.youzan.ad.constant.Constants;
import com.youzan.ad.dao.AdPlanRepository;
import com.youzan.ad.dao.AdUnitRepository;
import com.youzan.ad.dao.unit_condition.AdCreativeUnitRepository;
import com.youzan.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.youzan.ad.dao.unit_condition.AdUnitItRepository;
import com.youzan.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.entity.AdUnit;
import com.youzan.ad.entity.unit_condition.AdCreativeUnit;
import com.youzan.ad.entity.unit_condition.AdUnitDistrict;
import com.youzan.ad.entity.unit_condition.AdUnitIt;
import com.youzan.ad.entity.unit_condition.AdUnitKeyword;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.IAdUnitService;
import com.youzan.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdUnitServiceImpl implements IAdUnitService {

    @Autowired
    private AdPlanRepository planRepository;

    @Autowired
    private AdUnitRepository unitRepository;

    @Autowired
    private AdUnitKeywordRepository unitKeywordRepository;

    @Autowired
    private AdUnitItRepository unitItRepository;

    @Autowired
    private AdUnitDistrictRepository unitDistrictRepository;

    @Autowired
    private AdCreativeUnitRepository creativeUnitRepository;

    /**
     * 创建推广单元
     * @param unitRequest
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public AdUnitResponse createUnit(AdUnitRequest unitRequest) throws AdException {
        //验证参数合法性
        if(!unitRequest.createvalidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMETERS_ERROR);
        }
        //验证创建的该推广单元的推广计划是否存在
        Optional<AdPlan> adPlan = planRepository.findById(unitRequest.getPlanId());
        if(!adPlan.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_PLAN);
        }
        //验证要创建的该退管计划是否已经存在
        AdUnit adUnit = unitRepository.findByPlanIdAndUnitName(unitRequest.getPlanId(), unitRequest.getUnitName());
        if(adUnit != null){
            throw new AdException(Constants.ErrorMsg.THE_PLAN_ALREADY_EXISTS);
        }

        AdUnit newUnit = unitRepository.save(
                new AdUnit(
                        unitRequest.getPlanId(),
                        unitRequest.getUnitName(),
                        unitRequest.getPositionType(),
                        unitRequest.getBudget()
                )
        );
        return new AdUnitResponse(newUnit.getId(),newUnit.getUnitName());
    }

    /**
     * 创建推广单元  关键词限制
     * @param unitKeyWordRequest
     * @return
     */
    @Override
    @Transactional
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeyWordRequest unitKeyWordRequest) throws AdException {
        List<Long> unitIds = unitKeyWordRequest.getUnitKeyWords().stream().map(
                AdUnitKeyWordRequest.UnitKeyWords::getUnitId).collect(Collectors.toList());

        ArrayList<AdUnitKeyword>  unitKeywordList= new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        //然后拿着 unitIds遍历，AdUnit   只要有一个不存在，则抛异常  参数不合法
        if(!CollectionUtils.isEmpty(unitKeyWordRequest.getUnitKeyWords())){
            unitKeyWordRequest.getUnitKeyWords().forEach(
                    i -> unitKeywordList.add(
                            new AdUnitKeyword(i.getUnitId(),i.getKeyword())
                    )
            );

            ids = unitKeywordRepository.saveAll(unitKeywordList).stream().map(
                    AdUnitKeyword::getId
            ).collect(Collectors.toList());

        }
        return new AdUnitKeywordResponse(ids);
    }

    /**
     * 创建推广单元  兴趣限制
     * @param unitItRequest
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public AdUnitItResponse createUnitIt(AdUnitItRequest unitItRequest) throws AdException {
        /*List<Long> unitIts = unitItRequest.getUnitIts().stream().map(
                AdUnitItRequest.UnitIt::getUnitId
        ).collect(Collectors.toList());

        List<AdUnitIt> adUnitItList = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        //然后拿着 unitIds遍历，AdUnit   只要有一个不存在，则抛异常  参数不合法
        if(!CollectionUtils.isEmpty(unitItRequest.getUnitIts())){
            unitItRequest.getUnitIts().forEach(
                    i ->adUnitItList.add(
                            new AdUnitIt(i.getUnitId(),i.getItTag())
                    )
            );
            ids = unitItRepository.saveAll(adUnitItList).stream().map(
                    AdUnitIt::getId
            ).collect(Collectors.toList());
        }*/

        List<Long> UnitIds = unitItRequest.getUnitIts().stream().map(
                AdUnitItRequest.UnitIt::getUnitId
        ).collect(Collectors.toList());

        List<Long> ids = new ArrayList<>();
        ArrayList<AdUnitIt> adUnitIt = new ArrayList<>();

        if(!CollectionUtils.isEmpty(unitItRequest.getUnitIts())){
            unitItRequest.getUnitIts().forEach(
                    unitIt -> adUnitIt.add(
                            new AdUnitIt(unitIt.getUnitId(),unitIt.getItTag())
                    )
            );

            ids = unitItRepository.saveAll(adUnitIt).stream().map(
                    AdUnitIt::getId
            ).collect(Collectors.toList());

        }

        return new AdUnitItResponse(ids);
    }

    /**
     * 创建推广单元   地域限制
     * @param unitDistrictRequest
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest unitDistrictRequest) throws AdException {
        List<Long> UnitIds = unitDistrictRequest.getUnitDistricts().stream().map(
                AdUnitDistrictRequest.UnitDistricts::getUnitId
        ).collect(Collectors.toList());

        List<AdUnitDistrict> unitDistrictList = new ArrayList<>();
        List<Long> list = new ArrayList<>();

        if(!CollectionUtils.isEmpty(unitDistrictRequest.getUnitDistricts())){
            unitDistrictRequest.getUnitDistricts().forEach(
                    unitDistricts -> unitDistrictList.add(
                            new AdUnitDistrict(unitDistricts.getUnitId(),unitDistricts.getProvince(),unitDistricts.getCity())
                    )
            );

            list = unitDistrictRepository.saveAll(unitDistrictList).stream().map(
                    AdUnitDistrict::getId
            ).collect(Collectors.toList());
        }
        return new AdUnitDistrictResponse(list);
    }

    /**
     * 创建创意
     * @param creativeUnitRequest
     * @return
     * @throws AdException
     */
    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest creativeUnitRequest) throws AdException {

        List<Long> ids = Collections.emptyList();

        //获取创意Id集合
        List<Long> creativeId = creativeUnitRequest.getCreativeUnits().stream().map(
                CreativeUnitRequest.CreativeUnits::getCreativeId
        ).collect(Collectors.toList());

        //获取推广单元Id集合
        creativeUnitRequest.getCreativeUnits().stream().map(
                CreativeUnitRequest.CreativeUnits::getUnitId
        ).collect(Collectors.toList());

        List<AdCreativeUnit> creativeUnitList = new ArrayList<>();

        //然后creativeIdList,unidList遍历   只要有一个不存在，则抛异常  参数不合法
        creativeUnitRequest.getCreativeUnits().forEach(
                creativeUnits -> creativeUnitList.add(
                        new AdCreativeUnit(creativeUnits.getCreativeId(),creativeUnits.getUnitId())
                )
        );
        ids = creativeUnitRepository.saveAll(creativeUnitList).stream().map(
                AdCreativeUnit::getId
        ).collect(Collectors.toList());


        return new CreativeUnitResponse(ids);
    }
}
