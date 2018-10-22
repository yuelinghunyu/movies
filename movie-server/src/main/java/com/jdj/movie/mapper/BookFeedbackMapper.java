package com.jdj.movie.mapper;

import com.jdj.movie.model.BookFeedback;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface BookFeedbackMapper {
    @Delete({
        "delete from book_feedback",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into book_feedback (id, feed_type, ",
        "type, create_time, ",
        "modify_time, content)",
        "values (#{id,jdbcType=VARCHAR}, #{feedType,jdbcType=INTEGER}, ",
        "#{type,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyTime,jdbcType=TIMESTAMP}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(BookFeedback record);

    @InsertProvider(type=BookFeedbackSqlProvider.class, method="insertSelective")
    int insertSelective(BookFeedback record);

    @Select({
        "select",
        "id, feed_type, type, create_time, modify_time, content",
        "from book_feedback",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="feed_type", property="feedType", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    BookFeedback selectByPrimaryKey(String id);

    @UpdateProvider(type=BookFeedbackSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BookFeedback record);

    @Update({
        "update book_feedback",
        "set feed_type = #{feedType,jdbcType=INTEGER},",
          "type = #{type,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(BookFeedback record);

    @Update({
        "update book_feedback",
        "set feed_type = #{feedType,jdbcType=INTEGER},",
          "type = #{type,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(BookFeedback record);
}