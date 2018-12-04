package com.jdj.movie.controller;

import com.jdj.movie.bll.BookTypeBll;
import com.jdj.movie.bll.BooksBll;
import com.jdj.movie.bll.ChapterBll;
import com.jdj.movie.model.*;
import com.jdj.movie.utils.CovertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangdajun on 2018/11/30.
 */
@RestController
@RequestMapping("/books")
public class BooksController {
    private final static Logger logger = LoggerFactory.getLogger(BooksController.class);
    @Autowired
    private BooksBll booksBll;
    @Autowired
    private BookTypeBll bookTypeBll;
    @Autowired
    private ChapterBll chapterBll;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getBookList(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "title",required = false,defaultValue = "") String title,
            @RequestParam(value = "author",required = false,defaultValue = "") String author,
            @RequestParam(value = "bookType",required = false,defaultValue = "-1") int bookType,
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "9") int limit
    ){
        int skip = (page-1)*limit;
        List<Books> booksList = booksBll.getBookList(id,title,author,bookType,skip,limit);
        List<BooksConvert> booksConvertList = new ArrayList<>();

        int bookTypeTotal = bookTypeBll.getTotalCount();
        List<BookType> bookTypeList = bookTypeBll.getBookTypeList(0,bookTypeTotal);


        for(int i=0;i<booksList.size();i++){
            BooksConvert booksConvert  = CovertUtils.covertBook(booksList.get(i),bookTypeList);
            booksConvertList.add(booksConvert);
        }
        int total = booksBll.getBooksCount(id,title,author,bookType);
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",booksConvertList);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);
    }

    @RequestMapping(value = "/addOrUpdate",method = RequestMethod.POST)
    public ReturnModel addOrUpdateBook(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "title",required = true) String title,
            @RequestParam(value = "logo",required = true) String logo,
            @RequestParam(value = "introUrl",required = true) String introUrl,
            @RequestParam(value = "author",required = true) String author,
            @RequestParam(value = "bookType",required = true) int bookType,
            @RequestParam(value = "price",required = false,defaultValue = "0") Long price,
            @RequestParam(value = "description",required = true) String description
    ){
        Books books = new Books();
        books.setTitle(title);
        books.setLogo(logo);
        books.setAuthor(author);
        books.setIntroUrl(introUrl);
        books.setBookType(bookType);
        books.setPrice(price);
        books.setDescription(description);
        int flag = 0;
        if(id.isEmpty()){
            logger.info("id值","：空，执行插入数据库操作");
            flag = booksBll.insertBooksItem(books);
        }else {
            logger.info("id值","："+id+";执行更新数据库操作");
            books.setId(id);
            flag = booksBll.updateBooksItem(books);
        }
        int total = booksBll.getBooksCount("","","",-1);
        if(flag>0){
            logger.info("info","：插入或更新成功");
            return new ReturnModel(0,total);
        }else {
            logger.error("return","：插入失败");
            return new ReturnModel(-1,flag);
        }
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ReturnModel delete(
            @RequestParam(value = "id",required = true) String id
    ){

        int flag = booksBll.deleteBooksItem(id);
        /**删除章节表*/
        int total = chapterBll.getChapterCount("",id,"","");
        int chFlag = 0;
        if(total>0){
            chFlag = chapterBll.deleteChapterItem("",id);
        }

        if(flag>0 && chFlag>=0){
            return new ReturnModel(0,true);
        }else {
            return new ReturnModel(-1,false);
        }
    }

    @RequestMapping(value = "/getCount",method = RequestMethod.GET)
    public ReturnModel getTotalCount(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "title",required = false,defaultValue = "") String title,
            @RequestParam(value = "author",required = false,defaultValue = "") String author,
            @RequestParam(value = "bookType",required = false,defaultValue = "-1") int bookType
    ){
        int totalCount = booksBll.getBooksCount(id,title,author,bookType);
        Map map = new HashMap<>();
        map.put("total",totalCount);
        return new ReturnModel(0,map);
    }
}
