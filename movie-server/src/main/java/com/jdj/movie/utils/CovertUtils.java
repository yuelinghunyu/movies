package com.jdj.movie.utils;

import com.jdj.movie.model.*;

import java.util.List;

/**
 * Created by jiangdajun on 2018/7/13.
 */
public class CovertUtils {
    //将对应的类型转换成字符串中文;
    public static String getAreaTitle(int area, List<Areas> list){
        for (Areas areas:list){
            if(areas.getArea() == area) {
                return areas.getTitle();
            }
        }
        return "";
    }

    public static String getTypeTitle(int type, List<Types> list){
        for (Types types:list){
            if(types.getType() == type){
                return types.getTitle();
            }
        }
        return "";
    }

    public static String getBlogTypeTitle(int blogType, List<BookType> list){
        for(BookType bookType:list){
            if(bookType.getTypeId() == blogType){
                return bookType.getTypeTitle();
            }
        }
        return "";
    }

    public static String getBookTypeTitle(int bookType,List<BookType> list){
        for(BookType type:list){
            if(type.getTypeId() == bookType){
                return type.getTypeTitle();
            }
        }
        return "";
    }

    public static String getIsFreeTitle(int isFree){
        String isFreeVal = "";
        switch (isFree){
            case 0:
                isFreeVal = "否";
                break;
            case 1:
                isFreeVal = "是";
                break;
        }
        return isFreeVal;
    }
    //博客
    public static BlogConvert convertBlog(Blog blog,List<BookType> bookTypeList){
        BlogConvert blogConvert = new BlogConvert();
        blogConvert.setId(blog.getId());
        blogConvert.setTitle(blog.getTitle());
        blogConvert.setBlogType(blog.getBlogType());
        blogConvert.setBlogTypeTitle(getBlogTypeTitle(blog.getBlogType(),bookTypeList));
        blogConvert.setHref(blog.getHref());
        blogConvert.setTimes(blog.getTimes());
        blogConvert.setCreateTime(blog.getCreateTime());
        return blogConvert;
    }
    //电影
    public static MovieConvert covertMovie(Movie movie,List<Areas> areaslist, List<Types> typeslist){
        MovieConvert movieConvert = new MovieConvert();
        movieConvert.setId(movie.getId());
        movieConvert.setArea(movie.getArea());
        movieConvert.setAreaTitle(getAreaTitle(movie.getArea(),areaslist));
        movieConvert.setPicUrl(movie.getPicUrl());
        movieConvert.setContent(movie.getContent());
        movieConvert.setTitle(movie.getTitle());
        movieConvert.setActor(movie.getActor());
        movieConvert.setType(movie.getType());
        movieConvert.setTypeTitle(getTypeTitle(movie.getType(),typeslist));
        movieConvert.setPrice(movie.getPrice());
        movieConvert.setCount(movie.getCount());
        movieConvert.setMovieType(movie.getMovieType());
        movieConvert.setIsFree(movie.getIsFree());
        movieConvert.setIsFreeTitle(getIsFreeTitle(movie.getIsFree()));
        movieConvert.setCreateTime(movie.getCreateTime());
        movieConvert.setModifyTime(movie.getModifyTime());
        movieConvert.setDescription(movie.getDescription());
        return movieConvert;
    }

    //小册
    public static BooksConvert covertBook(Books books,List<BookType> bookTypeList,List<Chapters> chaptersList,List<Payers> payersList){
        BooksConvert booksConvert = new BooksConvert();
        booksConvert.setId(books.getId());
        booksConvert.setLogo(books.getLogo());
        booksConvert.setTitle(books.getTitle());
        booksConvert.setAuthor(books.getAuthor());
        booksConvert.setBookType(books.getBookType());
        booksConvert.setBookTypeTitle(getBookTypeTitle(books.getBookType(),bookTypeList));
        booksConvert.setChaptersList(chaptersList);
        booksConvert.setPayersList(payersList);
        booksConvert.setIntroUrl(books.getIntroUrl());
        booksConvert.setPrice(books.getPrice());
        booksConvert.setDescription(books.getDescription());
        booksConvert.setCreateTime(books.getCreateTime());
        booksConvert.setModifyTime(books.getModifyTime());
        return booksConvert;
    }
}
