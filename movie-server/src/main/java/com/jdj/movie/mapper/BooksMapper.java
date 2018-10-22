package com.jdj.movie.mapper;

import com.jdj.movie.model.Books;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface BooksMapper {
    @Delete({
        "delete from books",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into books (id, title, ",
        "logo, author, introUrl, ",
        "book_type, price, ",
        "create_time, modify_time, ",
        "description)",
        "values (#{id,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{logo,jdbcType=VARCHAR}, #{author,jdbcType=VARCHAR}, #{introurl,jdbcType=VARCHAR}, ",
        "#{bookType,jdbcType=INTEGER}, #{price,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{description,jdbcType=LONGVARCHAR})"
    })
    int insert(Books record);

    @InsertProvider(type=BooksSqlProvider.class, method="insertSelective")
    int insertSelective(Books record);

    @Select({
        "select",
        "id, title, logo, author, introUrl, book_type, price, create_time, modify_time, ",
        "description",
        "from books",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="introUrl", property="introurl", jdbcType=JdbcType.VARCHAR),
        @Result(column="book_type", property="bookType", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    Books selectByPrimaryKey(String id);

    @UpdateProvider(type=BooksSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Books record);

    @Update({
        "update books",
        "set title = #{title,jdbcType=VARCHAR},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "author = #{author,jdbcType=VARCHAR},",
          "introUrl = #{introurl,jdbcType=VARCHAR},",
          "book_type = #{bookType,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(Books record);

    @Update({
        "update books",
        "set title = #{title,jdbcType=VARCHAR},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "author = #{author,jdbcType=VARCHAR},",
          "introUrl = #{introurl,jdbcType=VARCHAR},",
          "book_type = #{bookType,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Books record);
}