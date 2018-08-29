package com.jdj.movie.controller;

import com.jdj.movie.model.ReturnModel;
import com.jdj.movie.utils.OSSClientUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangdajun on 2018/7/11.
 */
@RestController
@RequestMapping(value = "/file")
public class FileUploadController {
    @Autowired
    private OSSClientUtil ossClientUtil;
    /**
     * 上传到阿里云，返回url；
     * @param uploader 文件file
     */
    @RequestMapping(value = "/toOssServer",method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel resUrl(
            @Param("uploader") MultipartFile uploader
    ) throws IOException {
        if(uploader == null || uploader.getSize() <= 0){
            return new ReturnModel(-1,"file is empty");
        }
        Map<String,Object> map = new HashMap<>();
        String name = ossClientUtil.uploadImg2Oss(uploader);
        String imgUrl = ossClientUtil.getImgUrl(name);
        map.put("fileUrl",imgUrl);
        return new ReturnModel(0,map);
    }
}
