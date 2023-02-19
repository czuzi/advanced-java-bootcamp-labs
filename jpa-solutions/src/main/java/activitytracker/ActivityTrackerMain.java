package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ActivityTrackerMain {

    public static void main(String[] args) {
        Activity firstActivity = new Activity(LocalDateTime.of(2022, 4, 11, 6, 0), "morning running", ActivityType.RUNNING);
        Activity secondActivity = new Activity(LocalDateTime.of(2022, 4, 9, 10, 15), "hiking near Budapest", ActivityType.HIKING);
        Activity thirdActivity = new Activity(LocalDateTime.of(2022, 3, 14, 17, 30), "evening running", ActivityType.RUNNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();

        try {
            saveActivity(firstActivity, em);
            saveActivity(secondActivity, em);
            saveActivity(thirdActivity, em);
        } finally {
            em.close();
        }
    }

    private static void saveActivity(Activity firstActivity, EntityManager em) {
        em.getTransaction().begin();
        em.persist(firstActivity);
        em.getTransaction().commit();
    }
}
