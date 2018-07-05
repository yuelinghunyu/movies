package com.jdj.movie.mapper;

import com.jdj.movie.model.Types;
import org.apache.ibatis.annotations.*;

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
    List<Types> getListTypes(@Param("skip") int skip,@Param("limit") int limit);

    @Select({
            "select count(0)",
            "from types"
    })
    int getTypesCount();

    @Update({
            "update types",
            "set type = #{type,jdbcType=INTEGER},",
            "title = #{title,jdbcType=VARCHAR},",
            "create_time = #{createTime,jdbcType=TIMESTAMP},",
            "modify_time = now()",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Types record);
}