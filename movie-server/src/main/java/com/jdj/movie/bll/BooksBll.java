package com.jdj.movie.bll;

import com.jdj.movie.mapper.BooksMapper;
import com.jdj.movie.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdajun on 2018/11/29.
 */
@Service
public class BooksBll {
    @Autowired
    private BooksMapper booksMapper;

    public int insertBooksItem(Books books){
        return booksMapper.insert(books);
    }

    public int updateBooksItem(Books books){
        return booksMapper.updateByPrimaryKeyWithBLOBs(books);
    }

    public int deleteBooksItem(String id){
        return booksMapper.deleteByPrimaryKey(id);
    }

    public List<Books> getBookList(
            String id,
            String title,
            String author,
            int bookType,
            int skip,
            int limit
    ){
        return booksMapper.selectByParam(id,title,author,bookType,skip,limit);
    }
    public int getBooksCount(
            String id,
            String title,
            String author,
            int bookType
    ){
        return booksMapper.getBooksCount(id,title,author,bookType);
    }

}
