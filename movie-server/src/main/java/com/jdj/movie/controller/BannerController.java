package com.jdj.movie.controller;

import com.jdj.movie.bll.BannerBll;
import com.jdj.movie.model.Banner;
import com.jdj.movie.model.ReturnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangdajun on 2018/11/10.
 */
@RestController
@RequestMapping("/banner")
public class BannerController {
    private final static Logger logger = LoggerFactory.getLogger(BannerController.class);
    @Autowired
    private BannerBll bannerBll;
    /**
     *@param type
     *@deprecated 根据类型返回小册、电影的轮播图
     *@return list
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getBannerList(
            @RequestParam(value = "type",required = false,defaultValue = "") int type,
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "10") int limit
    ){
        int skip = (page-1)*limit;
        List<Banner> list = bannerBll.getBannerList(type,skip,limit);
        int total = bannerBll.getTotalByType(type);
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("type",type);
        map.put("list",list);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);
    }
    /**
     * @param banner
     * @deprecated 新增或者更新
     * @return list
     */
    @RequestMapping(value = "/addOrUpdate",method = RequestMethod.POST)
    public ReturnModel setBanners(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "title",required = true) String title,
            @RequestParam(value = "type",required = true) int type,
            @RequestParam(value = "href",required = true) String href,
            @RequestParam(value = "OSSAccessKeyId") String keyId,
            @RequestParam(value = "Signature") String sign,
            @RequestParam(value = "redirect",required = false,defaultValue = "") String redirect
    )throws Exception{
        Banner banner = new Banner();
        banner.setTitle(title);
        banner.setType(type);
        String url = href + "&OSSAccessKeyId="+keyId+"&Signature="+ URLEncoder.encode(sign, "utf-8");
        banner.setHref(url);
        banner.setRedirect(redirect);
        int flag = 0;
        if(id.isEmpty()){//新增
            logger.info("id值","：空，执行插入数据库操作");
            flag = bannerBll.insertBanner(banner);
        }else{
            banner.setId(id);
            flag = bannerBll.updateBanner(banner);
        }
        int total = bannerBll.getTotalByType(type);
        if(flag>0){
            logger.info("banner","：插入成功");
            return new ReturnModel(0,total);
        }else {
            logger.info("banner","：插入失败");
            return new ReturnModel(-1,flag);
        }
    }
    /**
     * @param id
     * @deprecated 删除一条记录
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ReturnModel deleteBanner(
            @RequestParam(value = "id",required = true) String id
    ){
        Banner banner = bannerBll.getBanner(id);
        int flag = bannerBll.deleteBanner(id);
        Map<String,Object> map = new HashMap<>();
        map.put("type",banner.getType());
        if(flag>0){
            map.put("status",true);
            return new ReturnModel(0,map);
        }else {
            map.put("status",false);
            return new ReturnModel(-1,map);
        }
    }
}
