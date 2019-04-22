package com.youzan.ad.entity;

import com.youzan.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 推广单元表
 */
@Entity
@Data
@Table(name = "ad_unit")
@AllArgsConstructor
@NoArgsConstructor
public class AdUnit {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联推广计划Id
     */
    @Column(name = "plan_id", nullable = false)
    private Long planId;

    /**
     * 推广单元名称
     */
    @Column(name = "unit_name", nullable = false)
    private String unitName;

    /**
     * 推广单元状态
     */
    @Column(name = "unit_status", nullable = false)
    private Integer unitStatus;

    /**
     * 广告位类型
     */
    @Column(name = "position_type", nullable = false)
    private Integer positionType;

    /**
     * 预算
     */
    @Column(name = "budget", nullable = false)
    private Long budget;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public AdUnit(Long planId, String unitName, Integer positionType, Long budget) {
        this.planId = planId;
        this.unitName = unitName;
        this.positionType = positionType;
        this.budget = budget;
        this.createTime = new Date();
        this.updateTime = new Date();
        this.unitStatus = CommonStatus.VALID.getStatus();
    }
}
