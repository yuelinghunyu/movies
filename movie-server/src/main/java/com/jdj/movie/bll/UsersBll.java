package com.jdj.movie.bll;

import com.jdj.movie.mapper.UsersMapper;
import com.jdj.movie.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdajun on 2018/11/30.
 */
@Service
public class UsersBll {
    @Autowired
    private UsersMapper usersMapper;

    public int insertUserItem(Users users){
        return usersMapper.insert(users);
    }
    public List<Users> getUserList(String wechatName,int skip,int limit){
        return usersMapper.selectByName(wechatName,skip,limit);
    }

    public int getUserCount(String wechatName){
        return  usersMapper.getUsersCount(wechatName);
    }

}
