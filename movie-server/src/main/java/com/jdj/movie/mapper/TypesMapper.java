package com.jdj.movie.mapper;

import com.jdj.movie.model.Types;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface TypesMapper {
    @Delete({
            "delete from types",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);
    @Insert({
        "insert into types (id, type, ",
        "title, create_time, ",
        "modify_time)",
        "values (UUID(), #{type,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, now(), ",
        "now())"
    })
    int insert(Types record);

    @InsertProvider(type=TypesSqlProvider.class, method="insertSelective")
    int insertSelective(Types record);

    @Select({
            "select",
            "id,type,title,create_time,modify_time",
            "from types",
            "order by type asc",
            "limit #{skip},#{limit}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.VARCHAR, id=true),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Types> getListTypes(@Param("skip") int skip,@Param("limit") int limit);

    @Select({
            "select",
            "id,type,title,create_time,modify_time",
            "from types",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.VARCHAR, id=true),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Types selectByPrimaryKey(String id);
    @Select({
            "select count(0)",
            "from types"
    })
    int getTypesCount();

    @Update({
            "update types",
            "set type = #{type,jdbcType=INTEGER},",
            "title = #{title,jdbcType=VARCHAR},",
            "modify_time = now()",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Types record);
}