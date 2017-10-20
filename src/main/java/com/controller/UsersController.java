package com.controller;


import com.domain.Users;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/users")
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/{idUsers}")
    public Users get(@PathVariable("idUsers") Integer idUsers){ return userRepository.findOne(idUsers); }
}
