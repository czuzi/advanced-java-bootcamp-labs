package trainings;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TrainersTrainingsRepositoryTest {

    EntityManagerFactory factory;
    TrainersTrainingsRepository repository;

    @BeforeEach
    void init() {
        factory = Persistence.createEntityManagerFactory("pu");
        repository = new TrainersTrainingsRepository(factory);
    }

    @Test
    void testSaveTrainerAndFindById() {
        Trainer trainer = new Trainer("john", Status.JUNIOR);
        repository.saveTrainer(trainer);
        Trainer foundTrainer = repository.findTrainerById(1L);
        assertEquals("john", foundTrainer.getName());
    }

    @Test
    void testSaveTraining() {
        Trainer trainer = new Trainer("John", Status.JUNIOR);
        repository.saveTrainer(trainer);
        Training training = new Training("Math", LocalDate.of(2023, 1,11), LocalDate.of(2023, 1,14), trainer);
        Training training1 = new Training("History", LocalDate.of(2023, 1,17), LocalDate.of(2023, 1,19), trainer);
        Training training2 = new Training("Programming", LocalDate.of(2023, 1,29), LocalDate.of(2023, 2,3), trainer);
        repository.saveTraining(training, 1L);
        repository.saveTraining(training1, 1L);
        repository.saveTraining(training2, 1L);
        assertEquals(3, repository.getTrainerWithTrainings().getTrainings().size());
    }

    @Test
    void testFindTrainerBetweenGiverPeriod() {
        Trainer trainer = new Trainer("John", Status.JUNIOR);
        repository.saveTrainer(trainer);
    }
}