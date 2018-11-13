package com.jdj.movie.controller;

import com.jdj.movie.bll.AreasBll;
import com.jdj.movie.bll.MovieBll;
import com.jdj.movie.bll.TypesBll;
import com.jdj.movie.enums.StaticTypes;
import com.jdj.movie.model.*;
import com.jdj.movie.utils.CovertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URLEncoder;
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
    @Autowired
    private AreasBll areasBll;
    @Autowired
    private TypesBll typesBll;
    /**
     * @param null
     * @return movie list
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getMovieList(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "area",required = false,defaultValue = "-1") int area,
            @RequestParam(value = "title",required = false,defaultValue = "") String title,
            @RequestParam(value = "type",required = false,defaultValue = "-1") int type,
            @RequestParam(value = "movieType",required = false,defaultValue = "-1") int movieType,
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "9") int limit
    ){
        int skip = (page-1)*limit;
        List<Movie> list =  movieBll.getMovieList(id,area,title,type,movieType,skip,limit);
        List<MovieConvert> mList = new ArrayList<>();
        int areaTotal = areasBll.getTotal();
        int typesTotal = typesBll.getTotal();

        List<Areas> areasList = areasBll.getAreasList(0,areaTotal);
        List<Types> typesList = typesBll.getTypeList(0,typesTotal);

        for (int i=0;i<list.size();i++){
            MovieConvert movieConvert = CovertUtils.covertMovie(list.get(i),areasList,typesList);
            mList.add(movieConvert);
        }
        if(list.size()==0){
            logger.info("list的长度",list.size());
            return new ReturnModel(0,0);
        }
        int total = movieBll.getTotal(id,area,title,type,movieType);
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",mList);
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
            @RequestParam(value = "OSSAccessKeyId") String keyId,
            @RequestParam(value = "Signature") String sign,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "type") int type,
            @RequestParam(value = "price",required = false,defaultValue = "") String price,
            @RequestParam(value = "count" ,required = false,defaultValue = "0") int count,
            @RequestParam(value = "movieType") int movieType,
            @RequestParam(value = "isFree") int isFree
    )throws Exception{
        Movie movie = new Movie();
        movie.setArea(area);
        String url = picUrl + "&OSSAccessKeyId="+keyId+"&Signature="+ URLEncoder.encode(sign, "utf-8");
        movie.setPicUrl(url);
        movie.setContent(content);
        movie.setDescription(description);
        movie.setTitle(title);
        movie.setType(type);
        if(!price.isEmpty()){
            movie.setPrice(new BigDecimal(price));
        }
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
    /**
     * @content 模糊查询电影名
     */
    @RequestMapping(value = "/listLike",method = RequestMethod.GET)
    public ReturnModel getMovieListLike(
            @RequestParam(value = "title",required = false,defaultValue = "") String title,
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "9") int limit
    ){
        int skip = (page-1)*limit;
        List<Movie> list =  movieBll.getMovieListLike(title,skip,limit);
        List<MovieConvert> mList = new ArrayList<>();
        int areaTotal = areasBll.getTotal();
        int typesTotal = typesBll.getTotal();

        List<Areas> areasList = areasBll.getAreasList(0,areaTotal);
        List<Types> typesList = typesBll.getTypeList(0,typesTotal);

        for (int i=0;i<list.size();i++){
            MovieConvert movieConvert = CovertUtils.covertMovie(list.get(i),areasList,typesList);
            mList.add(movieConvert);
        }
        if(list.size()==0){
            logger.info("list的长度",list.size());
            return new ReturnModel(0,0);
        }
        int total = movieBll.getMovieListLikeCount(title);
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",mList);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);
    }
}
