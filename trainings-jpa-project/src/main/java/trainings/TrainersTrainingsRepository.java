package trainings;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
            Trainer trainer = em.find(Trainer.class, trainerId);
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

    public Trainer findTrainerWithTrainingsBetween(long trainerId, LocalDate startDate, LocalDate endDate) {
        EntityManager em = factory.createEntityManager();
        try {
            Trainer result = em.createQuery("select t from Trainer t left join fetch t.trainings training where training.trainer.id = :trainerId and not (training.endDate < :start or training.startDate > :end)", Trainer.class)
            .setParameter("trainerId", trainerId).setParameter("start", startDate).setParameter("end", endDate).getSingleResult();
            return result;
        } finally {
            em.close();
        }
    }

    public Trainer deleteTrainer(long id) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            Trainer trainer = em.getReference(Trainer.class, id);
            em.remove(trainer);
            em.getTransaction().commit();
            return trainer;
        } finally {
            em.close();
        }
    }

    public Trainer getTrainerWithTrainings() {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select distinct t from Trainer t left join fetch t.trainings", Trainer.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
