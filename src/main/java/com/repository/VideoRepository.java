package com.repository;

import com.domain.Video;
import com.exception.EntityException;

import javax.persistence.EntityManager;
import java.util.List;

public class VideoRepository {
    private EntityManager entityManager = EntityManagerUtil.getEntityManager();

    public List<Video> findByUserId(int idUser){
        List<Video> videos =  (List<Video>) entityManager.createQuery("SELECT v FROM Video v where v.idUser = :idUser").setParameter("idUser",idUser).getResultList();
        if(videos.size() > 0){
            return videos;
        }
        return null;
    }

    public Video findOne(String videoId) throws EntityException {
        Video video = entityManager.find(Video.class, videoId);
        if(video == null){
            throw new EntityException();
        }
        return video;
    }

}
