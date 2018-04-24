package com.jdj.movie.mapper;

import com.jdj.movie.model.Types;
import org.apache.ibatis.jdbc.SQL;

public class TypesSqlProvider {

    public String insertSelective(Types record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("types");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }
}