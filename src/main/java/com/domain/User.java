package com.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Users")
public class User implements java.io.Serializable{
    @Id
    private int idUser;
    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    @NotNull
    @Column(unique = true)
    private String mail;
    @NotNull
    private int alpha;
    private String login;
    private String passe;

    public User() {
    }

    public User(int idUser, String nom, String prenom, String mail, int alpha, String login, String passe) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.alpha = alpha;
        this.login = login;
        this.passe = passe;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasse() {
        return passe;
    }

    public void setPasse(String passe) {
        this.passe = passe;
    }
}
