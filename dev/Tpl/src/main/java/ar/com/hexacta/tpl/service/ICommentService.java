package ar.com.hexacta.tpl.service;

import java.util.List;

import ar.com.hexacta.tpl.model.Comment;

public interface ICommentService {

    List<Comment> findAllComments();

    /*
     * List<Comment> findCommentsByBookId(String bookId);
     * 
     * // List<Comment> findCommentsByBook(Book book);
     * 
     * Comment findComment(String commentId);
     * 
     * void createComment(final Comment comment);
     * 
     * // void updateComment(final Comment comment);
     * 
     * void deleteComment(final Comment comment);
     * 
     * void deleteCommentById(String commentId);
     */
}
