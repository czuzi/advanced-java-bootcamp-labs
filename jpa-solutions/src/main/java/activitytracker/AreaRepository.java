package activitytracker;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AreaRepository {

    EntityManagerFactory factory;

    public AreaRepository(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Area saveArea(Area area) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(area);
            em.getTransaction().commit();
            return area;
        } finally {
            em.close();
        }
    }

    public Area findAreaByIdWithActivities(long id) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select distinct area from Area area left join fetch area.activities where area.id = :id", Area.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public Area findAreaByIdWithCities(long id) {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("select distinct area from Area area left join fetch area.cityMap where area.id = :id", Area.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
