package com.ms.cas.entity;

import com.ms.cas.constant.EnableEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户
 * Created by wuketao on 2018/1/30.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(indexes = {@Index(name="index_userName",unique = false,columnList = "userName"),
    @Index(name = "index_orgId",unique = false,columnList = "orgId")})
public class User {

    /**
     * 登录名
     */
    @Id
    @Column(length = 100)
    private String loginName;

    /**
     * 密码
     */
    @Column(nullable = false, length = 36)
    private String password;

    /**
     * 真实姓名
     */
    @Column(nullable = false, length = 20)
    private String userName;

    /**
     * 组织结构id
     */
    @Column(nullable = false, length = 100)
    private String orgId;

    /**
     * 机构名称
     */
    @Column(nullable = false, length = 100)
    private String orgName;

    /**
     * 是否有效
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnableEnum enable;

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
