package com.jdj.movie.controller;

import com.jdj.movie.bll.MovieBll;
import com.jdj.movie.enums.StaticTypes;
import com.jdj.movie.model.Movie;
import com.jdj.movie.model.ReturnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by jiangdajun on 2018/7/5.
 */
@RestController
@RequestMapping("/movie")
public class MovieController {
    private final static Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private MovieBll movieBll;
    /**
     * @param null
     * @return movie list
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getMovieList(
            @RequestParam(value = "flag",required = false,defaultValue = "") String flag,
            @RequestParam(value = "val",required = false,defaultValue = "") String val,
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "9") int limit
    ){
        int skip = (page-1)*limit;
        List<Movie> list = new ArrayList<>();
        String id = "";
        int area =  -1;
        String title = "";
        int type =  -1;
        int movieType =  -1;
        //根据flag作为判断条件（id\area\title\type|movieType）
        if(flag.equals("id")){
            id = val;
        }
        if(flag.equals("area")){
            area = Integer.parseInt(val);
        }
        if(flag.equals("title")){
            title = val;
        }
        if(flag.equals("type")){
            type = Integer.parseInt(val);
        }
        if(flag.equals("movieType")){
            movieType = Integer.parseInt(val);
        }

        list =  movieBll.getMovieList(id,area,title,type,movieType,skip,limit);
        if(list.size()==0){
            logger.info("list的长度",list.size());
            return new ReturnModel(0,0);
        }
        int total = movieBll.getTotal(id,area,title,type,movieType);
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",list);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);
    }

    /**
     * @param movie
     * @return true
     */
    @RequestMapping(value = "/addOrUpdate",method = RequestMethod.POST)
    public ReturnModel addOrUpdateMovie(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "area") int area,
            @RequestParam(value = "picUrl") String picUrl,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "type") int type,
            @RequestParam(value = "price") String price,
            @RequestParam(value = "count") int count,
            @RequestParam(value = "movieType") int movieType,
            @RequestParam(value = "isFree") int isFree
    ){
        Movie movie = new Movie();
        movie.setArea(area);
        movie.setPicUrl(picUrl);
        movie.setContent(content);
        movie.setDescription(description);
        movie.setTitle(title);
        movie.setType(type);
        movie.setPrice(new BigDecimal(price));
        movie.setCount(count);
        movie.setMovieType(StaticTypes.valueOf(movieType));
        movie.setIsFree(isFree);
        int flag = 0;
        if(id.isEmpty()){
            id = UUID.randomUUID().toString().replace("-","").toLowerCase();
            movie.setId(id);
            logger.info("id值","：空，执行插入数据库操作");
            flag = movieBll.insertMovie(movie);
        }else {
            movie.setId(id);
            logger.info("id值","："+id+";执行更新数据库操作");
            flag = movieBll.updateMovie(movie);
        }
        int total = movieBll.getTotal("",-1,"",-1,-1);
        if(flag>0){
            logger.info("return","：插入成功");
            return new ReturnModel(0,total);
        }else {
            logger.info("return","：插入失败");
            return new ReturnModel(-1,flag);
        }
    }

    /**
     * @content 删除一条记录
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ReturnModel deleteMovie(
            @RequestParam(value = "id") String id
    ){
        int flag = movieBll.deleteMovie(id);
        if(flag>0){
            return new ReturnModel(0,true);
        }else {
            return new ReturnModel(-1,flag);
        }
    }
}
