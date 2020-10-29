package com.kao.server.entity;

import java.sql.Timestamp;
import java.util.Date;

public class SummerCamp {

    private String cid;
    private Date date;
    private Integer admin_id;
    private String title;
    private String content;
    private String image;
    private Timestamp update_time;
    private String official_link;

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public String getOfficial_link() {
        return official_link;
    }

    public void setOfficial_link(String official_link) {
        this.official_link = official_link;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "SummerCamp{" +
                "cid='" + cid + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
