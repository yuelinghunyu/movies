package com.jdj.movie.mapper;

import com.jdj.movie.model.Types;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

public interface TypesMapper {
    @Insert({
        "insert into types (id, type, ",
        "title, create_time, ",
        "modify_time)",
        "values (#{id,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyTime,jdbcType=TIMESTAMP})"
    })
    int insert(Types record);

    @InsertProvider(type=TypesSqlProvider.class, method="insertSelective")
    int insertSelective(Types record);
}