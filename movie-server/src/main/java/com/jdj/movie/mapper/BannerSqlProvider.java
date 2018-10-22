package com.jdj.movie.mapper;

import com.jdj.movie.model.Banner;
import org.apache.ibatis.jdbc.SQL;

public class BannerSqlProvider {

    public String insertSelective(Banner record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("banner");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getHref() != null) {
            sql.VALUES("href", "#{href,jdbcType=VARCHAR}");
        }
        
        if (record.getRedirect() != null) {
            sql.VALUES("redirect", "#{redirect,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Banner record) {
        SQL sql = new SQL();
        sql.UPDATE("banner");
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        if (record.getHref() != null) {
            sql.SET("href = #{href,jdbcType=VARCHAR}");
        }
        
        if (record.getRedirect() != null) {
            sql.SET("redirect = #{redirect,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}