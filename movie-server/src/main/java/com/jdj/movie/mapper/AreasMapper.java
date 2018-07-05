package com.jdj.movie.mapper;

import com.jdj.movie.model.Areas;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface AreasMapper {
    @Delete({
        "delete from areas",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into areas (id, area, ",
        "title, create_time, ",
        "modify_time)",
        "values (UUID(), #{area,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, now(), ",
        "now())"
    })
    int insert(Areas record);

    @InsertProvider(type=AreasSqlProvider.class, method="insertSelective")
    int insertSelective(Areas record);

    @Select({
        "select",
        "id, area, title, create_time, modify_time",
        "from areas",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="area", property="area", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Areas selectByPrimaryKey(String id);

    /**
     *
     * @param null
     * @return list
     */
    @Select({
            "select",
            "id, area, title, create_time, modify_time",
            "from areas",
            "order by area asc",
            "limit #{skip},#{limit}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="area", property="area", jdbcType=JdbcType.INTEGER),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Areas> getListAreas(@Param("skip") int skip,@Param("limit") int limit);

    /**
     *
     * @param null
     * @return count
     */

    @Select({
            "select count(0)",
            "from areas"
    })
    int getAreasCount();
    @UpdateProvider(type=AreasSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Areas record);

    @Update({
        "update areas",
        "set area = #{area,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = now()",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Areas record);
}