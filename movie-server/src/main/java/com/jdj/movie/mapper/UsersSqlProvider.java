package com.jdj.movie.mapper;

import com.jdj.movie.model.Users;
import org.apache.ibatis.jdbc.SQL;

public class UsersSqlProvider {

    public String insertSelective(Users record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("users");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatId() != null) {
            sql.VALUES("wechat_id", "#{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatName() != null) {
            sql.VALUES("wechat_name", "#{wechatName,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatLogo() != null) {
            sql.VALUES("wechat_logo", "#{wechatLogo,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Users record) {
        SQL sql = new SQL();
        sql.UPDATE("users");
        
        if (record.getWechatId() != null) {
            sql.SET("wechat_id = #{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatName() != null) {
            sql.SET("wechat_name = #{wechatName,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatLogo() != null) {
            sql.SET("wechat_logo = #{wechatLogo,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}