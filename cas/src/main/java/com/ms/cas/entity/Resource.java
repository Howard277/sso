package com.ms.cas.entity;

import com.ms.cas.constant.EnableEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 资源
 * Created by wuketao on 2018/1/30.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "index_appCode_resourceName", unique = true, columnList = "appCode,resourceName"),
        @Index(name = "index_resourceName", unique = false, columnList = "resourceName"),
        @Index(name = "index_resourcePath", unique = false, columnList = "resourcePath")})
public class Resource {

    /**
     * 资源id 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 资源名称
     */
    @Column(length = 100)
    private String resourceName;

    /**
     * 资源路径
     */
    @Column(length = 200)
    private String resourcePath;

    /**
     * 应用系统编码
     */
    @Column(length = 100)
    private String appCode;

    /**
     * 是否有效
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnableEnum enable;

    /**
     * 备注
     */
    @Column(length = 200)
    private String comment;

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
