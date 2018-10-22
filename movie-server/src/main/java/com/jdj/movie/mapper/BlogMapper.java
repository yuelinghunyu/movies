package com.jdj.movie.mapper;

import com.jdj.movie.model.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface BlogMapper {
    @Delete({
        "delete from blog",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into blog (id, title, ",
        "blog_type, href, ",
        "times, create_time)",
        "values (#{id,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{blogType,jdbcType=INTEGER}, #{href,jdbcType=VARCHAR}, ",
        "#{times,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(Blog record);

    @InsertProvider(type=BlogSqlProvider.class, method="insertSelective")
    int insertSelective(Blog record);

    @Select({
        "select",
        "id, title, blog_type, href, times, create_time",
        "from blog",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="blog_type", property="blogType", jdbcType=JdbcType.INTEGER),
        @Result(column="href", property="href", jdbcType=JdbcType.VARCHAR),
        @Result(column="times", property="times", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Blog selectByPrimaryKey(String id);

    @UpdateProvider(type=BlogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Blog record);

    @Update({
        "update blog",
        "set title = #{title,jdbcType=VARCHAR},",
          "blog_type = #{blogType,jdbcType=INTEGER},",
          "href = #{href,jdbcType=VARCHAR},",
          "times = #{times,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Blog record);
}