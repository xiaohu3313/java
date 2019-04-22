package com.youzan.ad.entity;

import com.youzan.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户账户
 */
@Entity
@Data
@Table(name = "ad_user")
@AllArgsConstructor
@NoArgsConstructor
public class AdUser {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账户名称
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * 账户token
     */
    @Column(name = "token", nullable = false)
    private String token;

    /**
     * 账号状态
     */
    @Column(name = "user_status", nullable = false)
    private Integer userStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public AdUser(String username, String token) {
        this.username = username;
        this.token = token;
        this.userStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
