package com.jdj.movie.mapper;

import com.jdj.movie.model.Areas;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

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
        "values (#{id,jdbcType=VARCHAR}, #{area,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyTime,jdbcType=TIMESTAMP})"
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

    @UpdateProvider(type=AreasSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Areas record);

    @Update({
        "update areas",
        "set area = #{area,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Areas record);
}