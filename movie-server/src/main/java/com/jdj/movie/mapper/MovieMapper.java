package com.jdj.movie.mapper;

import com.jdj.movie.model.Movie;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface MovieMapper {
    @Delete({
        "delete from movie",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into movie (id, area, ",
        "pic_url, content, ",
        "title, type, price, ",
        "count, movie_type, ",
        "is_free, create_time, ",
        "modify_time, description)",
        "values (#{id,jdbcType=VARCHAR}, #{area,jdbcType=INTEGER}, ",
        "#{picUrl,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, #{price,jdbcType=DECIMAL}, ",
        "#{count,jdbcType=INTEGER}, #{movieType,jdbcType=TINYINT}, ",
        "#{isFree,jdbcType=TINYINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{description,jdbcType=LONGVARCHAR})"
    })
    int insert(Movie record);

    @InsertProvider(type=MovieSqlProvider.class, method="insertSelective")
    int insertSelective(Movie record);

    @Select({
        "select",
        "id, area, pic_url, content, title, type, price, count, movie_type, is_free, ",
        "create_time, modify_time, description",
        "from movie",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="area", property="area", jdbcType=JdbcType.INTEGER),
        @Result(column="pic_url", property="picUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="movie_type", property="movieType", jdbcType=JdbcType.TINYINT),
        @Result(column="is_free", property="isFree", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    Movie selectByPrimaryKey(String id);

    @UpdateProvider(type=MovieSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Movie record);

    @Update({
        "update movie",
        "set area = #{area,jdbcType=INTEGER},",
          "pic_url = #{picUrl,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "count = #{count,jdbcType=INTEGER},",
          "movie_type = #{movieType,jdbcType=TINYINT},",
          "is_free = #{isFree,jdbcType=TINYINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(Movie record);

    @Update({
        "update movie",
        "set area = #{area,jdbcType=INTEGER},",
          "pic_url = #{picUrl,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "count = #{count,jdbcType=INTEGER},",
          "movie_type = #{movieType,jdbcType=TINYINT},",
          "is_free = #{isFree,jdbcType=TINYINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Movie record);
}