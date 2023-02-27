package activitytracker;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class AreaRepositoryTest {

    EntityManagerFactory factory;
    AreaRepository areaRepository;
    ActivityRepository activityRepository;

    @BeforeEach
    void init() {
        factory = Persistence.createEntityManagerFactory("test-pu");
        areaRepository = new AreaRepository(factory);
        activityRepository = new ActivityRepository(factory);
    }

    @Test
    void testFindAreaByIdWithActivities() {
        Area area = new Area("New York");
        Activity activity = new Activity(LocalDateTime.of(2022, 4, 11, 6, 0), "morning running", ActivityType.RUNNING);
        Activity otherActivity = new Activity(LocalDateTime.of(2011, 5, 12, 8, 0), "late night basketball", ActivityType.BASKETBALL);
        activityRepository.saveActivity(activity);
        activityRepository.saveActivity(otherActivity);
        area.addActivity(activity);
        area.addActivity(otherActivity);
        areaRepository.saveArea(area);
        System.out.println(area.getActivities());
    }
}