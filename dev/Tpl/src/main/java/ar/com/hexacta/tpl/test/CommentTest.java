package ar.com.hexacta.tpl.test;

import org.junit.Assert;
import org.junit.Test;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.Comment;

public class CommentTest {

    @Test
    public void testCommentCreation() {
        Comment comment = new Comment();

        Assert.assertNotNull(comment);
    }

    @Test
    public void testParamCommentCreation() {

        Book book = new Book("Test Book");

        String testUser = "test@mail.com";
        String testBody = "Test comment body";
        Book testBook = book;

        Comment comment = new Comment(testBook, testUser, testBody);

        Assert.assertTrue(comment.getUser() == testUser);
        Assert.assertTrue(comment.getBody() == testBody);
        Assert.assertTrue(comment.getBook() == testBook);
    }

    @Test
    public void testCommentDAO() {
    }
}
