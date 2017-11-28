package com.controller;

import com.domain.User;
import com.exception.EntityException;
import com.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/client")
public class ConnextionController {

    private UserRepository userRepository = new UserRepository();

    @PostMapping(value = "/login/{userId}/{password}")
    public boolean logIn(@PathVariable int userId, String password) throws EntityException{
        return userRepository.findOne(userId, password);

    }

}
