package com.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "AssociationUsersRobots")
@Data
public class AssociationUsersRobots {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NonNull
    private Integer idUser;
    @NonNull
    private Integer idRobot;

}
