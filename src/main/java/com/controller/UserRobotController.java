package com.controller;

import com.domain.UsersRobots;
import com.exception.EntityException;
import com.repository.UserRobotResopitory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping(value = "api/{idUser}/users_robots")
public class UserRobotController {

    private UserRobotResopitory repository = new UserRobotResopitory();

    @GetMapping()
    public List<UsersRobots> getAssociation(@PathVariable int idUser) throws EntityException{
        return repository.findByUserId(idUser);
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsersRobots> deleteAssociation(@RequestBody UsersRobots userRobot) throws EntityException {
        return repository.deleteAssociation(userRobot);
    }
}


