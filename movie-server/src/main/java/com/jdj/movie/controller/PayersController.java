package com.jdj.movie.controller;

import com.jdj.movie.bll.PayersBll;
import com.jdj.movie.model.Payers;
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
@RequestMapping("/payers")
public class PayersController {
    private final static Logger logger = LoggerFactory.getLogger(PayersController.class);

    @Autowired
    private PayersBll payersBll;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getPayerList(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "wechatId",required = false,defaultValue = "") String wechatId,
            @RequestParam(value = "wechatName",required = false,defaultValue = "") String wechatName,
            @RequestParam(value = "bookId",required = false,defaultValue = "") String bookId,
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "9") int limit
    ){
        int skip = (page-1)*limit;
        List<Payers> payersList = payersBll.getPayersList(id,wechatId,wechatName,bookId,skip,limit);
        int total = payersBll.getPayersCount(id,wechatId,wechatName,bookId);
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",payersList);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);
    }

    @RequestMapping(value = "/getCount",method = RequestMethod.GET)
    public ReturnModel getTotalCount(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "wechatId",required = false,defaultValue = "") String wechatId,
            @RequestParam(value = "wechatName",required = false,defaultValue = "") String wechatName,
            @RequestParam(value = "bookId",required = false,defaultValue = "") String bookId
    ){
        int totalCount = payersBll.getPayersCount(id,wechatId,wechatName,bookId);
        Map map = new HashMap<>();
        map.put("total",totalCount);
        return new ReturnModel(0,map);
    }

    @RequestMapping(value = "/doPayer",method = RequestMethod.POST)
    public ReturnModel setPayer(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "wechatId",required = true) String wechatId,
            @RequestParam(value = "wechatName",required = true) String wechatName,
            @RequestParam(value = "wechatLogo",required = true) String wechatLogo,
            @RequestParam(value = "bookId",required = true) String bookId
    ){
        Payers payers = new Payers();
        payers.setWechatId(wechatId);
        payers.setWechatName(wechatName);
        payers.setLogo(wechatLogo);
        payers.setBooks(bookId);

        int flag = payersBll.insertPayers(payers);
        int total = payersBll.getPayersCount("","","","");
        if(flag>0){
            logger.info("info","：购买成功");
            return new ReturnModel(0,total);
        }else {
            logger.error("return","：插入失败");
            return new ReturnModel(-1,flag);
        }
    }
}
