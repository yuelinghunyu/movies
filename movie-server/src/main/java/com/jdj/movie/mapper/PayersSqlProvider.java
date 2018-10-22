package com.jdj.movie.mapper;

import com.jdj.movie.model.Payers;
import org.apache.ibatis.jdbc.SQL;

public class PayersSqlProvider {

    public String insertSelective(Payers record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("payers");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatId() != null) {
            sql.VALUES("wechat_id", "#{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatName() != null) {
            sql.VALUES("wechat_name", "#{wechatName,jdbcType=VARCHAR}");
        }
        
        if (record.getLogo() != null) {
            sql.VALUES("logo", "#{logo,jdbcType=VARCHAR}");
        }
        
        if (record.getBooks() != null) {
            sql.VALUES("books", "#{books,jdbcType=VARCHAR}");
        }
        
        if (record.getPaydate() != null) {
            sql.VALUES("payDate", "#{paydate,jdbcType=DATE}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Payers record) {
        SQL sql = new SQL();
        sql.UPDATE("payers");
        
        if (record.getWechatName() != null) {
            sql.SET("wechat_name = #{wechatName,jdbcType=VARCHAR}");
        }
        
        if (record.getLogo() != null) {
            sql.SET("logo = #{logo,jdbcType=VARCHAR}");
        }
        
        if (record.getBooks() != null) {
            sql.SET("books = #{books,jdbcType=VARCHAR}");
        }
        
        if (record.getPaydate() != null) {
            sql.SET("payDate = #{paydate,jdbcType=DATE}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        sql.WHERE("wechat_id = #{wechatId,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}