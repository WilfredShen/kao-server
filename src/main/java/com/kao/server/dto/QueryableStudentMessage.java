package com.kao.server.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author 全鸿润
 */
public class QueryableStudentMessage implements Serializable {

    private String phone;
    private String email;
    private String major;
    private String name;
    private String college;
    private Date graduationDate;
    private String expectedMajor;
    private String level;

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
