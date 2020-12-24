package com.kao.server.dto;

import java.io.Serializable;

/**
 * @author 全鸿润
 */
public class UpdatedUserMessage implements Serializable {
    private Integer uid;
    private String username;
    private String phone;
    private String email;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
