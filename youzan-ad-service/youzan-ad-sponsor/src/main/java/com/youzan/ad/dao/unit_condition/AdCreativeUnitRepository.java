package com.youzan.ad.dao.unit_condition;

import com.youzan.ad.entity.unit_condition.AdCreativeUnit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 创意与推广单元关联
 */
public interface AdCreativeUnitRepository extends JpaRepository<AdCreativeUnit,Long> {
}
