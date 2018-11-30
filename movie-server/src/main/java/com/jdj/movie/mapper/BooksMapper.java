package com.jdj.movie.mapper;

import com.jdj.movie.model.Books;
import com.jdj.movie.model.BooksKey;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface BooksMapper {
    @Delete({
        "delete from books",
        "where id = #{id,jdbcType=VARCHAR}",
          "and chapter_id = #{chapterId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(BooksKey key);

    @Insert({
        "insert into books (id, chapter_id, ",
        "title, logo, author, ",
        "intro_url, book_type, ",
        "price, create_time, ",
        "modify_time, description)",
        "values (UUID(), #{chapterId,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{logo,jdbcType=VARCHAR}, #{author,jdbcType=VARCHAR}, ",
        "#{introUrl,jdbcType=VARCHAR}, #{bookType,jdbcType=INTEGER}, ",
        "#{price,jdbcType=DECIMAL}, now(), ",
        "now(), #{description,jdbcType=LONGVARCHAR})"
    })
    int insert(Books record);

    @InsertProvider(type=BooksSqlProvider.class, method="insertSelective")
    int insertSelective(Books record);

    @Select({
        "<script>",
        "select",
        "id, chapter_id, title, logo, author, intro_url, book_type, price, create_time, ",
        "modify_time, description",
        "from books",
        "where 1=1",
            "<if test='id!=null and id!= &apos;&apos;'>",
                "and id = #{id,jdbcType=VARCHAR}",
            "</if>",
            "<if test='chapterId!=null and chapterId!= &apos;&apos;'>",
                "and chapter_id = #{chapterId,jdbcType=VARCHAR}",
            "</if>",
            "<if test='title != null and title != &apos;&apos;'>",
                "and title = #{title,jdbcType=VARCHAR}",
            "</if>",
            "<if test='author != null and author != &apos;&apos;'>",
                "and author = #{author,jdbcType=VARCHAR}",
            "</if>",
            "<if test='bookType!= -1'>",
                "and book_type = #{bookType,jdbcType=INTEGER}",
            "</if>",
            "order by create_time desc",
            "limit #{skip},#{limit}",
        "</script>"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="chapter_id", property="chapterId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="intro_url", property="introUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="book_type", property="bookType", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Books> selectByParam(
            @Param("id") String id,
            @Param("chapterId") String chapterId,
            @Param("title") String title,
            @Param("author") String author,
            @Param("bookType") int bookType,
            @Param("skip") int skip,
            @Param("limit") int limit
    );

    @UpdateProvider(type=BooksSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Books record);

    @Update({
        "update books",
        "set title = #{title,jdbcType=VARCHAR},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "author = #{author,jdbcType=VARCHAR},",
          "intro_url = #{introUrl,jdbcType=VARCHAR},",
          "book_type = #{bookType,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = now(),",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(Books record);


    @Select({
            "<script>",
            "select count(0)",
            "from books",
            "where 1=1",
            "<if test='bookType!= -1'>",
            "and book_type = #{bookType,jdbcType=INTEGER}",
            "</if>",
            "</script>"
    })
    int getBooksCount(
            @Param("bookType") int bookType
    );

    @Update({
        "update books",
        "set title = #{title,jdbcType=VARCHAR},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "author = #{author,jdbcType=VARCHAR},",
          "intro_url = #{introUrl,jdbcType=VARCHAR},",
          "book_type = #{bookType,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}",
          "and chapter_id = #{chapterId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Books record);
}