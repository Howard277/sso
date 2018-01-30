package com.ms.cas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 城市
 * Created by wuketao on 2018/1/30.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {@Index(name = "index_cityName",unique = false,columnList = "cityName"),
        @Index(name = "index_levelCode",unique = false,columnList = "levelCode"),
        @Index(name = "index_levelName",unique = false,columnList = "levelName"),})
public class City {
    /**
     * 城市编码
     */
    @Id
    @Column(length = 20)
    private String cityCode;

    /**
     * 城市名称
     */
    @Column(nullable = false,length = 100)
    private String cityName;

    /**
     * 城市等级编码
     */
    @Column(nullable = false,length = 100)
    private String levelCode;

    /**
     * 城市等级名称
     */
    @Column(nullable = false,length = 100)
    private String levelName;
}
