package com.controller;

import com.domain.User;
import com.exception.EntityException;
import com.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/client")
public class ConnextionController {

    private UserRepository userRepository = new UserRepository();

    @GetMapping(value = "/login")
    public User logIn(@RequestParam("pseudo") int pseudo,
                      @RequestParam("password") String password) throws EntityException{

        User user =  userRepository.findOne(pseudo, password);
        System.out.println("USER=="+ user.getPrenom()+ user.getAlpha());
        return user;
    }



}
