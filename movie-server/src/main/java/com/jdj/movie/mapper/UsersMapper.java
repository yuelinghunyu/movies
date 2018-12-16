package com.jdj.movie.mapper;

import com.jdj.movie.model.Users;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
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
        "values (UUID(), #{wechatId,jdbcType=VARCHAR}, ",
        "#{wechatName,jdbcType=VARCHAR}, #{wechatLogo,jdbcType=VARCHAR}, ",
        "now())"
    })
    int insert(Users record);

    @InsertProvider(type=UsersSqlProvider.class, method="insertSelective")
    int insertSelective(Users record);

    @Select({
            "<script>",
                "select",
                "id, wechat_id, wechat_name, wechat_logo, create_time",
                "from users",
                "where 1=1",
                    "<if test='wechatId != null and wechatId != &apos;&apos;'>",
                    "and wechat_id = #{wechatId,jdbcType=VARCHAR}",
                    "</if>",
                    "<if test='wechatName != null and wechatName != &apos;&apos;'>",
                    "and wechat_name LIKE CONCAT(CONCAT('%',#{wechatName,jdbcType=VARCHAR},'%'))",
                    "</if>",
                "order by  create_time desc",
                "limit #{skip},#{limit}",
            "</script>"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_name", property="wechatName", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_logo", property="wechatLogo", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Users> selectByName(
            @Param("wechatId") String wechatId,
            @Param("wechatName") String wechatName,
            @Param("skip") int skip,
            @Param("limit") int limit
    );


    @Select({
            "<script>",
            "select count(0)",
            "from users",
            "where 1=1",
            "<if test='wechatId != null and wechatId != &apos;&apos;'>",
                "and wechat_id = #{wechatId,jdbcType=VARCHAR}",
            "</if>",
            "<if test='wechatName != null and wechatName != &apos;&apos;'>",
            "and wechat_name LIKE CONCAT(CONCAT('%',#{wechatName,jdbcType=VARCHAR},'%'))",
            "</if>",
            "</script>"
    })
    int getUsersCount(
            @Param("wechatId") String wechatId,
            @Param("wechatName") String wechatName
    );


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