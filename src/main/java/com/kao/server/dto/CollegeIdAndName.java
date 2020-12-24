package com.kao.server.dto;

import java.io.Serializable;

/**
 * @author 沈伟峰
 */
public class CollegeIdAndName implements Serializable {
    private String cid;
    private String cname;

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
}
