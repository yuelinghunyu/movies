package com.jdj.movie.mapper;

import com.jdj.movie.model.Sales;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface SalesMapper {
    @Delete({
        "delete from sales",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into sales (id, wechat_id, ",
        "movie_id, create_time)",
        "values (#{id,jdbcType=VARCHAR}, #{wechatId,jdbcType=VARCHAR}, ",
        "#{movieId,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(Sales record);

    @InsertProvider(type=SalesSqlProvider.class, method="insertSelective")
    int insertSelective(Sales record);

    @Select({
        "select",
        "id, wechat_id, movie_id, create_time",
        "from sales",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="wechat_id", property="wechatId", jdbcType=JdbcType.VARCHAR),
        @Result(column="movie_id", property="movieId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Sales selectByPrimaryKey(String id);

    @UpdateProvider(type=SalesSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Sales record);

    @Update({
        "update sales",
        "set wechat_id = #{wechatId,jdbcType=VARCHAR},",
          "movie_id = #{movieId,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Sales record);
}