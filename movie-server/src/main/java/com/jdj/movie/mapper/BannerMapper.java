package com.jdj.movie.mapper;

import com.jdj.movie.model.Banner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface BannerMapper {
    @Delete({
        "delete from banner",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into banner (id, title, ",
        "type, href, redirect, ",
        "create_time)",
        "values (#{id,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{href,jdbcType=VARCHAR}, #{redirect,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(Banner record);

    @InsertProvider(type=BannerSqlProvider.class, method="insertSelective")
    int insertSelective(Banner record);

    @Select({
        "select",
        "id, title, type, href, redirect, create_time",
        "from banner",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="href", property="href", jdbcType=JdbcType.VARCHAR),
        @Result(column="redirect", property="redirect", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Banner selectByPrimaryKey(String id);

    @UpdateProvider(type=BannerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Banner record);

    @Update({
        "update banner",
        "set title = #{title,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "href = #{href,jdbcType=VARCHAR},",
          "redirect = #{redirect,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Banner record);
}