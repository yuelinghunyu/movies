package com.jdj.movie.model;

import java.util.Date;

public class Users {
    private String id;

    private String wechatId;

    private String wechatName;

    private String wechatLogo;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId == null ? null : wechatId.trim();
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName == null ? null : wechatName.trim();
    }

    public String getWechatLogo() {
        return wechatLogo;
    }

    public void setWechatLogo(String wechatLogo) {
        this.wechatLogo = wechatLogo == null ? null : wechatLogo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}