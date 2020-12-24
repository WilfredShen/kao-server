package com.kao.server.dto;

import java.io.Serializable;

/**
 * @author 沈伟峰
 */
public class MajorFavorBase implements Serializable {

    private String majorCid;
    private String majorMid;

    public String getMajorCid() {
        return majorCid;
    }

    public void setMajorCid(String majorCid) {
        this.majorCid = majorCid;
    }

    public String getMajorMid() {
        return majorMid;
    }

    public void setMajorMid(String majorMid) {
        this.majorMid = majorMid;
    }
}
