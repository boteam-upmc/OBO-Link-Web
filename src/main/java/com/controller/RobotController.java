package com.controller;


import com.domain.Robot;
import com.exception.EntityException;
import com.repository.RobotRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/robots")
public class RobotController {

    private RobotRepository robotRepository = new RobotRepository();

    @GetMapping(value = "/{idRobot}")
    public Robot get(@PathVariable("idRobot") int idRobot) throws EntityException{
        return robotRepository.findOne(idRobot); }
}
