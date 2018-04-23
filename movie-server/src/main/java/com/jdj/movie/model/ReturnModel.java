package com.jdj.movie.model;

/**
 * Created by jdj on 2018/4/23.
 */
public class ReturnModel<T> {
    private int code;
    private T data;

    public ReturnModel() {
        this.code = 0;
        this.data = null;
    }

    public ReturnModel(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
