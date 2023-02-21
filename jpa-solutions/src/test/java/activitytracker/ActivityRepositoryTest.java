package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.*;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ActivityRepositoryTest {

    EntityManagerFactory factory;
    ActivityRepository repository;

    @BeforeEach
    void init() {
        factory = Persistence.createEntityManagerFactory("test-pu");
        repository = new ActivityRepository(factory);
    }

    @Test
    void testSaveActivity() {
        Activity activity = new Activity(LocalDateTime.of(2022, 4, 11, 6, 0), "morning running", ActivityType.RUNNING);
        repository.saveActivity(activity);

        Long id = activity.getId();

        Activity result = repository.findActivityById(id);

        assertEquals("morning running", result.getDescription());
        assertEquals(ActivityType.RUNNING, result.getType());
    }
}