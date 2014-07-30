package ar.com.hexacta.tpl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.model.Comment;

public class BookTest {

    @Test
    public void testBookCommentList() {
        Book testBook = new Book("Test book name");

        List<Comment> commentList = new ArrayList<Comment>();

        Comment testComment1 = new Comment(testBook, "user1", "body1");
        Comment testComment2 = new Comment(testBook, "user2", "body2");
        Comment testComment3 = new Comment(testBook, "user3", "body3");

        commentList.add(testComment1);
        commentList.add(testComment2);
        commentList.add(testComment3);

        testBook.setBookComments(commentList);

        Assert.assertEquals(testBook.getBookComments(), commentList);

    }
}
