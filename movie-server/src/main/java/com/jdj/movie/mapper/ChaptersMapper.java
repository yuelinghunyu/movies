package com.jdj.movie.mapper;

import com.jdj.movie.model.Chapters;
import com.jdj.movie.model.ChaptersKey;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ChaptersMapper {
    @Delete({
        "delete from chapters",
        "where id = #{id,jdbcType=VARCHAR}",
          "and book_id = #{bookId,jdbcType=VARCHAR}"
    })
    int deleteByParam(String id, String bookId);

    @Insert({
        "insert into chapters (id, book_id, ",
        "book_title, title, ",
        "href, time, browser, ",
        "create_time, modify_time)",
        "values (UUID(), #{bookId,jdbcType=VARCHAR}, ",
        "#{bookTitle,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{href,jdbcType=VARCHAR}, #{time,jdbcType=VARCHAR}, #{browser,jdbcType=INTEGER}, ",
        "now(), now())"
    })
    int insert(Chapters record);

    @InsertProvider(type=ChaptersSqlProvider.class, method="insertSelective")
    int insertSelective(Chapters record);

    @Select({
        "<script>",
        "select",
        "id, book_id, book_title, title, href, time, browser, create_time, modify_time",
        "from chapters",
        "where 1=1",
            "<if test='id!=null and id!= &apos;&apos;'>",
            "and id = #{id,jdbcType=VARCHAR}",
            "</if>",
            "<if test='bookId!=null and bookId!= &apos;&apos;'>",
            "and book_Id = #{bookId,jdbcType=VARCHAR}",
            "</if>",
            "<if test='bookTitle!=null and bookTitle!= &apos;&apos;'>",
            "and book_title = #{bookTitle,jdbcType=VARCHAR}",
            "</if>",
            "<if test='title!=null and title!= &apos;&apos;'>",
            "and title = #{title,jdbcType=VARCHAR}",
            "</if>",
            "group by book_id",
            "order by create_time desc",
            "limit #{skip},#{limit}",
        "</script>"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="book_id", property="bookId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="book_title", property="bookTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="href", property="href", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.VARCHAR),
        @Result(column="browser", property="browser", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Chapters> selectByChapterParam(
            @Param("id") String id,
            @Param("book_id") String bookId,
            @Param("book_title") String bookTitle,
            @Param("title") String title,
            @Param("skip") int skip,
            @Param("limit") int limit
    );

    @Select({
            "<script>",
            "select count(0)",
            "from chapters",
            "where 1=1",
            "<if test='bookId!=null and bookId!= &apos;&apos;'>",
            "and book_Id = #{bookId,jdbcType=VARCHAR}",
            "</if>",
            "<if test='bookTitle!=null and bookTitle!= &apos;&apos;'>",
            "and book_title = #{bookTitle,jdbcType=VARCHAR}",
            "</if>",
            "</script>"
    })
    int getChapterCount(
            @Param("bookId") String bookId,
            @Param("bookTitle") String bookTitle
    );


    @UpdateProvider(type=ChaptersSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Chapters record);

    @Update({
        "update chapters",
        "set book_title = #{bookTitle,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "href = #{href,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=VARCHAR},",
          "browser = #{browser,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = now()",
        "where id = #{id,jdbcType=VARCHAR}",
          "and book_id = #{bookId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Chapters record);
}