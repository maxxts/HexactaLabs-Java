package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.Book;
import ar.com.hexacta.tpl.persistence.repository.BookRepository;

@Repository
public class BookDAO extends AbstractDAO<Book> implements BookRepository {

	@SuppressWarnings("unchecked")
	public List<Book> findAll(final String publisher) {
		Criteria criteria = this.getSession().createCriteria(Book.class);
		criteria.add(Restrictions.like("publisher", publisher));
		return criteria.list();
	}

	@Override
	public Book findById(final Long bookId) {
		Criteria criteria = this.getSession().createCriteria(Book.class);
<<<<<<< HEAD
        criteria.add(Restrictions.like("id", new Long(bookId))); // trae country null
        Book b = (Book) criteria.uniqueResult();
        return b;
=======
		criteria.add(Restrictions.like("id", bookId));
		return (Book) criteria.uniqueResult();
>>>>>>> 85473341f72b21ddd1268698445fbd25d27c10d2
	}

	@Override
	public void deleteById(final Long bookId) {
		super.deleteById(bookId);
	}

}
