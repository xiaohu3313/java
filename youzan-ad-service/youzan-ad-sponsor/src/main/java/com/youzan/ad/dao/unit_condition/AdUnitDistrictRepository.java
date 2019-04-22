package com.youzan.ad.dao.unit_condition;

import com.youzan.ad.entity.unit_condition.AdUnitDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 地域限制
 */
public interface AdUnitDistrictRepository extends JpaRepository<AdUnitDistrict,Long> {
}
