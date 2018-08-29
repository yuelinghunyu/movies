package com.jdj.movie.model;

public class Token {
    private String clientId;
    private String base64Secret;
    private long expiresSecond;
    private String userName;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public long getExpiresSecond() {
        return expiresSecond;
    }

    public void setExpiresSecond(long expiresSecond) {
        this.expiresSecond = expiresSecond;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
