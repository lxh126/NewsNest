package com.app.luoxueheng.entity;

public class AbstractInfo {
    private int AbstractID;
    private String newsID;

    public String getAbstract() {
        return Abstract;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public int getAbstractID() {
        return AbstractID;
    }

    public void setAbstractID(int abstractID) {
        AbstractID = abstractID;
    }

    private String Abstract;

    public AbstractInfo(int AbstractID, String newsID, String Abstract) {
        this.AbstractID = AbstractID;
        this.newsID = newsID;
        this.Abstract = Abstract;
    }

}
