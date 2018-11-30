package com.jdj.movie.controller;

import com.jdj.movie.bll.ChapterBll;
import com.jdj.movie.model.Chapters;
import com.jdj.movie.model.ReturnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangdajun on 2018/11/30.
 */
@RestController
@RequestMapping("/chapters")
public class ChapterController {
    private final static Logger logger = LoggerFactory.getLogger(ChapterController.class);

    @Autowired
    private ChapterBll chapterBll;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getChaptersList(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "bookId",required = false,defaultValue = "") String bookId,
            @RequestParam(value = "bookTitle",required = false,defaultValue = "") String bookTitle,
            @RequestParam(value = "title",required = false,defaultValue = "") String title,
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "9") int limit
    ){
        int skip = (page-1)*limit;
        List<Chapters> chaptersList = chapterBll.getChapterList(id,bookId,bookTitle,title,skip,limit);
        int total = chapterBll.getChapterCount(id,bookId,bookTitle,title);
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",chaptersList);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);
    }

    @RequestMapping(value = "/addOrUpdate",method = RequestMethod.POST)
    public ReturnModel addOrUpdateChapter(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "bookId",required = true) String bookId,
            @RequestParam(value = "bookTitle",required = true) String bookTitle,
            @RequestParam(value = "title",required = true) String title,
            @RequestParam(value = "href",required = true) String href,
            @RequestParam(value = "time",required = true) String time,
            @RequestParam(value = "browser",required = false,defaultValue = "0") int browser
    ){
        Chapters chapters = new Chapters();
        chapters.setTitle(title);
        chapters.setBookId(bookId);
        chapters.setBookTitle(bookTitle);
        chapters.setHref(href);
        chapters.setBrowser(browser);
        chapters.setTime(time);
        chapters.setBrowser(browser);
        int chapterFlag = 0;
        if(id.isEmpty()){
            logger.info("id值","：空，执行插入数据库操作");
            chapterFlag = chapterBll.insertChapter(chapters);
        }else {
            logger.info("id值","："+id+";执行更新数据库操作");
            chapters.setId(id);
            chapterFlag = chapterBll.updateChapter(chapters);
        }
        int total = chapterBll.getChapterCount("","","","");
        if(chapterFlag>0){
            logger.info("info","：章节插入或更新成功");
            return new ReturnModel(0,total);
        }else {
            logger.error("return","：插入失败");
            return new ReturnModel(-1,chapterFlag);
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ReturnModel delete(
            @RequestParam(value = "id",required = true) String id
    ){

        int flag = chapterBll.deleteChapterItem(id,"");
        if(flag>0){
            return new ReturnModel(0,true);
        }else {
            return new ReturnModel(-1,false);
        }
    }
}
