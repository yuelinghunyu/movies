package com.jdj.movie.controller;

import com.jdj.movie.bll.FeedBackBll;
import com.jdj.movie.model.FeedBack;
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
 * Created by jiangdajun on 2018/12/2.
 */
@RestController
@RequestMapping("/feed-back")
public class FeedBackController {
    private final static Logger logger = LoggerFactory.getLogger(FeedBackController.class);
    @Autowired
    private FeedBackBll feedBackBll;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getFeedBackList(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "wechatName",required = false,defaultValue = "") String wechatName,
            @RequestParam(value = "feedType",required = false,defaultValue = "0") int feedType,
            @RequestParam(value = "type",required = false,defaultValue = "0") int type,
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "9") int limit

    ){
        int skip = (page-1)*limit;
        List<FeedBack> feedBackList = feedBackBll.getFeedBackList(id,wechatName,feedType,type,skip,limit);
        int total = feedBackBll.getFeedBackCount(id,wechatName,feedType,type);
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",feedBackList);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);

    }

    @RequestMapping(value = "/set-feed-back",method = RequestMethod.POST)
    public ReturnModel inserFeedBack(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "wechatId",required = true) String wechatId,
            @RequestParam(value = "wechatName",required = true) String wechatName,
            @RequestParam(value = "feedType",required = true) int feedType,
            @RequestParam(value = "type",required = true) int type,
            @RequestParam(value = "content",required = true) String content
    ){
        FeedBack feedBack = new FeedBack();
        feedBack.setId(id);
        feedBack.setWechatId(wechatId);
        feedBack.setWechatName(wechatName);
        feedBack.setFeedType(feedType);
        feedBack.setType(type);
        feedBack.setContent(content);
        int flag = feedBackBll.insertFeedBack(feedBack);
        int total = feedBackBll.getFeedBackCount("","",feedType,0);
        if(flag>0){
            logger.info("info","：反馈成功");
            return new ReturnModel(0,total);
        }else {
            logger.error("return","：插入失败");
            return new ReturnModel(-1,flag);
        }
    }

    @RequestMapping(value = "/getCount",method = RequestMethod.GET)
    public ReturnModel getTotalCount(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "wechatName",required = false,defaultValue = "") String wechatName,
            @RequestParam(value = "feedType",required = false,defaultValue = "0") int feedType,
            @RequestParam(value = "type",required = false,defaultValue = "0") int type
    ){
        int totalCount = feedBackBll.getFeedBackCount(id,wechatName,feedType,type);
        Map map = new HashMap<>();
        map.put("total",totalCount);
        return new ReturnModel(0,map);
    }
}
