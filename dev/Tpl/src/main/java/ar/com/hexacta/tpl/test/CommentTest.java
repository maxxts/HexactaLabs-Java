package ar.com.hexacta.tpl.test;

import org.junit.Assert;
import org.junit.Test;

import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.persistence.dao.CommentDAO;

public class CommentTest {

    @Test
    public void testCommentCreation() {
        Comment comment = new Comment();

        Assert.assertNotNull(comment);
    }

    @Test
    public void testParamCommentCreation() {

        String testUser = "test@mail.com";
        String testBody = "Test comment body";
        String testBook = "Test Book";

        Comment comment = new Comment(testBook, testUser, testBody);

        Assert.assertTrue(comment.getUser() == testUser);
        Assert.assertTrue(comment.getBody() == testBody);
        Assert.assertTrue(comment.getBook() == testBook);
    }

    @Test
    public void testCommentDAO() {
        Comment comment = new Comment("Libro", "Usuario", "Cuerpo");

        Comment comment2 = new Comment();

        CommentDAO commentDao = new CommentDAO();

        commentDao.save(comment);

        comment2 = commentDao.findById(1);

        Assert.assertTrue(comment.getUser() == comment2.getUser());

    }
}
