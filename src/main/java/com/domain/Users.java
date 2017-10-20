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
@Data
@NoArgsConstructor
public class Users{
    @Id
    private Integer idUsers;
    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    @NotNull
    @Column(unique = true)
    private String mail;
    @NotNull
    private Integer alpha;
}
