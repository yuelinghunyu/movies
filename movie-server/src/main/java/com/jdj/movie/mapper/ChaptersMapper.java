package com.jdj.movie.mapper;

import com.jdj.movie.model.Chapters;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ChaptersMapper {
    @Delete({
        "delete from chapters",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into chapters (id, title, ",
        "href, time, browser, ",
        "create_time, modify_time)",
        "values (#{id,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{href,jdbcType=VARCHAR}, #{time,jdbcType=VARCHAR}, #{browser,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP})"
    })
    int insert(Chapters record);

    @InsertProvider(type=ChaptersSqlProvider.class, method="insertSelective")
    int insertSelective(Chapters record);

    @Select({
        "select",
        "id, title, href, time, browser, create_time, modify_time",
        "from chapters",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="href", property="href", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.VARCHAR),
        @Result(column="browser", property="browser", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Chapters selectByPrimaryKey(String id);

    @UpdateProvider(type=ChaptersSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Chapters record);

    @Update({
        "update chapters",
        "set title = #{title,jdbcType=VARCHAR},",
          "href = #{href,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=VARCHAR},",
          "browser = #{browser,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Chapters record);
}