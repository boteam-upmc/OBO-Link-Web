package com.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Robots")
public class Robot implements java.io.Serializable{
    @Id
    private int idRobot;
    @NotNull
    @Column(unique = true)
    private String numSerie;

    public Robot() {
    }

    public Robot(Integer idRobot, String numSerie) {
        this.idRobot = idRobot;
        this.numSerie = numSerie;
    }

    public Integer getIdRobot() {
        return idRobot;
    }

    public void setIdRobot(Integer idRobot) {
        this.idRobot = idRobot;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }
}
