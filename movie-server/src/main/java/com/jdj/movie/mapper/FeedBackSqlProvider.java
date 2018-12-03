package com.jdj.movie.mapper;

import com.jdj.movie.model.FeedBack;
import org.apache.ibatis.jdbc.SQL;

public class FeedBackSqlProvider {

    public String insertSelective(FeedBack record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("feed_back");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatId() != null) {
            sql.VALUES("wechat_id", "#{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatName() != null) {
            sql.VALUES("wechat_name", "#{wechatName,jdbcType=VARCHAR}");
        }
        
        if (record.getFeedType() != null) {
            sql.VALUES("feed_type", "#{feedType,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FeedBack record) {
        SQL sql = new SQL();
        sql.UPDATE("feed_back");
        
        if (record.getWechatId() != null) {
            sql.SET("wechat_id = #{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatName() != null) {
            sql.SET("wechat_name = #{wechatName,jdbcType=VARCHAR}");
        }
        
        if (record.getFeedType() != null) {
            sql.SET("feed_type = #{feedType,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}