package com.kao.server.dto;

/**
 * @author 全鸿润
 */
public class AdminViewEvaluation {

    private String mname;
    private String mid;
    private String cname;
    private String cid;
    private String result;

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AdminViewEvaluation{" +
                "mname='" + mname + '\'' +
                ", mid='" + mid + '\'' +
                ", cname='" + cname + '\'' +
                ", cid='" + cid + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
