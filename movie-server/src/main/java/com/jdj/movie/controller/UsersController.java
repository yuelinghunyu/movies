package com.jdj.movie.controller;

import com.jdj.movie.bll.UsersBll;
import com.jdj.movie.model.ReturnModel;
import com.jdj.movie.model.Users;
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
@RequestMapping("/users")
public class UsersController {
    private final static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersBll usersBll;

    @RequestMapping(value = "/attention",method = RequestMethod.POST)
    public ReturnModel insertUsers(
            @RequestParam(value = "wechatId",required = true) String wechatId,
            @RequestParam(value = "wechatName",required = true) String wechatName,
            @RequestParam(value = "wechatLogo",required = true) String wechatLogo
    ){
        Users users = new Users();
        users.setWechatId(wechatId);
        users.setWechatName(wechatName);
        users.setWechatLogo(wechatLogo);

        int flag = usersBll.insertUserItem(users);
        int total = usersBll.getUserCount("","");
        if(flag>0){
            logger.info("info","：用户关注成功");
            return new ReturnModel(0,total);
        }else {
            logger.error("return","：用户未关注");
            return new ReturnModel(-1,flag);
        }
    }

    @RequestMapping(value = "/getCount",method = RequestMethod.GET)
    public ReturnModel getTotalCount(
            @RequestParam(value = "wechatId",required = false,defaultValue = "") String wechatId,
            @RequestParam(value = "wechatName",required = false,defaultValue = "") String wechatName
    ){
        int totalCount = usersBll.getUserCount(wechatId,wechatName);
        Map map = new HashMap<>();
        map.put("total",totalCount);
        return new ReturnModel(0,map);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getUsersList(
            @RequestParam(value = "wechatId",required = false,defaultValue = "") String wechatId,
            @RequestParam(value = "wechatName",required = false,defaultValue = "") String wechatName,
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "9") int limit

    ){
        int skip = (page-1)*limit;
        List<Users> usersList = usersBll.getUserList(wechatId,wechatName,skip,limit);
        int total = usersBll.getUserCount(wechatId,wechatName);
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",usersList);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);
    }
}
