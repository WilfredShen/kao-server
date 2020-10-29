package com.kao.server.entity;

import java.sql.Date;

public class FavoriteTutorVo {

    private String stu_cid;
    private String stu_sid;
    private Integer tut_tid;
    private String tut_cid;
    private Date date;

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

    public Integer getTut_tid() {
        return tut_tid;
    }

    public void setTut_tid(Integer tut_tid) {
        this.tut_tid = tut_tid;
    }

    public String getTut_cid() {
        return tut_cid;
    }

    public void setTut_cid(String tut_cid) {
        this.tut_cid = tut_cid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
