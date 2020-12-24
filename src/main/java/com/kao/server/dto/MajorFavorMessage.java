package com.kao.server.dto;

import java.io.Serializable;

/**
 * @author 沈伟峰
 */
public class MajorFavorMessage implements Serializable {

    private String cid;
    private String cname;
    private String mid;
    private String mname;

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
}
