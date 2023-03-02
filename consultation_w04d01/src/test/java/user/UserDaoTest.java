package user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao userDao;

    VideoDao videoDao;

    TagDao tagDao;

    CommentDao commentDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        userDao = new UserDao(entityManagerFactory);
        videoDao = new VideoDao(entityManagerFactory);
        tagDao = new TagDao(entityManagerFactory);
        commentDao = new CommentDao(entityManagerFactory);
    }

    @Test
    void testSaveUser() {
        User user = new User("John", LocalDate.of(2021,11,11));

        userDao.saveUser(user);

        User other = userDao.findUser(user.getId());

        assertEquals("John", other.getName());
        assertEquals(LocalDate.of(2021, 11, 11), other.getRegistrationDate());
    }

    @Test
    void testFindUserWithVideos() {
        User user = new User("John", LocalDate.of(2021,11,11));
        userDao.saveUser(user);
        Video video = new Video("My first video", LocalTime.of(1, 23, 31));
        videoDao.saveVideo(video);

        userDao.saveVideoToUser(user.getId(), video.getId());

        User other = userDao.findUserWithVideos(user.getId());

        assertEquals("John", other.getName());
        assertEquals(LocalDate.of(2021, 11, 11), other.getRegistrationDate());
        assertEquals(1, other.getVideos().size());
    }

    @Test
    void testFindUserWithComments() {
        User user = new User("John", LocalDate.of(2021,11,11));
        userDao.saveUser(user);
        Comment comment = new Comment("This is my first video and I like it very much");
        commentDao.saveComment(comment);
        userDao.saveCommentToUser(user.getId(), comment.getId());

        User other = userDao.findUserWithComments(user.getId());

        assertEquals("John", other.getName());
        assertEquals(LocalDate.of(2021, 11, 11), other.getRegistrationDate());
        assertEquals(1, other.getComments().size());
    }
}