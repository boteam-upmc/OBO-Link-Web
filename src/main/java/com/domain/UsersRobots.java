package com.domain;

import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "Users_Robots")
public class UsersRobots implements java.io.Serializable{
    @Id
    @NonNull
    private Integer idUser;
    @Id
    @NonNull
    private Integer idRobot;

    public UsersRobots() {
    }

    public UsersRobots(Integer idUser, Integer idRobot) {
        this.idUser = idUser;
        this.idRobot = idRobot;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdRobot() {
        return idRobot;
    }

    public void setIdRobot(Integer idRobot) {
        this.idRobot = idRobot;
    }
}
