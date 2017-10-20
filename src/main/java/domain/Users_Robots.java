package domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Users_Robots")
@Data
public class Users_Robots {
    @Id
    private Integer idUsers;
    @Id
    private Integer idRobots;
}
