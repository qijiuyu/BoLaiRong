package com.zxdc.utils.library.bean;

import java.io.Serializable;
import java.util.List;

public class Notice extends BaseBean {

    private NoticeBean data;

    public NoticeBean getData() {
        return data;
    }

    public void setData(NoticeBean data) {
        this.data = data;
    }

    public static class NoticeBean implements Serializable{

        private List<ListBean> rows;

        public List<ListBean> getRows() {
            return rows;
        }

        public void setRows(List<ListBean> rows) {
            this.rows = rows;
        }
    }


    public static class ListBean implements Serializable{
        private int id;
        private String title="";
        private String content="";
        private String createName="";
        private String createDate="";

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
