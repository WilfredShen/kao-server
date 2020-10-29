package com.kao.server.entity;

import java.sql.Timestamp;

public class StudentRole {

    private Integer uid;
    private String cid;
    private String sid;
    private String mid;
    private String contact_phone;
    private String contact_email;
    private String graduation_date;
    private String expected;
    private Boolean queryable;
    private Boolean verified;
    private Timestamp verified_time;

    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public Integer getUid() {
        return uid;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Timestamp getVerified_time() {
        return verified_time;
    }

    public void setVerified_time(Timestamp verified_time) {
        this.verified_time = verified_time;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getGraduation_date() {
        return graduation_date;
    }

    public void setGraduation_date(String graduation_date) {
        this.graduation_date = graduation_date;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public Boolean getQueryable() {
        return queryable;
    }

    public void setQueryable(Boolean queryable) {
        this.queryable = queryable;
    }
}
