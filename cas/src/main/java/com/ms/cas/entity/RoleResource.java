package com.ms.cas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 角色-资源关系表
 * Created by wuketao on 2018/1/30.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {@Index(name = "index_appCode_roleCode_resourcePath", unique = true, columnList = "appCode,roleCode,resourcePath")})
public class RoleResource {
    /**
     * 角色-资源关系id 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 应用系统编码
     */
    @Column(nullable = false, length = 100)
    private String appCode;

    /**
     * 角色编码
     */
    @Column(nullable = false, length = 100)
    private String roleCode;

    /**
     * 资源路径
     */
    @Column(nullable = false, length = 200)
    private String resourcePath;

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
