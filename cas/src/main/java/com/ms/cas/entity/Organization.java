package com.ms.cas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 组织机构
 * Created by wuketao on 2018/1/29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "index_orgName", unique = true, columnList = "orgName"),
        @Index(name = "index_parentOrgId", unique = false, columnList = "parentOrgId"),
        @Index(name = "index_address", unique = false, columnList = "address"),
        @Index(name = "index_cityCode", unique = false, columnList = "cityCode"),
        @Index(name = "index_cityName", unique = false, columnList = "cityName")})
public class Organization {

    /**
     * 机构id
     */
    @Id
    @Column(length = 100)
    private String orgId;

    /**
     * 机构名称
     */
    @Column(nullable = false, length = 100)
    private String orgName;

    /**
     * 父级机构id
     */
    @Column(length = 100)
    private String parentOrgId;

    /**
     * 机构地址
     */
    @Column(length = 100)
    private String address;

    /**
     * 城市编码
     */
    @Column(length = 100)
    private String cityCode;

    /**
     * 城市名称
     */
    @Column(length = 100)
    private String cityName;

    /**
     * 创建人
     */
    @Column(nullable = false, length = 50)
    private String creator;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Date createTime;
}
