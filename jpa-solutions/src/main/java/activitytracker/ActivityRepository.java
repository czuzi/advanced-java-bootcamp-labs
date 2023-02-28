package activitytracker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class ActivityRepository {

    private EntityManagerFactory factory;

    public ActivityRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Activity saveActivity(Activity activity) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(activity);
            em.getTransaction().commit();
            return activity;
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

    public Activity findActivityByIdWithLabels(long id) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select distinct a from Activity a left join fetch a.labels where a.id = :id", Activity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public Activity findActivityByIdWithTrackPoints(long id) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select distinct a from Activity a left join fetch a.trackPoints where a.id = :id", Activity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public Activity findActivityByIdWithAreas(Long id) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select distinct a from Activity a left join fetch a.areas where a.id = :id", Activity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Coordinate> findTrackPointCoordinatesByDate(LocalDateTime afterThis, int start, int max) {
        EntityManager manager = factory.createEntityManager();
        try {
            List<Coordinate> coordinates = manager
                    .createNamedQuery("findTrackPointCoordinatesByDate")
                    .setParameter("time", afterThis)
                    .setFirstResult(start)
                    .setMaxResults(max)
                    .getResultList();
            return coordinates;
        } finally {
            manager.close();
        }
    }

    public List<Object[]> findTrackPointCountByActivity() {
        EntityManager manager = factory.createEntityManager();
        try {
            List<Object[]> trackPointsCount = manager
                    .createQuery("select description, size(a.trackPoints) from Activity a order by a.description",
                            Object[].class)
                    .getResultList();
            return trackPointsCount;
        } finally {
            manager.close();
        }
    }
}
