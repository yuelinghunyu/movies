package com.jdj.movie.mapper;

import com.jdj.movie.model.Person;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PersonMapper {
    @Delete({
        "delete from person",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into person (id, user_name, ",
        "pass_word)",
        "values (#{id,jdbcType=INTEGER}, #{userName,jdbcType=VARCHAR}, ",
        "#{passWord,jdbcType=VARCHAR})"
    })
    int insert(Person record);

    @InsertProvider(type=PersonSqlProvider.class, method="insertSelective")
    int insertSelective(Person record);

    @Select({
        "select",
        "id, user_name, pass_word",
        "from person",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="pass_word", property="passWord", jdbcType=JdbcType.VARCHAR)
    })
    Person selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PersonSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Person record);

    @Update({
        "update person",
        "set user_name = #{userName,jdbcType=VARCHAR},",
          "pass_word = #{passWord,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Person record);
}