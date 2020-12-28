package com.kao.server.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 全鸿润
 */
public class Admin implements Serializable {

    private Integer adminId;
    private String username;
    private String password;
    private String salt;
    private Timestamp registerTime;
    private Timestamp latestLoginTime;
    private String latestLoginLocation;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Timestamp getLatestLoginTime() {
        return latestLoginTime;
    }

    public void setLatestLoginTime(Timestamp latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
    }

    public String getLatestLoginLocation() {
        return latestLoginLocation;
    }

    public void setLatestLoginLocation(String latestLoginLocation) {
        this.latestLoginLocation = latestLoginLocation;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", registerTime=" + registerTime +
                ", latestLoginTime=" + latestLoginTime +
                ", latestLoginLocation='" + latestLoginLocation + '\'' +
                '}';
    }
}
