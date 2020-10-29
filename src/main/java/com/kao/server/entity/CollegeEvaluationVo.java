package com.kao.server.entity;

public class CollegeEvaluationVo {

    private String cid;
    private String cname;
    private String mid;
    private String mname;
    private String dname;
    private Integer round;
    private String result;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CollegeEvaluationVo{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", mid='" + mid + '\'' +
                ", mname='" + mname + '\'' +
                ", dname='" + dname + '\'' +
                ", round=" + round +
                ", result='" + result + '\'' +
                '}';
    }
}
