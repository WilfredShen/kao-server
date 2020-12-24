package com.kao.server.dto;

import java.io.Serializable;

/**
 * @author 沈伟峰
 */
public class StudentId implements Serializable {
    private String uid;
    private String cid;
    private String sid;
    private String mid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
}
