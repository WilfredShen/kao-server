package com.kao.server.dto;

import java.sql.Date;

/**
 * @author 全鸿润
 */
public class LatestCollegeRank {

    private String rankFrom;
    private String rank;
    private Date date;

    public String getRankFrom() {
        return rankFrom;
    }

    public void setRankFrom(String rankFrom) {
        this.rankFrom = rankFrom;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
