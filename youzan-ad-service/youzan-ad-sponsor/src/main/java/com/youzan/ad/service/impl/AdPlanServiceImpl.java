package com.youzan.ad.service.impl;

import com.youzan.ad.constant.CommonStatus;
import com.youzan.ad.constant.Constants;
import com.youzan.ad.dao.AdPlanRepository;
import com.youzan.ad.dao.AdUserRepository;
import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.entity.AdUser;
import com.youzan.ad.exception.AdException;
import com.youzan.ad.service.IAdPlanService;
import com.youzan.ad.utils.CommonUtils;
import com.youzan.ad.vo.AdPlanGetRequest;
import com.youzan.ad.vo.AdPlanRequest;
import com.youzan.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdPlanServiceImpl implements IAdPlanService {

    @Autowired
    private AdPlanRepository adPlanRepository;

    @Autowired
    private AdUserRepository adUserRepository;

    /**
     * 创建推广计划
     *
     * @param planRequest
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public AdPlanResponse createPlan(AdPlanRequest planRequest) throws AdException {
        //验证参数合法性
        if (!planRequest.createPlanValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMETERS_ERROR);
        }
        //查询当前广告主是否存在
        Optional<AdUser> adUser = adUserRepository.findById(planRequest.getUserId());
        if (!adUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_USER);
        }
        //查询当前广告主是否有相同的推广计划
        AdPlan byUserIdAndPlanName = adPlanRepository.findByUserIdAndPlanName(planRequest.getUserId(), planRequest.getPlanName());
        if (null != byUserIdAndPlanName) {
            throw new AdException(Constants.ErrorMsg.THE_PLAN_ALREADY_EXISTS);
        }
        //保存
        AdPlan newPlan = adPlanRepository.save(
                new AdPlan(
                        planRequest.getUserId(),
                        planRequest.getPlanName(),
                        CommonUtils.parseStringDate(planRequest.getStartTime()),
                        CommonUtils.parseStringDate(planRequest.getEndTime())
                ));
        return new AdPlanResponse(newPlan.getId(), newPlan.getPlanName());
    }

    /**
     * 更新推广计划
     *
     * @param planRequest
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public AdPlanResponse updatePlan(AdPlanRequest planRequest) throws AdException {

        //验证参数是否合法
        if (!planRequest.updatePlanValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMETERS_ERROR);
        }

        //验证要修改的推广计划是否存在
        AdPlan byIdAndUserId = adPlanRepository.findByIdAndUserId(planRequest.getId(), planRequest.getUserId());
        if (null == byIdAndUserId) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_PLAN);
        }

        //验证属性是否为空
        if (null != planRequest.getPlanName()) {
            byIdAndUserId.setPlanName(planRequest.getPlanName());
        }
        if (null != planRequest.getStartTime()) {
            byIdAndUserId.setStartTime(CommonUtils.parseStringDate(planRequest.getStartTime()));
        }
        if (null != planRequest.getEndTime()) {
            byIdAndUserId.setEndTime(CommonUtils.parseStringDate(planRequest.getEndTime()));
        }
        byIdAndUserId.setUpdateTime(new Date());
        //保存
        byIdAndUserId = adPlanRepository.save(byIdAndUserId);
        return new AdPlanResponse(byIdAndUserId.getId(), byIdAndUserId.getPlanName());
    }

    /**
     * 获取推广计划
     *
     * @param planGetRequest
     * @return
     * @throws AdException
     */
    @Override
    public List<AdPlan> getPlanByIds(AdPlanGetRequest planGetRequest) throws AdException {
        //验证参数合法性
        if(!planGetRequest.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMETERS_ERROR);
        }
        return adPlanRepository.findAllByIdInAndUserId(
                planGetRequest.getIds(),planGetRequest.getUserId());
    }

    /**
     * 删除推广计划
     *
     * @param planRequest
     * @throws AdException
     */
    @Override
    @Transactional
    public void deletePlan(AdPlanRequest planRequest) throws AdException {
        //验证参数合法性
        if(!planRequest.deletePlanValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAMETERS_ERROR);
        }
        //验证要删除的推广计划是否存在
        AdPlan byIdAndUserId = adPlanRepository.findByIdAndUserId(planRequest.getId(), planRequest.getUserId());
        if(byIdAndUserId==null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_PLAN);
        }
        byIdAndUserId.setPlanStatus(CommonStatus.INVALID.getStatus());
        byIdAndUserId.setUpdateTime(new Date());
        adPlanRepository.save(byIdAndUserId);
    }
}
