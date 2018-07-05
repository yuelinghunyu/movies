package com.jdj.movie.controller;

import com.jdj.movie.bll.TypesBll;
import com.jdj.movie.model.ReturnModel;
import com.jdj.movie.model.Types;
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
import java.util.UUID;

/**
 * Created by jiangdajun on 2018/7/5.
 */
@RestController
@RequestMapping("/types")
public class TypesController {
    private final static Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private TypesBll typesBll;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getTypeListInfo(
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "10") int limit
    ){
        int skip = (page-1)*limit;
        List<Types> list = typesBll.getTypeList(skip,limit);
        if(list.size()==0){
            logger.info("list的长度",list.size());
            return new ReturnModel(0,null);
        }
        int total = typesBll.getTotal();
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",list);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);
    }
    @RequestMapping(value = "/addOrUpdate",method = RequestMethod.POST)
    public ReturnModel addOrUpdateType(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "type") int type,
            @RequestParam(value = "title") String title
    ){
        Types types = new Types();
        int flag = 0;
        if(id == ""){
            types.setId(UUID.randomUUID().toString().replace("-","").toLowerCase());
            logger.info("id值","：空，执行插入数据库操作");
            flag = typesBll.insertTypes(types);
        }else {
            types.setId(id);
            logger.info("id值","："+id+";执行更新数据库操作");
            flag = typesBll.updateTypes(types);
        }
        if(flag>0){
            logger.info("return","：插入成功");
            return new ReturnModel(0,true);
        }else {
            logger.info("return","：插入失败");
            return new ReturnModel(-1,flag);
        }
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ReturnModel deleteType(
            @RequestParam(value = "id") String id
    ){
        int flag = typesBll.deleteType(id);
        if(flag>0){
            return new ReturnModel(0,true);
        }else {
            return new ReturnModel(-1,flag);
        }
    }
}
