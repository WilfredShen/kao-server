package com.kao.server.dto;

/**
 * @author 全鸿润
 */
public class AcceptanceRateMessage {

    private String cid;
    private Integer year;
    private Integer total;
    private Integer enrollment;
    private Integer exemption;
    private Double exemptionRate;
    private Double enrollmentRate;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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

    public Double getExemptionRate() {
        return exemptionRate;
    }

    public void setExemptionRate(Double exemptionRate) {
        this.exemptionRate = exemptionRate;
    }

    public Double getEnrollmentRate() {
        return enrollmentRate;
    }

    public void setEnrollmentRate(Double enrollmentRate) {
        this.enrollmentRate = enrollmentRate;
    }
}
