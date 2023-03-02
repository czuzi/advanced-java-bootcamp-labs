package user;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UserDao {

    private EntityManagerFactory entityManagerFactory;

    public UserDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public User saveUser(User user){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public User findUser(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = entityManager.find(User.class, id);
        entityManager.close();
        return user;
    }

    public User findUserWithVideos(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("select distinct u from User u left join fetch u.videos where u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public void saveCommentToUser(Long userId, Long commentId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            Comment comment = em.find(Comment.class, commentId);
            user.addComment(comment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    public User findUserWithComments(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("select distinct u from User u left join fetch u.comments where u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public void saveVideoToUser(Long userId, Long videoId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            Video video = em.find(Video.class, videoId);
            user.addVideo(video);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
