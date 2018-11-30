package com.jdj.movie.bll;

import com.jdj.movie.mapper.PayersMapper;
import com.jdj.movie.model.Payers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdajun on 2018/11/29.
 */
@Service
public class PayersBll {
    @Autowired
    private PayersMapper payersMapper;

    public List<Payers> getPayersList(
            String id,
            String wechatId,
            String wechatName,
            String bookId,
            int skip,
            int limit
    ){
        return payersMapper.selectByParams(id,wechatId,wechatName,bookId,skip,limit);
    }

    public int insertPayers(Payers payers){
        return payersMapper.insert(payers);
    }

    public int getPayersCount(
            String id,
            String wechatId,
            String wechatName,
            String bookId
    ){
        return payersMapper.getPayersCount(id,wechatId,wechatName,bookId);
    }
}
