package activitytracker;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void testUpdateTime() {
        Activity activity = new Activity(LocalDateTime.of(2022, 4, 11, 6, 0), "morning running", ActivityType.RUNNING);
        repository.saveActivity(activity);
        long id = activity.getId();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            throw new IllegalStateException();
        }
        repository.updateActivity(id, "afternoon running");
        System.out.println(repository.findActivityById(id));
    }

    @Test
    void testFindByIdWithLabels() {
        Activity activity = new Activity(LocalDateTime.of(2022, 4, 11, 6, 0), "morning running", ActivityType.RUNNING);
        activity.addLabel("high intensity");
        activity.addLabel("long distance");
        repository.saveActivity(activity);
        Activity result = repository.findActivityByIdWithLabels(activity.getId());
        System.out.println(result.getLabels());
    }

    @Test
    void testFindByIdWithTrackPoints() {
        Activity activity = new Activity(LocalDateTime.of(2022, 4, 11, 6, 0), "morning running", ActivityType.RUNNING);
        activity.addTrackPoint(new TrackPoint(LocalDateTime.of(1999, 12,11,19,32), 12.123,23.21));
        repository.saveActivity(activity);
        Activity result = repository.findActivityByIdWithTrackPoints(activity.getId());
        System.out.println(result.getTrackPoints());
    }

    @Test
    void testFindTrackPointCoordinatesByDate() {
        Activity wrongActivity = new Activity(LocalDateTime.of(2017, 4, 11, 16, 0), "evening running", ActivityType.RUNNING);
        wrongActivity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 6, 3), 1, 1));
        wrongActivity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 4, 5, 7, 4), 2, 2));
        wrongActivity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 3, 4, 8, 5), 3, 3));
        wrongActivity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 3, 4, 9, 6), 4, 4));
        repository.saveActivity(wrongActivity);
        Activity rightActivity = new Activity(LocalDateTime.of(2022, 4, 11, 6, 0), "morning running", ActivityType.RUNNING);
        rightActivity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 6, 3), 47.497912, 19.040235));
        rightActivity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 4, 5, 7, 4), 33.88223, 151.33140));
        rightActivity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 3, 4, 8, 5), 48.87376, 2.25120));
        rightActivity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 3, 5, 9, 6), 23.87376, 4.25120));
        repository.saveActivity(rightActivity);

        List<Coordinate> expected = repository.findTrackPointCoordinatesByDate(LocalDateTime.of(2018, 1, 1, 0, 0), 1, 2);

        assertEquals(2, expected.size());
        assertEquals(48.87376, expected.get(0).getLatitude());
        assertEquals(4.2512, expected.get(1).getLongitude());
    }

    @Test
    void testFindTrackPointCountByActivity() {
        Activity activity = new Activity(LocalDateTime.of(2022, 4, 11, 6, 0), "morning running", ActivityType.RUNNING);
        activity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 6, 3), 47.497912, 19.040235));
        activity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 4, 5, 7, 4), -33.88223, 151.33140));
        activity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 3, 4, 8, 5), 48.87376, 2.25120));
        repository.saveActivity(activity);

        Activity secondActivity = new Activity(LocalDateTime.of(2022, 3, 14, 17, 30), "evening running", ActivityType.RUNNING);
        secondActivity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 6, 3), 47.497912, 19.040235));
        secondActivity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 4, 5, 7, 4), -33.88223, 151.33140));
        repository.saveActivity(secondActivity);

        Activity thirdActivity = new Activity(LocalDateTime.of(2022, 4, 9, 10, 15), "hiking near Budapest", ActivityType.HIKING);
        thirdActivity.addTrackPoint(new TrackPoint(LocalDateTime.of(2021, 2, 3, 6, 3), 47.497912, 19.040235));
        repository.saveActivity(thirdActivity);

        List<Object[]> expected = repository.findTrackPointCountByActivity();

        Object[] firstActivityData = new Object[]{"morning running", 3};
        Object[] secondActivityData = new Object[]{"evening running", 2};
        Object[] thirdActivityData = new Object[]{"hiking near Budapest", 1};

        assertEquals(3, expected.size());
        assertArrayEquals(firstActivityData, expected.get(2));
        assertArrayEquals(secondActivityData, expected.get(0));
        assertArrayEquals(thirdActivityData, expected.get(1));
    }
}