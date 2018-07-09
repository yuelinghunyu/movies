package com.jdj.movie.bll;

import com.jdj.movie.mapper.AreasMapper;
import com.jdj.movie.model.Areas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdajun on 2018/7/5.
 */

@Service
public class AreasBll {
    @Autowired
    private AreasMapper areasMapper;
    /**
     * @param null
     * @return 返回所有的areas
     */
    public List<Areas> getAreasList(int skip,int limit){
        return areasMapper.getListAreas(skip,limit);
    }
    /**
     * @param null
     * @return 返回总数
     */
    public int getTotal(){
        return areasMapper.getAreasCount();
    }
    /**
     * @param area
     * @return int
     */
    public int insertArea(Areas areas){
        return areasMapper.insert(areas);
    }
    /**
     * @param area
     * @return int
     */
    public int updateArea(Areas areas){
        return areasMapper.updateByPrimaryKey(areas);
    }
    /**
     * @param id
     * @return int
     */
    public int deleteArea(String id){
        return areasMapper.deleteByPrimaryKey(id);
    }

    public Areas getItemById(String id){
        return areasMapper.selectByPrimaryKey(id);
    }
}
