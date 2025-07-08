package com.app.luoxueheng.entity;

public class CollectionInfo {
    private int collection_id;
    private String newsID;
    private String username;
    private String new_json;

    public CollectionInfo(int history_id, String newsID, String username, String new_json) {
        this.collection_id = collection_id;
        this.newsID = newsID;
        this.username = username;
        this.new_json = new_json;
    }

    public int getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(int history_id) {
        this.collection_id = collection_id;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNew_json() {
        return new_json;
    }

    public void setNew_json(String new_json) {
        this.new_json = new_json;
    }
}