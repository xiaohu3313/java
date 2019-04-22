package com.youzan.ad.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 关键字限制
 */
@Data
@Entity
@Table(name = "ad_unit_keyword")
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    @Basic
    @Column(name = "keyword", nullable = false)
    private String keyword;

    public AdUnitKeyword(Long unitId, String keyword) {
        this.unitId = unitId;
        this.keyword = keyword;
    }
}
