package com.controller;

import com.domain.UsersRobots;
import com.exception.EntityException;
import com.repository.UserRobotResopitory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/{idUser}/users_robots")
public class UserRobotController {
    private UserRobotResopitory repository = new UserRobotResopitory();

    @GetMapping(value = "/all")
    public List<UsersRobots> get(@PathVariable int idUser) throws EntityException{
        return repository.findByUserId(idUser); }
}
