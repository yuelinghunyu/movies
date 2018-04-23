package com.jdj.movie.mapper;

import com.jdj.movie.model.Sales;
import org.apache.ibatis.jdbc.SQL;

public class SalesSqlProvider {

    public String insertSelective(Sales record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sales");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getWechatId() != null) {
            sql.VALUES("wechat_id", "#{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getMovieId() != null) {
            sql.VALUES("movie_id", "#{movieId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Sales record) {
        SQL sql = new SQL();
        sql.UPDATE("sales");
        
        if (record.getWechatId() != null) {
            sql.SET("wechat_id = #{wechatId,jdbcType=VARCHAR}");
        }
        
        if (record.getMovieId() != null) {
            sql.SET("movie_id = #{movieId,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}