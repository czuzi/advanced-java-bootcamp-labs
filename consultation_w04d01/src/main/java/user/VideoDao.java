package user;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class VideoDao {

    private EntityManagerFactory entityManagerFactory;

    public VideoDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Video saveVideo(Video video) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(video);
        em.getTransaction().commit();
        em.close();
        return video;
    }

    public Video findVideo(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Video video = entityManager.find(Video.class, id);
        entityManager.close();
        return video;
    }

    public void saveTagToVideo(Long videoId, Long tagId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Video video = em.find(Video.class, videoId);
            Tag tag = em.find(Tag.class, tagId);
            video.addTags(tag);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Video findVideoWithTags(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("select distinct v from Video v left join fetch v.tags where v.id = :id", Video.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
