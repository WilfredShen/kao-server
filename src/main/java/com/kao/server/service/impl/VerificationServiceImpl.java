package com.kao.server.service.impl;

import com.kao.server.mapper.VerificationMapper;
import com.kao.server.service.VerificationService;
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

    @Override
    public boolean realAuth(Integer uid, String identity, String name) {
        boolean flag = VerificationUtil.realAuth(identity, name);
        if (flag) {
            try {
                verificationMapper.realAuth(uid, identity, name);
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean studentAuth(Integer uid, String cid, String sid) {
        try {
            verificationMapper.studentAuth(uid, cid, sid);
        } catch (Exception e) {
            return false;
        }
        boolean flag = VerificationUtil.studentAuth(cid, sid);
        if (flag) {
            try {
                verificationMapper.studentAuthVerified(uid);
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

    @Override
    public boolean tutorAuth(Integer uid, String cid, String tid) {
        try {
            verificationMapper.tutorAuth(uid, cid, tid);
        } catch (Exception e) {
            return false;
        }
        boolean flag = VerificationUtil.studentAuth(cid, tid);
        if (flag) {
            try {
                verificationMapper.tutorAuthVerified(uid);
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
}
