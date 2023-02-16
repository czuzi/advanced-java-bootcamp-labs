package users;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {

	EntityManagerFactory factory;
	UserRepository repository;

	@BeforeEach
	void init() {
		factory = Persistence.createEntityManagerFactory("pu");
		repository = new UserRepository(factory);
	}

	@AfterEach
	void afterEachTest(){
		factory.close();
	}

	@Test
	void testSaveUser() {
		User user = repository.saveUser(new User("bla", "qwerty", Role.USER));
		assertEquals("bla", user.getName());
	}
}