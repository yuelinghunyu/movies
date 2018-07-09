package com.jdj.movie.mapper;

import com.jdj.movie.enums.StaticTypes;
import com.jdj.movie.model.Movie;
import com.jdj.movie.typesHandlers.MovieTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
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
        "#{count,jdbcType=INTEGER}, #{movieType,jdbcType=INTEGER,typeHandler = com.jdj.movie.typesHandlers.MovieTypeHandler}, ",
        "#{isFree,jdbcType=INTEGER}, now(), ",
        "now(), #{description,jdbcType=LONGVARCHAR})"
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
        @Result(column="movie_type", property="movieType", jdbcType=JdbcType.INTEGER,typeHandler = MovieTypeHandler.class),
        @Result(column="is_free", property="isFree", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    Movie selectByPrimaryKey(String id);

    /**
     * @content 查询所有数据，返回list
     * @param null
     * @return movieList
     */
    @Select({
            "<script>",
            "select",
            "id, area, pic_url, content, title, type, price, count, movie_type, is_free, ",
            "create_time, modify_time, description",
            "from movie",
            "where 1=1",
            "<if test='id!=null and id!= &apos;&apos;'>",
            "and id = #{id,jdbcType=VARCHAR}",
            "</if>",
            "<if test='area!=-1'>",
            "and area = #{area,jdbcType=INTEGER}",
            "</if>",
            "<if test='title!=null and title!= &apos;&apos;'>",
            "and title = #{title,jdbcType=VARCHAR}",
            "</if>",
            "<if test='type!= -1'>",
            "and type = #{type,jdbcType=INTEGER}",
            "</if>",
            "<if test='movieType!= -1'>",
            "and movie_type = #{movieType,jdbcType=VARCHAR}",
            "</if>",
            "limit #{skip},#{limit}",
            "</script>"
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
            @Result(column="movie_type", property="movieType", jdbcType=JdbcType.INTEGER,typeHandler = MovieTypeHandler.class),
            @Result(column="is_free", property="isFree", jdbcType=JdbcType.INTEGER),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Movie> movieList(
            @Param("id") String id,
            @Param("area") int area,
            @Param("title") String title,
            @Param("type") int type,
            @Param("movieType") int movieType,
            @Param("skip") int skip,
            @Param("limit") int limit
    );

    /**
     *
     * @param null
     * @return count
     */

    @Select({
            "<script>",
            "select count(0)",
            "from movie",
            "where 1=1",
            "<if test='id!=null and id!= &apos;&apos;'>",
            "and id = #{id,jdbcType=VARCHAR}",
            "</if>",
            "<if test='area!=-1'>",
            "and area = #{area,jdbcType=INTEGER}",
            "</if>",
            "<if test='title!=null and title!= &apos;&apos;'>",
            "and title = #{title,jdbcType=VARCHAR}",
            "</if>",
            "<if test='type!= -1'>",
            "and type = #{type,jdbcType=INTEGER}",
            "</if>",
            "<if test='movieType!= -1'>",
            "and movie_type = #{movieType,jdbcType=VARCHAR}",
            "</if>",
            "</script>"
    })
    int getMoviesCount(
            @Param("id") String id,
            @Param("area") int area,
            @Param("title") String title,
            @Param("type") int type,
            @Param("movieType") int movieType
    );
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
          "movie_type = #{movieType,jdbcType=INTEGER,typeHandler = com.jdj.movie.typesHandlers.MovieTypeHandler},",
          "is_free = #{isFree,jdbcType=INTEGER},",
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
          "movie_type = #{movieType,jdbcType=INTEGER,typeHandler = com.jdj.movie.typesHandlers.MovieTypeHandler},",
          "is_free = #{isFree,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = now()",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Movie record);
}