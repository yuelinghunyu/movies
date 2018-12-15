package com.jdj.movie.mapper;

import com.jdj.movie.model.Payers;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface PayersMapper {
    @Delete({
            "delete from payers",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
            "insert into payers (id, wechat_id, ",
            "wechat_name, logo, ",
            "books, payDate)",
            "values (UUID(), #{wechatId,jdbcType=VARCHAR}, ",
            "#{wechatName,jdbcType=VARCHAR}, #{logo,jdbcType=VARCHAR}, ",
            "#{books,jdbcType=VARCHAR}, now())"
    })
    int insert(Payers record);

    @InsertProvider(type=PayersSqlProvider.class, method="insertSelective")
    int insertSelective(Payers record);

    @Select({
            "<script>",
            "select",
            "id, wechat_id, wechat_name, logo, books, payDate",
            "from payers",
            "where 1=1",
            "<if test='id != null and id != &apos;&apos;'>",
            "and id = #{id,jdbcType=VARCHAR}",
            "</if>",
            "<if test='wechatId != null and wechatId != &apos;&apos;'>",
            "and wechat_id = #{wechatId,jdbcType=VARCHAR}",
            "</if>",
            "<if test='wechatName != null and wechatName != &apos;&apos;'>",
            "and wechat_name = #{wechatName,jdbcType=VARCHAR}",
            "</if>",
            "<if test='books != null and books != &apos;&apos;'>",
            "and books = #{books,jdbcType=VARCHAR}",
            "</if>",
            "order by convert(wechat_name using gbk) asc",
            "limit #{skip},#{limit}",
            "</script>"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR),
            @Result(column="wechat_name", property="wechatName", jdbcType=JdbcType.VARCHAR),
            @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
            @Result(column="books", property="books", jdbcType=JdbcType.VARCHAR),
            @Result(column="payDate", property="paydate", jdbcType=JdbcType.DATE)
    })
    List<Payers> selectByParams(
            @Param("id") String id,
            @Param("wechatId") String wechatId,
            @Param("wechatName") String wechatName,
            @Param("books") String books,
            @Param("skip") int skip,
            @Param("limit") int limit
    );

    @Select({
            "<script>",
            "select count(0)",
            "from payers",
            "where 1=1",
            "<if test='id != null and id != &apos;&apos;'>",
            "and id = #{id,jdbcType=VARCHAR}",
            "</if>",
            "<if test='wechatId != null and wechatId != &apos;&apos;'>",
            "and wechat_id = #{wechatId,jdbcType=VARCHAR}",
            "</if>",
            "<if test='wechatName != null and wechatName != &apos;&apos;'>",
            "and wechat_name = #{wechatName,jdbcType=VARCHAR}",
            "</if>",
            "<if test='books != null and books != &apos;&apos;'>",
            "and books = #{books,jdbcType=VARCHAR}",
            "</if>",
            "</script>"
    })
    int getPayersCount(
            @Param("id") String id,
            @Param("wechatId") String wechatId,
            @Param("wechatName") String wechatName,
            @Param("books") String books
    );


    @UpdateProvider(type=PayersSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Payers record);

    @Update({
            "update payers",
            "set wechat_id = #{wechatId,jdbcType=VARCHAR},",
            "wechat_name = #{wechatName,jdbcType=VARCHAR},",
            "logo = #{logo,jdbcType=VARCHAR},",
            "books = #{books,jdbcType=VARCHAR},",
            "payDate = now()",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Payers record);
}