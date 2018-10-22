package com.jdj.movie.model;

import java.util.Date;

public class Payers extends PayersKey {
    private String wechatName;

    private String logo;

    private String books;

    private Date paydate;

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