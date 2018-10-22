package com.jdj.movie.controller;

import com.jdj.movie.bll.BookTypeBll;
import com.jdj.movie.model.BookType;
import com.jdj.movie.model.ReturnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by jdj on 2018/10/22.
 */
@RestController
@RequestMapping("/bookType")
public class BookTypeController {
    private final static Logger logger = LoggerFactory.getLogger(BookTypeController.class);
    @Autowired
    private BookTypeBll bookTypeBll;
    /**
     * @param null
     * @return bookType list
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getBookTypeList(
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "10") int limit
    ){
        int skip = (page-1)*limit;
        List<BookType> list = bookTypeBll.getBookTypeList(skip,limit);
        if(list.size()==0){
            logger.info("list的长度",list.size());
            return new ReturnModel(0,new ArrayList<>());
        }
        int total = bookTypeBll.getTotalCount();
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",list);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);
    }

    /**
     * @param bookType
     * @return int
     */
    @RequestMapping(value = "/addOrUpdate",method = RequestMethod.POST)
    public ReturnModel addBookType(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "typeId") int typeId,
            @RequestParam(value = "typeTitle") String typeTitle
    ){
        BookType bookType = new BookType();
        bookType.setTypeId(typeId);
        bookType.setTypeTitle(typeTitle);
        int flag = 0;
        int total = bookTypeBll.getTotalCount();
        if(id.isEmpty()){
            bookType.setId(UUID.randomUUID().toString().replace("-","").toLowerCase());
            logger.info("id值","：空，执行插入数据库操作");
            flag = bookTypeBll.insertBookType(bookType);
        }else {
            bookType.setId(id);
            logger.info("id值","："+id+";执行更新数据库操作");
            flag = bookTypeBll.updateBookType(bookType);
        }
        if(flag>0){
            logger.info("插入成功");
            return new ReturnModel(0,total);
        }else{
            logger.info("插入失败");
            return new ReturnModel(-1,false);
        }
    }

    /**
     * @param id,
     * @return boolean
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ReturnModel deleteBookType(
            @RequestParam(value = "id") String id
    ){
        int flag = bookTypeBll.deleteBookType(id);
        if(flag>0){
            return new ReturnModel(0,true);
        }else {
            return new ReturnModel(0,false);
        }
    }
    /**
     * @content 查询总共条数
     */
    @RequestMapping(value = "/getTotal",method = RequestMethod.GET)
    public ReturnModel totalCount(){
        int total = bookTypeBll.getTotalCount();
        return new ReturnModel(0,total);
    }
}
