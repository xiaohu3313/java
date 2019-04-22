package com.youzan.ad.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 创意
 */
@Data
@Entity
@Table(name = "ad_creative")
@AllArgsConstructor
@NoArgsConstructor
public class AdCreative {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创意名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 物料类型
     */
    @Column(name = "type", nullable = false)
    private Integer type;

    /**
     * 物料子类型
     */
    @Column(name = "material_type", nullable = false)
    private Integer materialType;

    /**
     * 高度
     */
    @Column(name = "height", nullable = false)
    private Integer height;

    /**
     * 宽度
     */
    @Column(name = "width", nullable = false)
    private Integer width;

    /**
     * 物料大小，单位KB
     */
    @Column(name = "size", nullable = false)
    private Long size;

    /**
     * 持续时间
     */
    @Column(name = "duration", nullable = false)
    private Integer duration;

    /**
     * 审核状态
     */
    @Column(name = "audit_status", nullable = false)
    private Integer auditStatus;

    /**
     * 标记当前所属用户
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 物料地址
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 更行时间
     */
    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}
