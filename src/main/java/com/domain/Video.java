package com.domain;

import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Videos")
public class Video implements java.io.Serializable {
    @Id
    private int idVideo;
    @NonNull
    private int idUser;
    private int idRobot;
    private String title;
    private String date;
    private String urlImage;
    private String urlVideo;
    private String duration;


    public Video() {
    }

    public Video(int idVideo, int idUser, int idRobot, String title, String date, String urlImage, String urlVideo, String duration) {
        this.idVideo = idVideo;
        this.idUser = idUser;
        this.idRobot = idRobot;
        this.title = title;
        this.date = date;
        this.urlImage = urlImage;
        this.urlVideo = urlVideo;
        this.duration = duration;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRobot() {
        return idRobot;
    }

    public void setIdRobot(int idRobot) {
        this.idRobot = idRobot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
