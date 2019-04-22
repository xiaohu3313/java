package com.youzan.ad.dao;

import com.youzan.ad.entity.AdPlan;
import com.youzan.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdPlanRepository extends JpaRepository<AdPlan, Long> {

    /**
     * 根据广告主Id查询广告计划
     * @param userId
     * @return
     */
    List<AdPlan> findAllByUserId(Long userId);

    /**
     * 广告主查看指定的广告计划
     * @param id
     * @param userId
     * @return
     */
    AdPlan findByIdAndUserId(Long id, Long userId);

    /**
     * 广告主查看指定多个广告计划
     * @param ids
     * @param userId
     * @return
     */
    List<AdPlan> findAllByIdInAndUserId(List<Long> ids, Long userId);

    /**
     * 根据广告状态查询
     * @param planStatus
     * @return
     */
    List<AdPlan> findAllByPlanStatus(Integer planStatus);

    /**
     * 根据广告主Id和广告计划名称查询
     * @param userId
     * @param planName
     * @return
     */
    AdPlan findByUserIdAndPlanName(Long userId, String planName);

}
