package com.jdj.movie.mapper;

import com.jdj.movie.model.Payers;
import com.jdj.movie.model.PayersKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PayersMapper {
    @Delete({
        "delete from payers",
        "where id = #{id,jdbcType=VARCHAR}",
          "and wechat_id = #{wechatId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(PayersKey key);

    @Insert({
        "insert into payers (id, wechat_id, ",
        "wechat_name, logo, ",
        "books, payDate)",
        "values (#{id,jdbcType=VARCHAR}, #{wechatId,jdbcType=VARCHAR}, ",
        "#{wechatName,jdbcType=VARCHAR}, #{logo,jdbcType=VARCHAR}, ",
        "#{books,jdbcType=VARCHAR}, #{paydate,jdbcType=DATE})"
    })
    int insert(Payers record);

    @InsertProvider(type=PayersSqlProvider.class, method="insertSelective")
    int insertSelective(Payers record);

    @Select({
        "select",
        "id, wechat_id, wechat_name, logo, books, payDate",
        "from payers",
        "where id = #{id,jdbcType=VARCHAR}",
          "and wechat_id = #{wechatId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="wechat_name", property="wechatName", jdbcType=JdbcType.VARCHAR),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="books", property="books", jdbcType=JdbcType.VARCHAR),
        @Result(column="payDate", property="paydate", jdbcType=JdbcType.DATE)
    })
    Payers selectByPrimaryKey(PayersKey key);

    @UpdateProvider(type=PayersSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Payers record);

    @Update({
        "update payers",
        "set wechat_name = #{wechatName,jdbcType=VARCHAR},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "books = #{books,jdbcType=VARCHAR},",
          "payDate = #{paydate,jdbcType=DATE}",
        "where id = #{id,jdbcType=VARCHAR}",
          "and wechat_id = #{wechatId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Payers record);
}