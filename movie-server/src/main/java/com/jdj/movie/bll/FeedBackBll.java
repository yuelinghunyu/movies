package com.jdj.movie.bll;

import com.jdj.movie.mapper.FeedBackMapper;
import com.jdj.movie.model.FeedBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdajun on 2018/12/2.
 */
@Service
public class FeedBackBll {
    @Autowired
    private FeedBackMapper feedBackMapper;

    public int insertFeedBack(FeedBack feedBack){
        return feedBackMapper.insert(feedBack);
    }

    public List<FeedBack> getFeedBackList(
            String id,
            String wechatName,
            int feedType,
            int type,
            int skip,
            int limit
    ){
        return feedBackMapper.selectByParam(id,wechatName,feedType,type,skip,limit);
    }

    public int deleteFeedBack(String id){
        return feedBackMapper.deleteByPrimaryKey(id);
    }
    public int getFeedBackCount(
            String id,
            String wechatName,
            int feedType,
            int type
    ){
        return feedBackMapper.getFeedBackCount(id,wechatName,feedType,type);
    }
}
