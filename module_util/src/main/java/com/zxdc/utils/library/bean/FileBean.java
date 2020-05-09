package com.zxdc.utils.library.bean;

import java.io.Serializable;

public class FileBean implements Serializable {

    private int id;
    private int fid;
    private int type;
    private String url;

    public FileBean() {
    }

    public FileBean(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }
}
