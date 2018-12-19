package com.jdj.movie.controller;

import com.jdj.movie.bll.AreasBll;
import com.jdj.movie.model.Areas;
import com.jdj.movie.model.ReturnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by jiangdajun on 2018/7/5.
 */
@RestController
@RequestMapping("/areas")
public class AreasController {
    private final static Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private AreasBll areasBll;

    /**
     * @param null
     * @return areas list
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getAreasListInfo(
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "10") int limit
    ){
        int skip = (page-1)*limit;
        List<Areas> list = areasBll.getAreasList(skip,limit);
        int total = areasBll.getTotal();
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",list);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);
    }
    /**
     * @param area
     * @return true
     */
    @RequestMapping(value = "/addOrUpdate",method = RequestMethod.POST)
    public ReturnModel addOrUpdateArea(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "area") int area,
            @RequestParam(value = "title") String title
    ){
        Areas areas = new Areas();
        int flag = 0;
        areas.setArea(area);
        areas.setTitle(title);
        if(id.isEmpty()){
            areas.setId(UUID.randomUUID().toString().replace("-","").toLowerCase());
            logger.info("id值","：空，执行插入数据库操作");
            flag = areasBll.insertArea(areas);
        }else {
            areas.setId(id);
            logger.info("id值","："+id+";执行更新数据库操作");
            flag = areasBll.updateArea(areas);
        }
        int total = areasBll.getTotal();
        if(flag>0){
            logger.info("return","：插入成功");
            return new ReturnModel(0,total);
        }else {
            logger.info("return","：插入失败");
            return new ReturnModel(-1,false);
        }
    }
    /**
     * @content 删除一条记录
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ReturnModel deleteArea(
            @RequestParam(value = "id") String id
    ){
        int flag = areasBll.deleteArea(id);
        if(flag>0){
            return new ReturnModel(0,true);
        }else {
            return new ReturnModel(-1,flag);
        }
    }
    /**
     * @content 查询一条记录
     */
    @RequestMapping(value = "/getItem",method = RequestMethod.GET)
    public ReturnModel getAreaItem(
            @RequestParam(value = "id") String id
    ){
        Areas areas = areasBll.getItemById(id);
        return new ReturnModel(0,areas);
    }
    /**
     * @content 查询总共条数
     */
    @RequestMapping(value = "/getTotal",method = RequestMethod.GET)
    public ReturnModel totalCount(){
        int total = areasBll.getTotal();
        return new ReturnModel(0,total);
    }
}
