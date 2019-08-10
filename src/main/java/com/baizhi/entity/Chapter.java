package com.baizhi.entity;

import java.util.Date;

/**
 * Created by Administrator on 2019/7/31.
 */
public class Chapter {
    private  String id;
    private  String title;
    private  String url;
    private  String newsize;
    private  String duration;
    private  Date up_date;
    private  String album_id;

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", newsize=" + newsize +
                ", duration='" + duration + '\'' +
                ", up_date=" + up_date +
                ", album_id='" + album_id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getNewsize() {
        return newsize;
    }

    public void setNewsize(String newsize) {
        this.newsize = newsize;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }
}
