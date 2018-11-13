package com.jdj.movie.bll;

import com.jdj.movie.mapper.BannerMapper;
import com.jdj.movie.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdajun on 2018/11/10.
 */
@Service
public class BannerBll {
    @Autowired
    private BannerMapper bannerMapper;

    /**
     * @param skip,limit
     * @deprecated 返回所有的轮播图列表
     */
    public List<Banner> getBannerList(int type,int skip,int limit){
        return bannerMapper.getBannerList(type,skip,limit);
    }

    /**
     * @param type
     * @deprecated 返回数量
     */
    public int getTotalByType(int type){
        return bannerMapper.getBannerCount(type);
    }

    /**
     * @param banner
     * @deprecated 插入数据
     */
    public int insertBanner(Banner banner){
        return bannerMapper.insert(banner);
    }

    /**
     * @param banner
     * @deprecated 更新数据
     */
    public int updateBanner(Banner banner){
        return bannerMapper.updateByPrimaryKey(banner);
    }

}
