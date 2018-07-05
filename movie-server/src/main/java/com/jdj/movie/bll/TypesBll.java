package com.jdj.movie.bll;

import com.jdj.movie.mapper.TypesMapper;
import com.jdj.movie.model.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdajun on 2018/7/5.
 */
@Service
public class TypesBll {
    @Autowired
    private TypesMapper typesMapper;

    public List<Types> getTypeList(int skip,int limit){
        return  typesMapper.getListTypes(skip,limit);
    }
    public int getTotal(){
        return typesMapper.getTypesCount();
    }
    public int insertTypes(Types types){
        return typesMapper.insert(types);
    }
    public int updateTypes(Types types){
        return typesMapper.updateByPrimaryKey(types);
    }
    public int deleteType(String id){
        return typesMapper.deleteByPrimaryKey(id);
    }

}
