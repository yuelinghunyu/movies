package com.jdj.movie.utils;

import com.aliyun.oss.OSSClient;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by jiangdajun on 2018/7/11.
 */
@Component
public class OSSClientUtil {
    @Autowired
    private Environment env;
    Log log = LogFactory.getLog(OSSClientUtil.class);
    // 华东 1 (杭州)：cn-hangzhou.log.aliyuncs.com
    private String endpoint = "oss-cn-beijing.aliyuncs.com";
    // accessKey
    private String accessKeyId = "LTAIuw9k1cverkbk";
    private String accessKeySecret = "79pIZBoioCbqctZM7v9g49Lyl58JZx";
    //空间
    private String bucketName = "yuelinghunyu";
//    private String endpoint = env.getProperty("lximage.endpoint");
//    // accessKey
//    private String accessKeyId = env.getProperty("lximage.keyid");
//    private String accessKeySecret = env.getProperty("lximage.keysecret");
//    //空间
//    private String bucketName = env.getProperty("lximage.bucketname1");
    //文件存储目录
    private String filedir = "data/";
    private OSSClient ossClient;

    public OSSClientUtil() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 初始化
     */
    public void init() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }

    /**
     * 上传图片
     * @param url
     */
    public void uploadImg2Oss(String url) throws IOException {
        File fileOnServer = new File(url);
        FileInputStream fin = new FileInputStream(fileOnServer);
        String[] split = url.split("/");
        this.uploadFile2OSS(fin, split[split.length - 1]);
    }

    public String uploadImg2Oss(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        InputStream inputStream = file.getInputStream();
        this.uploadFile2OSS(inputStream, name);
        return name;
    }
    /**
     * 获得图片路径
     *
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return this.getUrl(this.filedir + split[split.length - 1]);
        }
        return null;
    }
    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public String uploadFile2OSS(InputStream inputStream,String fileName) throws IOException {
        String ret = "";
        //创建上传Object的Metadata
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(inputStream.available());
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
        objectMetadata.setContentDisposition("inline;filename=" + fileName);
        //上传文件
        PutObjectResult putResult = ossClient.putObject(bucketName,filedir + fileName, inputStream, objectMetadata);
        ret = putResult.getETag();
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase("jpeg") ||
                FilenameExtension.equalsIgnoreCase("jpg") ||
                FilenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase("md")) {
            return "text/md";
        }
        if (FilenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase("pptx") ||
                FilenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase("docx") ||
                FilenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }
    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}
