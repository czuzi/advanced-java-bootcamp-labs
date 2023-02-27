package activitytracker;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void testSaveThenFind() {
        Area area = new Area("Kiskunság");
        area.addCityToMap(new City("Kecskemét", 110_687));
        area.addCityToMap(new City("Soltvadkert", 7342));
        areaRepository.saveArea(area);
        Area expected = areaRepository.findAreaByIdWithCities(area.getId());

        assertEquals(2, expected.getCityMap().size());
        assertEquals(Set.of("Kecskemét", "Soltvadkert"), expected.getCityMap().keySet());
    }
}