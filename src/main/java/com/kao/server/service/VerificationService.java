package com.kao.server.service;

/**
 * @author 沈伟峰
 */
public interface VerificationService {

    /**
     * 实名认证
     *
     * @param uid      用户编号
     * @param identity 身份证号
     * @param name     姓名
     * @return 认证结果
     */
    boolean realAuth(Integer uid, String identity, String name);

    /**
     * 学生认证
     *
     * @param uid 用户编号
     * @param cid 学校编号
     * @param sid 学生编号
     * @return 认证结果
     */
    boolean studentAuth(Integer uid, String cid, String sid);

    /**
     * 导师认证
     *
     * @param uid 用户编号
     * @param cid 学校编号
     * @param tid 导师编号
     * @return 认证结果
     */
    boolean tutorAuth(Integer uid, String cid, String tid);
}
