package com.kao.server.entity;

import java.io.Serializable;

/**
 * @author 沈伟峰
 */
public class Discipline implements Serializable {
    private String did;
    private String dname;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
