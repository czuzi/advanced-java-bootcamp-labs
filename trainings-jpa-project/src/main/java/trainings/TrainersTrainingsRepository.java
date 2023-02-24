package trainings;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrainersTrainingsRepository {

    EntityManagerFactory factory;

    public Trainer saveTrainer(Trainer trainer) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(trainer);
            em.getTransaction().commit();
            return trainer;
        } finally {
            em.close();
        }
    }

    public Training saveTraining(Training training, long trainerId) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Trainer trainer = findTrainerById(trainerId);
            trainer.addTraining(training);
            em.getTransaction().commit();
            return training;
        } finally {
            em.close();
        }
    }

    public Trainer findTrainerById(long id) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.find(Trainer.class, id);
        } finally {
            em.close();
        }
    }
}
