package com.kao.server.dto;

import java.io.Serializable;

/**
 * @author 沈伟峰
 */
public class EvaluationBase implements Serializable {

    private String cid;
    private String mid;
    private String result;
    private Integer round;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }
}
