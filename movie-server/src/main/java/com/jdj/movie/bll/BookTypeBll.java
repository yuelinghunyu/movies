package com.jdj.movie.bll;

import com.jdj.movie.mapper.BookTypeMapper;
import com.jdj.movie.model.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdajun on 2018/10/22.
 */
@Service
public class BookTypeBll {
    @Autowired
    private BookTypeMapper bookTypeMapper;
    /**
     * 插入操作
     */
    public int insertBookType(BookType bookType){
        return bookTypeMapper.insert(bookType);
    }
    /**
     * 查询操作
     */
    public List<BookType> getBookTypeList(int skip, int limit){
        return  bookTypeMapper.getBookTypeList(skip,limit);
    }
    /**
     * 修改操作
     */
    public int updateBookType(BookType bookType){
        return bookTypeMapper.updateByPrimaryKey(bookType);
    }
    /**
     * 删除操作
     */
    public int deleteBookType(String id){
        return bookTypeMapper.deleteByPrimaryKey(id);
    }
    /**
     * 获取总数
     */
    public int getTotalCount(){
        return bookTypeMapper.getBookTypeCount();
    }
}
