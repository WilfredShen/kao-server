package com.kao.server.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author 全鸿润
 */
public class LatestCollegeRank implements Serializable {

    private String rankFrom;
    private Integer rank;
    private Date date;

    public String getRankFrom() {
        return rankFrom;
    }

    public void setRankFrom(String rankFrom) {
        this.rankFrom = rankFrom;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
