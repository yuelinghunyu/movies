package com.jdj.movie.bll;

import com.jdj.movie.mapper.PersonMapper;
import com.jdj.movie.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jdj on 2018/4/23.
 */
@Service
public class PersonBll {
    @Autowired
    private PersonMapper personMapper;

    //查询用户是否存在
    public String getPersonExist(String userName,String passWord) {
        return personMapper.selectExist(userName,passWord);
    }
    //查询所有用户；
    public List<Person> selectAll(){
        return personMapper.selectAll();
    }
}
