package com.kao.server.util.verification;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author 沈伟峰
 */
public class VerificationUtil {

    /**
     * 进行实名认证
     *
     * @param identity 身份证号
     * @param name     真实姓名
     * @return 实名认证结果<br />
     * boolean isOK: 认证是否通过<br/>
     * String name: 脱敏姓名<br/>
     * String identity: 脱敏身份证号<br/>
     * String sex: "M"表示男性，"F"表示女性<br/>
     * java.sql.Date birthday: 生日
     */
    public static Map<Object, Object> identityAuth(String identity, String name) {
        Map<Object, Object> map = null;
        try {
            map = IdentityUtil.verify(identity, name);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 学生身份认证
     *
     * @param cid 院校编号
     * @param sid 学号
     * @return 认证结果
     */
    public static boolean studentAuth(String cid, String sid) {
        return true;
    }

    /**
     * 导师身份认证
     *
     * @param cid 院校编号
     * @param tid 导师编号
     * @return 认证结果
     */
    public static boolean tutorAuth(String cid, String tid) {
        return true;
    }
}
