package com.kao.server.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author 全鸿润
 */
public class CollegeRankBase implements Serializable {

    private String cid;
    private String rid;
    private Date date;
    private Integer rank;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
