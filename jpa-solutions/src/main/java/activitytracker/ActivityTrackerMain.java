package activitytracker;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class ActivityTrackerMain {

    public static void main(String[] args) {

        ActivityTrackerMain main = new ActivityTrackerMain();

        Activity firstActivity = new Activity(LocalDateTime.of(2022, 4, 11, 6, 0), "morning running", ActivityType.RUNNING);
        Activity secondActivity = new Activity(LocalDateTime.of(2022, 4, 9, 10, 15), "hiking near Budapest", ActivityType.HIKING);
        Activity thirdActivity = new Activity(LocalDateTime.of(2022, 3, 14, 17, 30), "evening running", ActivityType.RUNNING);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();

        try {
            main.saveActivity(firstActivity, em);
            main.saveActivity(secondActivity, em);
            main.saveActivity(thirdActivity, em);

            List<Activity> activities = main.listActivities(em);
            System.out.println(activities);

            Activity activity = main.findActivityById(1L, em);
            System.out.println(activity);

            em.getTransaction().begin();
            em.remove(activity);
            em.getTransaction().commit();

            System.out.println(main.listActivities(em));
        } finally {
            em.close();
        }
    }

    private void saveActivity(Activity firstActivity, EntityManager em) {
        em.getTransaction().begin();
        em.persist(firstActivity);
        em.getTransaction().commit();
    }

    private List<Activity> listActivities(EntityManager em) {
        return em.createQuery("select a from Activity a", Activity.class).getResultList();
    }

    private Activity findActivityById(Long id, EntityManager em) {
        return em.find(Activity.class, id);
    }
}
