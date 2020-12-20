package com.kao.server.mapper;

/**
 * @author 沈伟峰
 */
public interface VerificationMapper {

    /**
     * 实名认证
     *
     * @param uid      用户编号
     * @param identity 身份证号
     * @param name     姓名
     * @return 影响的记录数
     */
    Integer realAuth(Integer uid, String identity, String name) throws Exception;

    /**
     * 学生提交认证
     *
     * @param uid 用户编号
     * @param cid 学校编号
     * @param sid 学生编号
     * @return 影响的记录数
     */
    Integer studentAuth(Integer uid, String cid, String sid) throws Exception;

    /**
     * 学生认证通过
     *
     * @param uid 用户编号
     * @return 影响的记录数
     */
    Integer studentAuthVerified(Integer uid) throws Exception;

    /**
     * 导师提交认证
     *
     * @param uid 用户编号
     * @param cid 学校编号
     * @param tid 导师编号
     * @return 影响的记录数
     */
    Integer tutorAuth(Integer uid, String cid, String tid) throws Exception;

    /**
     * 导师认证通过
     *
     * @param uid 用户编号
     * @return 影响的记录数
     */
    Integer tutorAuthVerified(Integer uid) throws Exception;
}
