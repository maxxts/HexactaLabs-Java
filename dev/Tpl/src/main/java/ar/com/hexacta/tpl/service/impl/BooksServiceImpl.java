package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.persistence.repository.BookRepository;
import ar.com.hexacta.tpl.service.IBooksService;

public class BooksServiceImpl implements IBooksService {

    private BookRepository booksRepository;

    @Override
    @Transactional
    public void createBook(final Book book) {
        // TODO: Add validation logic
    	booksRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(final Book book) {
        booksRepository.delete(book);
    }
    
    public void deleteBookById(String bookId) {
    	booksRepository.deleteById(bookId);
	}

    @Override
    @Transactional
    public void updateBook(final Book book) {
        // TODO: Add validation logic
    	booksRepository.update(book);
    }
    
    @Override
    public List<Book> findAllBooks() {
        return booksRepository.findAll();
    }
    
    @Override
    @Transactional
	public Book findBook(String bookId) {
		return booksRepository.findById(bookId);
	}

    public BookRepository getBookRepository() {
        return booksRepository;
    }

    public void setBookRepository(final BookRepository bookRepository) {
        booksRepository = bookRepository;
    }

}
