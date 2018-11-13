package com.jdj.movie.mapper;

import com.jdj.movie.model.Banner;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface BannerMapper {
    @Delete({
        "delete from banner",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into banner (id, title,",
        "type, href, redirect, ",
        "create_time)",
        "values (UUID(), #{title,jdbcType=VARCHAR},#{type,jdbcType=INTEGER},",
        "#{href,jdbcType=VARCHAR}, #{redirect,jdbcType=VARCHAR},",
        "now())"
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

    /**
     *
     * @param skip,limit，type
     * @return list
     */
    @Select({
            "<script>",
            "select",
            "id, title, type, href, redirect, create_time",
            "from banner",
            "where 1=1",
            "<if test='type != null and type != &apos;&apos;'>",
            "and type = #{type,jdbcType=INTEGER}",
            "</if>",
            "order by create_time asc",
            "limit #{skip},#{limit}",
            "</script>"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="href", property="href", jdbcType=JdbcType.VARCHAR),
            @Result(column="redirect", property="redirect", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Banner> getBannerList(@Param("type") int type,@Param("skip") int skip,@Param("limit") int limit);

    /**
     *
     * @param type
     * @return
     */

    @Select({
            "<script>",
            "select count(0)",
            "from banner",
            "where 1=1",
            "<if test='type != null'>",
            "and type = #{type,jdbcType=INTEGER}",
            "</if>",
            "</script>"
    })
    int getBannerCount(@Param("type") Integer type);

    @UpdateProvider(type=BannerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Banner record);

    @Update({
        "update banner",
        "set title = #{title,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "href = #{href,jdbcType=VARCHAR},",
          "redirect = #{redirect,jdbcType=VARCHAR},",
          "create_time = now()",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Banner record);
}