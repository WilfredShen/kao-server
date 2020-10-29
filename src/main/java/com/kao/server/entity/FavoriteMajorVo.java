package com.kao.server.entity;

import java.sql.Timestamp;

public class FavoriteMajorVo {

    private String major_cid;
    private Integer major_mid;
    private String stu_cid;
    private String stu_sid;
    private Timestamp date;

    public String getMajor_cid() {
        return major_cid;
    }

    public void setMajor_cid(String major_cid) {
        this.major_cid = major_cid;
    }

    public Integer getMajor_mid() {
        return major_mid;
    }

    public void setMajor_mid(Integer major_mid) {
        this.major_mid = major_mid;
    }

    public String getStu_cid() {
        return stu_cid;
    }

    public void setStu_cid(String stu_cid) {
        this.stu_cid = stu_cid;
    }

    public String getStu_sid() {
        return stu_sid;
    }

    public void setStu_sid(String stu_sid) {
        this.stu_sid = stu_sid;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
