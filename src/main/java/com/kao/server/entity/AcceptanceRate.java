package com.kao.server.entity;

public class AcceptanceRate {

    private String cid;
    private String date;
    //只返回年份
    private Integer year;
    private Integer total;
    private Integer enrollment;
    private Integer exemption;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Integer enrollment) {
        this.enrollment = enrollment;
    }

    public Integer getExemption() {
        return exemption;
    }

    public void setExemption(Integer exemption) {
        this.exemption = exemption;
    }
}
