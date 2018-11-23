package com.jdj.movie.model;

import java.util.Date;

/**
 * Created by jiangdajun on 2018/11/22.
 */
public class BlogConvert {
    private String id;

    private String title;

    private Integer blogType;

    private String  blogTypeTitle;

    private String href;

    private Integer times;

    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBlogType() {
        return blogType;
    }

    public void setBlogType(Integer blogType) {
        this.blogType = blogType;
    }

    public String getBlogTypeTitle() {
        return blogTypeTitle;
    }

    public void setBlogTypeTitle(String blogTypeTitle) {
        this.blogTypeTitle = blogTypeTitle;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
