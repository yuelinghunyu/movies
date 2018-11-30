package com.jdj.movie.model;

import java.util.Date;

public class Payers {
    private String id;

    private String wechatId;

    private String wechatName;

    private String logo;

    private String books;

    private Date paydate;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books == null ? null : books.trim();
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }
}