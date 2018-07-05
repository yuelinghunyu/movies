package com.jdj.movie.enums;

/**
 * Created by jiangdajun on 2018/7/5.
 */
public enum  StaticTypes {
    HOT(1, "最热"),//最热
    CLASSICAL(2, "经典"),//经典
    NEWS(3, "最新");//最新

    private  int code;
    private String value;

    StaticTypes(int code,String value){
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public static StaticTypes valueOf(int code){
        if(1==code){
            return HOT;
        }else if(2==code){
            return CLASSICAL;
        }else {
            return NEWS;
        }
    }
    public static StaticTypes toStaticType(String str){
        if("最热"==str){
            return HOT;
        }else if("经典"==str){
            return CLASSICAL;
        }else {
            return NEWS;
        }
    }

}

