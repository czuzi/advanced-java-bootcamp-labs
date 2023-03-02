package user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class CommentDaoTest {

    CommentDao commentDao;

    @BeforeEach
    void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        commentDao = new CommentDao(entityManagerFactory);
    }

    @Test
    void testSaveComment() {
        Comment comment = new Comment("This is my first video and I like it very much");
        commentDao.saveComment(comment);

        Comment other = commentDao.findComment(comment.getId());

        assertEquals("This is my first video and I like it very much", other.getText());
    }
}