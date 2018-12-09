package com.jdj.movie.controller;

import com.jdj.movie.model.ReturnModel;
import com.jdj.movie.utils.FileUtil;
import com.jdj.movie.utils.OSSClientUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jiangdajun on 2018/7/11.
 */
@RestController
@RequestMapping(value = "/file")
public class FileUploadController {
    @Autowired
    private OSSClientUtil ossClientUtil;
    @Autowired
    private Environment env;
    /**
     * 上传到阿里云，返回url；
     * @param uploader 文件file
     */
    @RequestMapping(value = "/toOssServer",method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel resUrl(
            @Param("uploader") MultipartFile uploader
    ) throws IOException {
        String BASE_PATH = env.getProperty("file.path");
        if(uploader == null || uploader.getSize() <= 0){
            return new ReturnModel(-1,"file is empty");
        }
        Map<String,Object> map = new HashMap<>();
        if(uploader.getContentType().equals("application/octet-stream")){
            String filename = uploader.getOriginalFilename();

            String ext= null;
            if(filename.contains(".")){
                ext = filename.substring(filename.lastIndexOf("."));
            }else{
                ext = "";
            }

            String uuid =  UUID.randomUUID().toString().replaceAll("-", "");
            String nfileName = uuid + ext;
            String filepath = BASE_PATH.endsWith("/") ? BASE_PATH : BASE_PATH+"/";
            try {
                FileUtil.uploadFile(uploader.getBytes(), filepath, nfileName);
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
            String accessUrl =  nfileName;
            map.put("fileUrl",accessUrl);
        }else {
            String name = ossClientUtil.uploadImg2Oss(uploader);
            String imgUrl = ossClientUtil.getImgUrl(name);
            map.put("fileUrl",imgUrl);
        }
        return new ReturnModel(0,map);
    }
    
    @RequestMapping(value = "/getChapters",method = RequestMethod.GET)
    public ReturnModel getChapter(
            @RequestParam(value = "url",required = false,defaultValue = "") String url
    )throws Exception{
        String BASE_PATH = env.getProperty("file.path");
        String filepath = BASE_PATH.endsWith("/") ? BASE_PATH : BASE_PATH+"/";
        String files = filepath+url;
        File file = new File(files);
        FileInputStream fileInputStream = new FileInputStream(file);
        int count = fileInputStream.available();
        byte[] buf = new byte[count];
        int length = 0;
        String content = "";
        while((length = fileInputStream.read(buf)) != -1){
            content = new String(buf,0,length);
        }
        //最后记得，关闭流
        fileInputStream.close();
        Map map = new HashMap<>();
        map.put("content",content);
        return new ReturnModel(0,map);
    }
}
