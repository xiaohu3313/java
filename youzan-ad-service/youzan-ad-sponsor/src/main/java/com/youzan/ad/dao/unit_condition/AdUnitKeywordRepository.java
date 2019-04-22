package com.youzan.ad.dao.unit_condition;

import com.youzan.ad.entity.unit_condition.AdUnitKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 关键字限制
 */
public interface AdUnitKeywordRepository extends JpaRepository<AdUnitKeyword,Long> {
}
