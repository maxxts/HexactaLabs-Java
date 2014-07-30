package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	public Book findById(String bookId) {
		Criteria criteria = this.getSession().createCriteria(Book.class);
        criteria.add(Restrictions.like("id", new Long(bookId)));
        return (Book) criteria.uniqueResult();
	}

	@Override
	public void deleteById(String bookId) {
		super.deleteById(new Long(bookId));
	}

}
