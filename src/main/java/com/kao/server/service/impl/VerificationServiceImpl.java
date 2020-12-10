package com.kao.server.service.impl;

import com.kao.server.entity.User;
import com.kao.server.mapper.UserMapper;
import com.kao.server.mapper.VerificationMapper;
import com.kao.server.service.VerificationService;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.verification.VerificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 沈伟峰
 */
@Service
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    private VerificationMapper verificationMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer realAuth(Integer uid, String identity, String name) {
        int status;
        boolean flag = VerificationUtil.realAuth(identity, name);
        if (flag) {
            try {
                verificationMapper.realAuth(uid, identity, name);
                status = JsonResultStatus.SUCCESS;
            } catch (Exception e) {
                status = JsonResultStatus.FAIL;
            }
        } else {
            status = JsonResultStatus.REAL_AUTH_FAILED;
        }
        return status;
    }

    @Override
    public Integer studentAuth(Integer uid, String cid, String sid) {
        int status;
        User user = userMapper.findUserByUserId(uid);
        if (user.getAccountType() != null) {
            status = JsonResultStatus.FAIL;
            return status;
        }
        try {
            verificationMapper.studentAuth(uid, cid, sid);
        } catch (Exception e) {
            e.printStackTrace();
            status = JsonResultStatus.FAIL;
            return status;
        }
        boolean flag = VerificationUtil.studentAuth(cid, sid);
        if (flag) {
            try {
                verificationMapper.studentAuthVerified(uid);
                status = JsonResultStatus.SUCCESS;
            } catch (Exception e) {
                status = JsonResultStatus.FAIL;
            }
        } else {
            status = JsonResultStatus.STUDENT_AUTH_FAILED;
        }
        return status;
    }

    @Override
    public Integer tutorAuth(Integer uid, String cid, String tid) {
        int status;
        User user = userMapper.findUserByUserId(uid);
        if (user.getAccountType() != null) {
            status = JsonResultStatus.FAIL;
            return status;
        }
        try {
            verificationMapper.tutorAuth(uid, cid, tid);
        } catch (Exception e) {
            status = JsonResultStatus.FAIL;
            return status;
        }
        boolean flag = VerificationUtil.tutorAuth(cid, tid);
        if (flag) {
            try {
                verificationMapper.tutorAuthVerified(uid);
                status = JsonResultStatus.SUCCESS;
            } catch (Exception e) {
                status = JsonResultStatus.FAIL;
            }
        } else {
            status = JsonResultStatus.TUTOR_AUTH_FAILED;
        }
        return status;
    }
}
