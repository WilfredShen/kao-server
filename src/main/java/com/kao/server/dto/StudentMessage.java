package com.kao.server.dto;

import cn.hutool.core.lang.Range;

import java.sql.Date;
import java.sql.Timestamp;

public class StudentMessage {

    private Integer uid;
    private String phone;
    private String email;
    private Date graduationDate;
    private String expectedMajor;
    private Boolean queryable;
    private String major;
    private String name;
    private String college;
    private Integer age;
    private boolean sex;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getExpectedMajor() {
        return expectedMajor;
    }

    public void setExpectedMajor(String expectedMajor) {
        this.expectedMajor = expectedMajor;
    }

    public Boolean getQueryable() {
        return queryable;
    }

    public void setQueryable(Boolean queryable) {
        this.queryable = queryable;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
