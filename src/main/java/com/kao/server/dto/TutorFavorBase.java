package com.kao.server.dto;

import java.io.Serializable;

/**
 * @author 沈伟峰
 */
public class TutorFavorBase implements Serializable {

    private String tutCid;
    private String tutTid;

    public String getTutCid() {
        return tutCid;
    }

    public void setTutCid(String tutCid) {
        this.tutCid = tutCid;
    }

    public String getTutTid() {
        return tutTid;
    }

    public void setTutTid(String tutTid) {
        this.tutTid = tutTid;
    }
}
