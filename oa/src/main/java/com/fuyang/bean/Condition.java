package com.fuyang.bean;

import java.io.Serializable;

public class Condition implements Serializable {

    private static final long serialVersionUID = -3798318510090697101L;
    private String conname;
    private String keyword;
    private String orderby;

    public String getConname() {
        return conname;
    }

    public void setConname(String conname) {
        this.conname = conname;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }
}
