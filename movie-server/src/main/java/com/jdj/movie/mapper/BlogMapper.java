package com.jdj.movie.mapper;

import com.jdj.movie.model.Blog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
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
        "values (UUID(), #{title,jdbcType=VARCHAR}, ",
        "#{blogType,jdbcType=INTEGER}, #{href,jdbcType=VARCHAR}, ",
        "#{times,jdbcType=INTEGER}, now())"
    })
    int insert(Blog record);

    @InsertProvider(type=BlogSqlProvider.class, method="insertSelective")
    int insertSelective(Blog record);
    //添加搜索查询和总数
    @Select({
        "<script>",
            "select",
            "id, title, blog_type, href, times, create_time",
            "from blog",
            "where 1=1",
            "<if test='id!=null and id!= &apos;&apos;'>",
                "and id = #{id,jdbcType=VARCHAR}",
            "</if>",
            "<if test='title!=null and title!= &apos;&apos;'>",
                "and title = #{title,jdbcType=VARCHAR}",
            "</if>",
            "<if test='blogType!= -1'>",
                "and blogType = #{blogType,jdbcType=INTEGER}",
            "</if>",
            "order by create_time desc",
            "limit #{skip},#{limit}",
        "</script>"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="blog_type", property="blogType", jdbcType=JdbcType.INTEGER),
            @Result(column="href", property="href", jdbcType=JdbcType.VARCHAR),
            @Result(column="times", property="times", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Blog> blogList(
            @Param("id") String id,
            @Param("title") String title,
            @Param("blogType") int blogType,
            @Param("skip") int skip,
            @Param("limit") int limit
    );


    @Select({
            "<script>",
            "select count(0)",
            "from blog",
            "where 1=1",
            "<if test='blogType!= -1'>",
                "and blogType = #{blogType,jdbcType=INTEGER}",
            "</if>",
            "</script>"
    })
    int getBlogsCount(
            @Param("blogType") int blogType
    );

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
          "create_time = now()",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Blog record);
}