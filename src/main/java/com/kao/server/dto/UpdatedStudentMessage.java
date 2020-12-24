package com.kao.server.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author 全鸿润
 */
public class UpdatedStudentMessage implements Serializable {

    private String phoneNumber;
    private String email;
    private String college;
    private String major;
    private String expectedMajor;
    private Date graduationDate;
    private Boolean queryable;

    public String getEmail() {
        return email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
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

    public String getExpectedMajor() {
        return expectedMajor;
    }

    public void setExpectedMajor(String expectedMajor) {
        this.expectedMajor = expectedMajor;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public Boolean getQueryable() {
        return queryable;
    }

    public void setQueryable(Boolean queryable) {
        this.queryable = queryable;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UpdatedStudentMessage{" +
                "phone='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", expectedMajor='" + expectedMajor + '\'' +
                ", graduationDate=" + graduationDate +
                ", queryable=" + queryable +
                '}';
    }
}
