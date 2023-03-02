package user;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CommentDao {

    private EntityManagerFactory entityManagerFactory;

    public CommentDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Comment saveComment(Comment comment){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(comment);
        em.getTransaction().commit();
        em.close();
        return comment;
    }

    public Comment findComment(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Comment comment = entityManager.find(Comment.class, id);
        entityManager.close();
        return comment;
    }
}
