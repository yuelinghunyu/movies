package com.jdj.movie.utils;

import com.jdj.movie.model.Areas;
import com.jdj.movie.model.Movie;
import com.jdj.movie.model.MovieConvert;
import com.jdj.movie.model.Types;

import java.util.List;

/**
 * Created by jiangdajun on 2018/7/13.
 */
public class CovertUtils {
    //将对应的类型转换成字符串中文;
    public static String getAreaTitle(int area, List<Areas> list){
        for (Areas areas:list){
            if(areas.getArea() == area) {
                return areas.getTitle();
            }
        }
        return "";
    }

    public static String getTypeTitle(int type, List<Types> list){
        for (Types types:list){
            if(types.getType() == type){
                return types.getTitle();
            }
        }
        return "";
    }

    public static String getIsFreeTitle(int isFree){
        String isFreeVal = "";
        switch (isFree){
            case 0:
                isFreeVal = "否";
                break;
            case 1:
                isFreeVal = "是";
                break;
        }
        return isFreeVal;
    }


    public static MovieConvert covertMovie(Movie movie,List<Areas> areaslist, List<Types> typeslist){
        MovieConvert movieConvert = new MovieConvert();
        movieConvert.setId(movie.getId());
        movieConvert.setArea(movie.getArea());
        movieConvert.setAreaTitle(getAreaTitle(movie.getArea(),areaslist));
        movieConvert.setPicUrl(movie.getPicUrl());
        movieConvert.setContent(movie.getContent());
        movieConvert.setTitle(movie.getTitle());
        movieConvert.setType(movie.getType());
        movieConvert.setTypeTitle(getTypeTitle(movie.getType(),typeslist));
        movieConvert.setPrice(movie.getPrice());
        movieConvert.setCount(movie.getCount());
        movieConvert.setMovieType(movie.getMovieType());
        movieConvert.setIsFree(movie.getIsFree());
        movieConvert.setIsFreeTitle(getIsFreeTitle(movie.getIsFree()));
        movieConvert.setCreateTime(movie.getCreateTime());
        movieConvert.setModifyTime(movie.getModifyTime());
        movieConvert.setDescription(movie.getDescription());
        return movieConvert;
    }
}
