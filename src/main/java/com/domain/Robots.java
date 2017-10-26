package com.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Robots")
@Data
public class Robots {
    @Id
    private Integer idRobot;
    @NotNull
    @Column(unique = true)
    private String numSerie;
}
