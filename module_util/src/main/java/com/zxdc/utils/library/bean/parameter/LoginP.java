package com.zxdc.utils.library.bean.parameter;

import java.io.Serializable;

public class LoginP implements Serializable {

    private String username;
    private String pwd;

    public LoginP(){}

    public LoginP(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
