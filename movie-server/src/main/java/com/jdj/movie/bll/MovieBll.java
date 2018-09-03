package com.jdj.movie.bll;

import com.jdj.movie.mapper.MovieMapper;
import com.jdj.movie.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdajun on 2018/7/5.
 */
@Service
public class MovieBll {
    @Autowired
    private MovieMapper movieMapper;
    /**
     * @content 返回所有的movieList
     * @param null;
     */
    public List<Movie> getMovieList(
            String id,
            int area,
            String title,
            int type,
            int movieType,
            int skip,
            int limit
    ){
        return movieMapper.movieList(id,area,title,type,movieType,skip,limit);
    }
    /**
     * @content 插入数据
     * @Param movie
     */
    public int insertMovie(Movie movie){
        return  movieMapper.insert(movie);
    }

    /**
     * @result count
     */
    public int getTotal(
            String id,
            int area,
            String title,
            int type,
            int movieType
    ){
        return movieMapper.getMoviesCount(id,area,title,type,movieType);
    }
    /**
     * @param movie
     */
    public int updateMovie(Movie movie){
        return movieMapper.updateByPrimaryKey(movie);
    }
    /**
     * @param id
     */
    public int deleteMovie(String id){
        return movieMapper.deleteByPrimaryKey(id);
    }

    /**
     * @param title
     */
    public List<Movie> getMovieListLike(String title,int skip,int limit){
        return movieMapper.movieListBySearch(title,skip,limit);
    }
    public int getMovieListLikeCount(String title){
        return movieMapper.movieListBySearchCount(title);
    }
}
