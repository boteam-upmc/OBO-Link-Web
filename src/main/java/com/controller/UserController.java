package com.controller;


import com.domain.User;
import com.exception.EntityException;
import com.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    private UserRepository userRepository = new UserRepository();

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) throws EntityException {
        return userRepository.findById(id); }
}
