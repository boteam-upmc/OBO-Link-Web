package com.repository;

import com.domain.User;
import com.exception.EntityException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserRepository{
    private EntityManager entityManager = EntityManagerUtil.getEntityManager();
    private EntityTransaction transaction = entityManager.getTransaction();

    public User findOne(int userId, String passe) throws EntityException{
        User user = entityManager.find(User.class, userId);
        System.out.println("user=="+ user);
        if(user == null){
            System.out.println("null true");
            throw new EntityException(userId);
        }
        System.out.println(user.getPasse());
        if(passe.equals(user.getPasse())){
            System.out.println("passe=="+ passe.equals(user.getPasse()));
            return user;
        }
        return null;
    }

    public User findById(int userId) throws EntityException{
        User user = entityManager.find(User.class, userId);
        if(user == null){
            System.out.println("null true");
            throw new EntityException(userId);
        }
        return user;
    }

    public User save(User user){
        if(!transaction.isActive()) {
            transaction.begin();
        }
        try {
            User user1 = entityManager.find(User.class, user.getIdUser());
            if (user1 == null) {
                entityManager.persist(user);
                entityManager.flush();
            }

            entityManager.merge(user);
            entityManager.flush();

            entityManager.getTransaction().commit();
        }catch (Exception e){
            transaction.rollback();
        }

        return user;
    }
}
