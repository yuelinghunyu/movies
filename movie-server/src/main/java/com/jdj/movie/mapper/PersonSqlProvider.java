package com.jdj.movie.mapper;

import com.jdj.movie.model.Person;
import org.apache.ibatis.jdbc.SQL;

public class PersonSqlProvider {

    public String insertSelective(Person record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("person");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getUserName() != null) {
            sql.VALUES("user_name", "#{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPassWord() != null) {
            sql.VALUES("pass_word", "#{passWord,jdbcType=VARCHAR}");
        }
        
        if (record.getImg() != null) {
            sql.VALUES("img", "#{img,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.VALUES("create_date", "#{createDate,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Person record) {
        SQL sql = new SQL();
        sql.UPDATE("person");
        
        if (record.getUserName() != null) {
            sql.SET("user_name = #{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPassWord() != null) {
            sql.SET("pass_word = #{passWord,jdbcType=VARCHAR}");
        }
        
        if (record.getImg() != null) {
            sql.SET("img = #{img,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            sql.SET("create_date = #{createDate,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}