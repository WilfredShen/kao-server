package com.kao.server.mapper;

/**
 * @author 沈伟峰
 */
public interface VerificationMapper {

    Integer realAuth(Integer uid, String identity, String name) throws Exception;

    Integer studentAuth(Integer uid, String cid, String sid) throws Exception;

    Integer studentAuthVerified(Integer uid) throws Exception;

    Integer tutorAuth(Integer uid, String cid, String tid) throws Exception;

    Integer tutorAuthVerified(Integer uid) throws Exception;
}
