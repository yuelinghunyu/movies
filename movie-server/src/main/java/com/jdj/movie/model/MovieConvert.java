package com.jdj.movie.model;

import com.jdj.movie.enums.StaticTypes;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jiangdajun on 2018/7/13.
 */
public class MovieConvert {
    private String id;

    private Integer area;

    private String areaTitle;

    private String picUrl;

    private String content;

    private String title;

    private String actor;

    private Integer type;

    private String typeTitle;

    private BigDecimal price;

    private Integer count;

    private StaticTypes movieType;

    private Integer isFree;

    private String isFreeTitle;

    private Date createTime;

    private Date modifyTime;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAreaTitle() {
        return areaTitle;
    }

    public void setAreaTitle(String areaTitle) {
        this.areaTitle = areaTitle;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public StaticTypes getMovieType() {
        return movieType;
    }

    public void setMovieType(StaticTypes movieType) {
        this.movieType = movieType;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public String getIsFreeTitle() {
        return isFreeTitle;
    }

    public void setIsFreeTitle(String isFreeTitle) {
        this.isFreeTitle = isFreeTitle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
