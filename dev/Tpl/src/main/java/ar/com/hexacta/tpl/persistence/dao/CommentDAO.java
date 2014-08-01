package ar.com.hexacta.tpl.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.persistence.repository.CommentRepository;

@Repository
public class CommentDAO extends AbstractDAO<Comment> implements
		CommentRepository {

	@Override
	public Comment findById(final String commentId) {
		Criteria criteria = this.getSession().createCriteria(Comment.class);
		criteria.add(Restrictions.like("id", commentId));
		return (Comment) criteria.uniqueResult();
	}

	@Override
	public void deleteById(final String commentId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Comment> findByBookId(final Long bookId) {
		throw new UnsupportedOperationException();
	}

}
