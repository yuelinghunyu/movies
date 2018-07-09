package com.jdj.movie.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangdajun on 2018/7/6.
 */
public class Utils {
    /**
     * 时间格式转化
     */
    public static String formatDate(Date dateData){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(dateData);
    }
}

