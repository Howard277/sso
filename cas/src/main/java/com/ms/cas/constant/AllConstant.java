package com.ms.cas.constant;

/**
 * 常量定义
 * 
 * @author wuketao
 *
 */
public class AllConstant {

    /**
     * 授权id
     */
    public static String AUTHID = "authid";
    /**
     * DES密码
     */
    public static String DESKEY = "123456781234567812345678";
    /**
     * 过期时间，单位是分钟。
     */
    public static Integer EXPIRETIME = 30;
    /**
     * URI跟路径
     */
    public static String URIROOTPATH = "/uam/";
    /**
     * 用户信息在session中的key
     */
    public static String USERKEY = "user";
    /**
     * redis中保存ticket的map集合的key
     */
    public static String TICKETS = "TICKETS";
}
