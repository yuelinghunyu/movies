package com.jdj.movie.mapper;

import com.jdj.movie.model.Blog;
import org.apache.ibatis.jdbc.SQL;

public class BlogSqlProvider {

    public String insertSelective(Blog record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("blog");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getBlogType() != null) {
            sql.VALUES("blog_type", "#{blogType,jdbcType=INTEGER}");
        }
        
        if (record.getHref() != null) {
            sql.VALUES("href", "#{href,jdbcType=VARCHAR}");
        }
        
        if (record.getTimes() != null) {
            sql.VALUES("times", "#{times,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Blog record) {
        SQL sql = new SQL();
        sql.UPDATE("blog");
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getBlogType() != null) {
            sql.SET("blog_type = #{blogType,jdbcType=INTEGER}");
        }
        
        if (record.getHref() != null) {
            sql.SET("href = #{href,jdbcType=VARCHAR}");
        }
        
        if (record.getTimes() != null) {
            sql.SET("times = #{times,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}