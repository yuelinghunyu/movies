package com.jdj.movie.controller;

import com.jdj.movie.bll.PersonBll;
import com.jdj.movie.model.Person;
import com.jdj.movie.model.ReturnModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jdj on 2018/4/23.
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    private PersonBll personBll;
    /**
     * @content:根据id对应的person
     * @param id=1;
     * @return returnModel
     */
    @RequestMapping(value = "/exsit",method = RequestMethod.GET)
    public ReturnModel exsit(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "passWord") String passWord
    ){
        String id = personBll.getPersonExist(userName,passWord);
        if(id!=null||id.length()<0){
            return new ReturnModel(-1,null);
        }else {
            return new ReturnModel(0,id);
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
            return new ReturnModel(1,list);
        }
    }
}
