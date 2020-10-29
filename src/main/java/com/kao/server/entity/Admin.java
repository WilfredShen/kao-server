package com.kao.server.entity;

import java.sql.Timestamp;

public class Admin {

    private Integer admin_id;
    private String username;
    private String password;
    private String salt;
    private Timestamp register_time;
    private Timestamp latest_login_time;
    private String lastest_login_location;

    public String getLastest_login_location() {
        return lastest_login_location;
    }

    public void setLastest_login_location(String lastest_login_location) {
        this.lastest_login_location = lastest_login_location;
    }

    public Timestamp getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Timestamp register_time) {
        this.register_time = register_time;
    }

    public Timestamp getLatest_login_time() {
        return latest_login_time;
    }

    public void setLatest_login_time(Timestamp latest_login_time) {
        this.latest_login_time = latest_login_time;
    }

    public Timestamp getLatest_login_location() {
        return latest_login_location;
    }

    public void setLatest_login_location(Timestamp latest_login_location) {
        this.latest_login_location = latest_login_location;
    }

    private Timestamp latest_login_location;

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
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
}
