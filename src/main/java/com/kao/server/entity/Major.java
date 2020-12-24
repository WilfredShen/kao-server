package com.kao.server.entity;

import java.io.Serializable;

/**
 * @author 沈伟峰
 */
public class Major implements Serializable {
    private String mid;
    private String mname;
    private String did;

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

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
