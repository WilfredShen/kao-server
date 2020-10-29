package com.kao.server.entity;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class User {

    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private String identity;
    private String name;
    private Date birthday;
    private Boolean sex;
    private String account_type;
    private Timestamp register_time;
    private Timestamp latest_login_time;
    private String lastest_login_location;

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

    public String getLastest_login_location() {
        return lastest_login_location;
    }

    public void setLastest_login_location(String lastest_login_location) {
        this.lastest_login_location = lastest_login_location;
    }

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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
}
