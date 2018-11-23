package com.jdj.movie.bll;

import com.jdj.movie.mapper.BlogMapper;
import com.jdj.movie.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdajun on 2018/11/22.
 */
@Service
public class BlogBll {
    @Autowired
    private BlogMapper blogMapper;
    public List<Blog> getBlogList(
            String id,
            String title,
            int blogType,
            int skip,
            int limit
    ){
        return blogMapper.blogList(id,title,blogType,skip,limit);
    }

    public int getBlogCount(int blogType){
        return blogMapper.getBlogsCount(blogType);
    }

    public int insertBlog(Blog blog){
        return blogMapper.insert(blog);
    }
    public int update(Blog blog){
        return blogMapper.updateByPrimaryKey(blog);
    }
    public int delete(String id){
        return blogMapper.deleteByPrimaryKey(id);
    }
}
