package com.jdj.movie.mapper;

import com.jdj.movie.model.Tailor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TailorMapper {
    @Delete({
        "delete from tailor",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into tailor (id, wechat_id, ",
        "area, type, title, ",
        "create_time, description)",
        "values (#{id,jdbcType=VARCHAR}, #{wechatId,jdbcType=VARCHAR}, ",
        "#{area,jdbcType=TINYINT}, #{type,jdbcType=TINYINT}, #{title,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{description,jdbcType=LONGVARCHAR})"
    })
    int insert(Tailor record);

    @InsertProvider(type=TailorSqlProvider.class, method="insertSelective")
    int insertSelective(Tailor record);

    @Select({
        "select",
        "id, wechat_id, area, type, title, create_time, description",
        "from tailor",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR),
        @Result(column="area", property="area", jdbcType=JdbcType.TINYINT),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    Tailor selectByPrimaryKey(String id);

    @UpdateProvider(type=TailorSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Tailor record);

    @Update({
        "update tailor",
        "set wechat_id = #{wechatId,jdbcType=VARCHAR},",
          "area = #{area,jdbcType=TINYINT},",
          "type = #{type,jdbcType=TINYINT},",
          "title = #{title,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(Tailor record);

    @Update({
        "update tailor",
        "set wechat_id = #{wechatId,jdbcType=VARCHAR},",
          "area = #{area,jdbcType=TINYINT},",
          "type = #{type,jdbcType=TINYINT},",
          "title = #{title,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Tailor record);
}