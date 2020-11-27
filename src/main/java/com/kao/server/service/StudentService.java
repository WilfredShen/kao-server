package com.kao.server.service;

import com.kao.server.dto.StudentMessage;

import java.sql.Date;

public interface StudentService {
    public StudentMessage getstuMsg(int uid);

    public Integer updatePhone(int uid, String phone);

    public Integer updateEmail(int uid, String email);

    public Integer updateAccountType(int uid, String accountType);

    public Integer updateCollege(int uid, String college);

    public Integer updateMajor(int uid, String major);

    public Integer updateGraduateDate(int uid, Date graduationDate);

    public Integer updateExpectedMajor(int uid, String expectedMajor);

    public Integer updateQueryable(int uid, boolean queryable);
}
