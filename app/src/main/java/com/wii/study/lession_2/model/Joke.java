package com.wii.study.lession_2.model;

/**
 * Created by wu on 2017/3/6.
 */

public class Joke {
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private  String title;
    private  String content;
    private String poster;// 笑话插图（不是全部笑话都有插图）
    private  String url; //来源地址
}
