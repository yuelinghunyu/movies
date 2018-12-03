package com.jdj.movie.mapper;

import com.jdj.movie.model.FeedBack;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FeedBackMapper {
    @Delete({
        "delete from feed_back",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into feed_back (id, wechat_id, ",
        "wechat_name, feed_type, ",
        "type, create_time, ",
        "modify_time, content)",
        "values (UUID(), #{wechatId,jdbcType=VARCHAR}, ",
        "#{wechatName,jdbcType=VARCHAR}, #{feedType,jdbcType=INTEGER}, ",
        "#{type,jdbcType=INTEGER}, now(), ",
        "now(), #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(FeedBack record);

    @InsertProvider(type=FeedBackSqlProvider.class, method="insertSelective")
    int insertSelective(FeedBack record);

    @Select({
        "<script>",
        "select",
        "id, wechat_id, wechat_name, feed_type, type, create_time, modify_time, content",
        "from feed_back",
        "where 1=1",
            "<if test='id!=null and id!= &apos;&apos;'>",
            "and id = #{id,jdbcType=VARCHAR}",
            "</if>",
            "<if test='wechatName!=null and wechatName!= &apos;&apos;'>",
            "and wechat_name = #{wechatName,jdbcType=VARCHAR}",
            "</if>",
            "<if test='feedType!= 0'>",
            "and feed_type = #{feedType,jdbcType=INTEGER}",
            "</if>",
            "<if test='type!= 0'>",
            "and type = #{type,jdbcType=INTEGER}",
            "</if>",
            "order by create_time desc",
            "limit #{skip},#{limit}",
        "</script>"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR),
        @Result(column="wechat_name", property="wechatName", jdbcType=JdbcType.VARCHAR),
        @Result(column="feed_type", property="feedType", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<FeedBack> selectByParam(String id,String wechatName,int feedType,int type,int skip,int limit);

    @Select({
            "<script>",
            "select count(0)",
            "from feed_back",
            "where 1=1",
            "<if test='id!=null and id!= &apos;&apos;'>",
            "and id = #{id,jdbcType=VARCHAR}",
            "</if>",
            "<if test='wechatName!=null and wechatName!= &apos;&apos;'>",
            "and wechat_name = #{wechatName,jdbcType=VARCHAR}",
            "</if>",
            "<if test='feedType!= 0'>",
            "and feed_type = #{feedType,jdbcType=INTEGER}",
            "</if>",
            "<if test='type!= 0'>",
            "and type = #{type,jdbcType=INTEGER}",
            "</if>",
            "</script>"
    })
    int getFeedBackCount(
            @Param("id") String id,
            @Param("wechatName") String wechatName,
            @Param("feedType") int feedType,
            @Param("type") int type
    );

    @UpdateProvider(type=FeedBackSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FeedBack record);

    @Update({
        "update feed_back",
        "set wechat_id = #{wechatId,jdbcType=VARCHAR},",
          "wechat_name = #{wechatName,jdbcType=VARCHAR},",
          "feed_type = #{feedType,jdbcType=INTEGER},",
          "type = #{type,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(FeedBack record);

    @Update({
        "update feed_back",
        "set wechat_id = #{wechatId,jdbcType=VARCHAR},",
          "wechat_name = #{wechatName,jdbcType=VARCHAR},",
          "feed_type = #{feedType,jdbcType=INTEGER},",
          "type = #{type,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(FeedBack record);
}