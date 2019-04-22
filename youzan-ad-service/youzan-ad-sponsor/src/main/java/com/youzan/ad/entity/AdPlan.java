package com.youzan.ad.entity;

import com.youzan.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 推广计划表
 */
@Entity
@Data
@Table(name = "ad_plan")
@AllArgsConstructor
@NoArgsConstructor
public class AdPlan {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标记当前记录所属同用户
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 推广计划名称
     */
    @Column(name = "plan_name", nullable = false)
    private String planName;

    /**
     * 推广计划状态
     */
    @Column(name = "plan_status", nullable = false)
    private Integer planStatus;

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

    /**
     * 推广计划开始时间
     */
    @Column(name = "start_time", nullable = false)
    private Date startTime;

    /**
     * 推广计划结束时间
     */
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    public AdPlan(Long userId, String planName, Date startTime, Date endTime) {
        this.userId = userId;
        this.planName = planName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.planStatus = CommonStatus.VALID.getStatus();
        this.updateTime = new Date();
        this.createTime = new Date();
    }
}
