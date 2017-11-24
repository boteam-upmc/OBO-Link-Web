package com.repository;

import com.domain.Robot;
import com.exception.EntityException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RobotRepository {

    private EntityManager entityManager = EntityManagerUtil.getEntityManager();
    private EntityTransaction transaction = entityManager.getTransaction();

    public Robot findOne(int robotId) throws EntityException{
        Robot robot = entityManager.find(Robot.class, robotId);
        if(robot == null){
            throw new EntityException(robotId);
        }
        return robot;
    }

    public Robot save(Robot robot){
        if(!transaction.isActive()) {
            transaction.begin();
        }
        try {
            Robot robot1 = entityManager.find(Robot.class, robot.getIdRobot());
            if (robot1 == null) {
                entityManager.persist(robot);
                entityManager.flush();
            }

            entityManager.merge(robot);
            entityManager.flush();

            entityManager.getTransaction().commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return robot;
    }
}
