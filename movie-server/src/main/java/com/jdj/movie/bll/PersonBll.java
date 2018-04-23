package com.jdj.movie.bll;

import com.jdj.movie.mapper.PersonMapper;
import com.jdj.movie.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jdj on 2018/4/23.
 */
@Service
public class PersonBll {
    @Autowired
    private PersonMapper personMapper;

    //查询所有用户
    public Person getPersonList(Integer id) {
        return personMapper.selectByPrimaryKey(id);
    }
}
