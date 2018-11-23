package com.jdj.movie.controller;

import com.jdj.movie.bll.BlogBll;
import com.jdj.movie.bll.BookTypeBll;
import com.jdj.movie.model.Blog;
import com.jdj.movie.model.BlogConvert;
import com.jdj.movie.model.BookType;
import com.jdj.movie.model.ReturnModel;
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
 * Created by jiangdajun on 2018/11/22.
 */
@RestController
@RequestMapping("/blog")
public class BlogsController {
    private final static Logger logger = LoggerFactory.getLogger(BlogsController.class);
    @Autowired
    private BlogBll blogBll;
    @Autowired
    private BookTypeBll bookTypeBll;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel getBlogList(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "title",required = false,defaultValue = "") String title,
            @RequestParam(value = "blogType",required = false,defaultValue = "-1") int blogType,
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "limit",required = false,defaultValue = "9") int limit
    ){
        int skip = (page-1)*limit;
        List<Blog> blogList = blogBll.getBlogList(id,title,blogType,skip,limit);
        List<BlogConvert> blogConvertList = new ArrayList<>();

        int bookTypeTotal = bookTypeBll.getTotalCount();
        List<BookType> bookTypeList = bookTypeBll.getBookTypeList(0,bookTypeTotal);

        for(int i=0;i<blogList.size();i++){
            BlogConvert blogConvert = CovertUtils.convertBlog(blogList.get(i),bookTypeList);
            blogConvertList.add(blogConvert);
        }
        int total = blogBll.getBlogCount(blogType);
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("list",blogConvertList);
        logger.info("返回成功",true);
        return new ReturnModel(0,map);
    }
    @RequestMapping(value = "/addOrUpdate",method = RequestMethod.POST)
    public ReturnModel addOrUpdateBlog(
            @RequestParam(value = "id",required = false,defaultValue = "") String id,
            @RequestParam(value = "title",required = true) String title,
            @RequestParam(value = "blogType",required = true) int blogType,
            @RequestParam(value = "href",required = true) String href,
            @RequestParam(value = "times",required = false,defaultValue = "") int times
    ){
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setBlogType(blogType);
        blog.setHref(href);
        blog.setTimes(times);
        int flag = 0;
        if(id.isEmpty()){
            logger.info("id值","：空，执行插入数据库操作");
            flag = blogBll.insertBlog(blog);
        }else {
            logger.info("id值","："+id+";执行更新数据库操作");
            blog.setId(id);
            flag = blogBll.update(blog);
        }
        int total = blogBll.getBlogCount(-1);
        if(flag>0){
            logger.info("info","：插入或更新成功");
            return new ReturnModel(0,total);
        }else {
            logger.info("return","：插入失败");
            return new ReturnModel(-1,flag);
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ReturnModel delete(
            @RequestParam(value = "id") String id
    ){
        int flag = blogBll.delete(id);
        if(flag>0){
            return new ReturnModel(0,true);
        }else {
            return new ReturnModel(-1,flag);
        }
    }
}
