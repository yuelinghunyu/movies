package com.jdj.movie.model;

/**
 * @param access_token token字段;
 * @param token_type token类型字段;
 * @param expires_in token 有效期字段;
 */
public class AccessToken {
    private String access_token;
    private String token_type;
    private long expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
