package com.kao.server.dto;

import java.io.Serializable;

/**
 * @author 全鸿润
 */
public class RankBase implements Serializable {
    private String rid;
    private String rankFrom;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRankFrom() {
        return rankFrom;
    }

    public void setRankFrom(String rankFrom) {
        this.rankFrom = rankFrom;
    }
}
