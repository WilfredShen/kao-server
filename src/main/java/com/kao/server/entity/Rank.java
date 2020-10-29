package com.kao.server.entity;

public class Rank {

    private  String rid;
    private Integer admin_id;
    private String rank_from;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getRank_from() {
        return rank_from;
    }

    public void setRank_from(String rank_from) {
        this.rank_from = rank_from;
    }
}
