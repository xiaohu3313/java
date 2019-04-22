package com.youzan.ad.controller;

import com.alibaba.fastjson.JSON;
import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.IAdPlanService;
import com.youzan.ad.vo.AdPlanGetRequest;
import com.youzan.ad.vo.AdPlanRequest;
import com.youzan.ad.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/plan")
public class AdPlanController {

    @Autowired
    private IAdPlanService adPlanService;

    /**
     * 创建推广计划
     *
     * @param adPlanRequest
     * @return
     * @throws AdException
     */
    @PostMapping
    public AdPlanResponse createPlan(@RequestBody AdPlanRequest adPlanRequest) throws AdException {
        log.info("youzan-ad-sponsor: createPlan->{}", JSON.toJSONString(adPlanRequest));
        return adPlanService.createPlan(adPlanRequest);
    }

    /**
     * 修改推广计划
     *
     * @param adPlanRequest
     * @return
     * @throws AdException
     */
    @PutMapping
    public AdPlanResponse updatePlan(@RequestBody AdPlanRequest adPlanRequest) throws AdException {
        log.info("youzan-ad-sponsor: updatePlan->{}", JSON.toJSONString(adPlanRequest));
        return adPlanService.updatePlan(adPlanRequest);
    }

    /**
     * 查询推广计划
     *
     * @param adPlanGetRequest
     * @return
     * @throws AdException
     */
    @PostMapping(value = "/getPlan")
    public List<AdPlan> getPlan(@RequestBody AdPlanGetRequest adPlanGetRequest) throws AdException {
        log.info("youzan-ad-sponsor: getPlan->{}", JSON.toJSONString(adPlanGetRequest));
        return adPlanService.getPlanByIds(adPlanGetRequest);
    }

    /**
     * 删除推广计划
     *
     * @param adPlanRequest
     * @throws AdException
     */
    public void deletePlan(@RequestBody AdPlanRequest adPlanRequest) throws AdException {
        log.info("youzan-ad-sponsor: deletePlan->{}", JSON.toJSONString(adPlanRequest));
        adPlanService.deletePlan(adPlanRequest);
    }

}
