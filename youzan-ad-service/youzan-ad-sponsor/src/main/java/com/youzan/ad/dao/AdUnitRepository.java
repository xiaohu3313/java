package com.youzan.ad.dao;

import com.youzan.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {

    /**
     * 根据广告计划和推广单元名称查询推广单元明细
     * @param planId
     * @param unitName
     * @return
     */
    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);

    /**
     * 根据广告推广单元状态查询
     * @param status
     * @return
     */
    List<AdUnit> findAllByUnitStatus(Integer status);
}
