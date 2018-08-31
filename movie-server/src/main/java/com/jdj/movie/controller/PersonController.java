package com.jdj.movie.controller;

import com.jdj.movie.bll.PersonBll;
import com.jdj.movie.model.AccessToken;
import com.jdj.movie.model.Audience;
import com.jdj.movie.model.Person;
import com.jdj.movie.model.ReturnModel;
import com.jdj.movie.utils.CreateTokenUtils;
import com.jdj.movie.utils.Md5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jdj on 2018/4/23.
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    private PersonBll personBll;
    @Autowired
    private Audience audience;
    /**
     * @content:根据id对应的person
     * @param id=1;
     * @return returnModel
     */
    @RequestMapping(value = "/exsit",method = RequestMethod.POST)
    public ReturnModel exsit(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "passWord") String passWord
    ){
        String md5PassWord = Md5Utils.getMD5(passWord);
        String id = personBll.getPersonExist(userName,md5PassWord);
        if(id == null||id.length()<0){
            return new ReturnModel(-1,null);
        }else {
            Map<String,Object> map = new HashMap<>();
            Person person = personBll.getPerson(id);
            map.put("person",person);
            String accessToken = CreateTokenUtils
                    .createJWT(userName,audience.getClientId(), audience.getName(),audience.getExpiresSecond() * 1000, audience.getBase64Secret());
            AccessToken accessTokenEntity = new AccessToken();
            accessTokenEntity.setAccess_token(accessToken);
            accessTokenEntity.setExpires_in(audience.getExpiresSecond());
            accessTokenEntity.setToken_type("bearer");
            map.put("accessToken",accessTokenEntity);
            return new ReturnModel(0,map);
        }
    }
    /**
     * @content:list
     * @param null;
     * @return returnModel
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ReturnModel list(){
        List<Person> list = personBll.selectAll();
        if(list.size()==0){
            return new ReturnModel(-1,null);
        }else {
            return new ReturnModel(0,list);
        }
    }

    @RequestMapping(value = "/item",method = RequestMethod.GET)
    public ReturnModel getItem(
            @RequestParam(value = "id") String id
    ){
        Person person = personBll.getPerson(id);
        if(person != null){
            return new ReturnModel(0,person);
        }else {
            return new ReturnModel(-1,"无此用户");
        }
    }
}
