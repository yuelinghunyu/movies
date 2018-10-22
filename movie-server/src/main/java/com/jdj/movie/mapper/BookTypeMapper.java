package com.jdj.movie.mapper;

import com.jdj.movie.model.BookType;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface BookTypeMapper {
    @Delete({
        "delete from book_type",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into book_type (id, type_id, ",
        "type_title, create_time, ",
        "modify_time)",
        "values (UUID(), #{typeId,jdbcType=INTEGER}, ",
        "#{typeTitle,jdbcType=VARCHAR},now(), ",
        "now())"
    })
    int insert(BookType record);

    @InsertProvider(type=BookTypeSqlProvider.class, method="insertSelective")
    int insertSelective(BookType record);

    @Select({
        "select",
        "id, type_id, type_title, create_time, modify_time",
        "from book_type",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
        @Result(column="type_title", property="typeTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    BookType selectByPrimaryKey(String id);

    /**
     *
     * @param skip,limit
     * @return list
     */
    @Select({
            "select",
            "id, type_id, type_title, create_time, modify_time",
            "from book_type",
            "order by type_id asc",
            "limit #{skip},#{limit}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="type_id", property="typeId", jdbcType=JdbcType.INTEGER),
            @Result(column="type_title", property="typeTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<BookType> getBookTypeList(@Param("skip") int skip, @Param("limit") int limit);

    /**
     *
     * @param null
     * @return count
     */

    @Select({
            "select count(0)",
            "from book_type"
    })
    int getBookTypeCount();

    @UpdateProvider(type=BookTypeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BookType record);

    @Update({
        "update book_type",
        "set type_id = #{typeId,jdbcType=INTEGER},",
          "type_title = #{typeTitle,jdbcType=VARCHAR},",
          "modify_time = now()",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(BookType record);
}