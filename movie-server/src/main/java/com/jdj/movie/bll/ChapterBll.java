package com.jdj.movie.bll;

import com.jdj.movie.mapper.ChaptersMapper;
import com.jdj.movie.model.Chapters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdajun on 2018/11/29.
 */
@Service
public class ChapterBll {
    @Autowired
    private ChaptersMapper chaptersMapper;

    public int insertChapter(Chapters chapters){
        return chaptersMapper.insert(chapters);
    }

    public List<Chapters> getChapterList(
            String id,
            String bookId,
            String bookTitle,
            String title,
            int skip,
            int limit
    ){
        return chaptersMapper.selectByChapterParam(id,bookId,bookTitle,title,skip,limit);
    }

    public int updateChapter(Chapters chapters){
        return chaptersMapper.updateByPrimaryKey(chapters);
    }

    public int deleteChapterItem(String id,String bookId){
        return chaptersMapper.deleteByParam(id,bookId);
    }

    public int getChapterCount(String id,String bookId,String bookTitle,String title){
        return chaptersMapper.getChapterCount(id,bookId,bookTitle,title);
    }

}
