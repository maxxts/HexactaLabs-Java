package ar.com.hexacta.tpl.persistence.repository;

import java.util.List;

import ar.com.hexacta.tpl.model.Comment;

public interface CommentRepository {

    void save(final Comment comment);

    // void update(final Comment book);

    void delete(final Comment comment);

    void deleteById(Long commentId);

    List<Comment> findAll();

    Comment findById(Long commentId);

    List<Comment> findByBookId(Long bookId);

    // List<Comment> findByBook(Book book);

}
