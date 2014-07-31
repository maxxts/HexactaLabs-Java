package ar.com.hexacta.tpl.persistence.repository;

import java.util.List;

import ar.com.hexacta.tpl.model.Book;

public interface BookRepository {

	void save(final Book book);

	void update(final Book book);

	void delete(final Book book);

	void deleteById(Long bookId);

	List<Book> findAll();

	Book findById(Long bookId);

}
