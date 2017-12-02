package com.repository;

import com.domain.UsersRobots;
import com.domain.Video;
import com.exception.EntityException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRobotResopitory {

    private EntityManager entityManager = EntityManagerUtil.getEntityManager();
    private EntityTransaction transaction= entityManager.getTransaction();

    /*
    * chercher tous les associations d'utilisateur et robot.
    * */
    public List<UsersRobots> findAll(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsersRobots> cq = cb.createQuery(UsersRobots.class);
        Root<UsersRobots> rootEntry = cq.from(UsersRobots.class);
        CriteriaQuery<UsersRobots> all = cq.select(rootEntry);
        TypedQuery<UsersRobots> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    /*
    * chercher les associations par utilisateur.
    * */
    public List<UsersRobots> findByUserId(int idUser){
        return  (List<UsersRobots>) entityManager.createQuery("SELECT ur FROM UsersRobots ur where ur.idUser = :idUser").setParameter("idUser",idUser).getResultList();
    }

    /*
    * stoker une association
    * */
    public UsersRobots save(UsersRobots usersRobots){ // beans
        if(!transaction.isActive()) { // etre sur  qu on est connecte
            transaction.begin();
        }

        try {
            //TODO optimiser : find(id, id) by Composite-id class
            //UsersRobots usersRobots1 = entityManager.find(UsersRobots.class, usersRobots);

            boolean exist = findExist(usersRobots);
            if(exist == false){
                entityManager.persist(usersRobots);
                entityManager.flush();
                entityManager.getTransaction().commit();

            }

        }catch (Exception e){
            transaction.rollback(); // atomic fait tout ou rien
        }
        return usersRobots;
    }

    /*
    * supprimer une association
    * */
    public List<UsersRobots> deleteAssociation(UsersRobots usersRobots) throws EntityException {
        if(!transaction.isActive()) {
            transaction.begin();
        }
        boolean exist = findExist(usersRobots);
        if(exist == true){
            entityManager.remove(entityManager.contains(usersRobots)? usersRobots: entityManager.merge(usersRobots));
            entityManager.getTransaction().commit();
        }
        return findByUserId(usersRobots.getIdUser());
    }

    /*
    * Avant que stoker et supprimer une association, on filtre qu'il existe deja ou pas
    * */
    private boolean findExist(UsersRobots usersRobots){
        List<UsersRobots> result =  (List<UsersRobots>) entityManager.createQuery("SELECT ur FROM UsersRobots ur where ur.idUser = :idUser and ur.idRobot = :idRobot")
                .setParameter("idUser",usersRobots.getIdUser())
                .setParameter("idRobot",usersRobots.getIdRobot())
                .getResultList();
        if(result.size() > 0){
            return true;
        }
        return false;
    }
}
