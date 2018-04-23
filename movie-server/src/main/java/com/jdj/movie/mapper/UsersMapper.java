package com.jdj.movie.mapper;

import com.jdj.movie.model.Users;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UsersMapper {
    @Delete({
        "delete from users",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into users (id, wechat_id, ",
        "wechat_name, wechat_logo, ",
        "create_time)",
        "values (#{id,jdbcType=VARCHAR}, #{wechatId,jdbcType=VARCHAR}, ",
        "#{wechatName,jdbcType=VARCHAR}, #{wechatLogo,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(Users record);

    @InsertProvider(type=UsersSqlProvider.class, method="insertSelective")
    int insertSelective(Users record);

    @Select({
        "select",
        "id, wechat_id, wechat_name, wechat_logo, create_time",
        "from users",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_name", property="wechatName", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_logo", property="wechatLogo", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Users selectByPrimaryKey(String id);

    @UpdateProvider(type=UsersSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Users record);

    @Update({
        "update users",
        "set wechat_id = #{wechatId,jdbcType=VARCHAR},",
          "wechat_name = #{wechatName,jdbcType=VARCHAR},",
          "wechat_logo = #{wechatLogo,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Users record);
}