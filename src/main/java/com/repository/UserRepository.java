package com.repository;

import com.domain.User;
import com.exception.EntityException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserRepository{
    private EntityManager entityManager = EntityManagerUtil.getEntityManager();
    private EntityTransaction transaction = entityManager.getTransaction();

    public boolean findOne(int userId, String passe) throws EntityException{
        User user = entityManager.find(User.class, userId);
        if(user == null){
            System.out.println("null true");
            throw new EntityException(userId);
        }else if(passe.equals(user.getPasse())){
            return true;
        }
        return false;
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
