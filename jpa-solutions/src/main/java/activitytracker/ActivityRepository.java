package activitytracker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class ActivityRepository {

    private EntityManagerFactory factory;

    public ActivityRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveActivity(Activity activity) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(activity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Activity> listActivities() {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select a from Activity a", Activity.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Activity findActivityById(long id) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.find(Activity.class, id);
        } finally {
            em.close();
        }
    }

    public void deleteActivity(long id) {
        EntityManager em = factory.createEntityManager();
        try {
            Activity activityToDelete = findActivityById(id);
            em.getTransaction().begin();
            em.remove(activityToDelete);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void updateActivity(long id, String description) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Activity activity = em.find(Activity.class, id);
            activity.setDescription(description);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
