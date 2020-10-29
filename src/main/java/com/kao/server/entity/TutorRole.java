package com.kao.server.entity;

public class TutorRole {

    private String cid;
    private String tid;
    private String mid;
    private Integer uid;
    private String contact_phone;
    private String contact_email;
    private String rsearch;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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

    public String getRsearch() {
        return rsearch;
    }

    public void setRsearch(String rsearch) {
        this.rsearch = rsearch;
    }
}
