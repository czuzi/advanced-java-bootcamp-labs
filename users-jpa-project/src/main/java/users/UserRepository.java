package users;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserRepository {

	private EntityManagerFactory factory;

	public UserRepository(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public User saveUser(User user) {
		EntityManager em = factory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			return user;
		} finally {
			em.close();
		}
	}

	public User findUserById(Long id) {
		EntityManager em = factory.createEntityManager();
		try {
			return em.find(User.class, id);
		} finally {
			em.close();
		}
	}
	public User findUserByUserName(String username) {
		EntityManager em = factory.createEntityManager();
		try {
			return em.createQuery("select u from User u where u.name = ?1", User.class)
					.setParameter(1, username)
					.getSingleResult();
		} finally {
			em.close();
		}
	}

	public void updateUserPassword(String username, String newPassword) {
		EntityManager em = factory.createEntityManager();
		try {
			em.getTransaction().begin();
			User user = em.getReference(User.class, username);
			user.setPassword(newPassword);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
}
