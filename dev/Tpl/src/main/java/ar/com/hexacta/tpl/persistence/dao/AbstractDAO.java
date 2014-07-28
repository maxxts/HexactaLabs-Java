package ar.com.hexacta.tpl.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class AbstractDAO<T> extends HibernateDaoSupport {

    private static final String UNCHECKED = "unchecked";

    private final transient Class<T> persistentClass;

    @SuppressWarnings(UNCHECKED)
    protected AbstractDAO() {
        super();
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.persistentClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    protected Class<T> getPersistentClass() {
        return this.persistentClass;
    }

    public void save(final T entity) {
        this.saveOrUpdate(entity);
    }

    public void delete(final T entity) {
    	this.getSession().delete(entity);
    }

    public void update(final T entity) {
    	//TODO: validate existence
    	//this.getHibernateTemplate().getSessionFactory().getCurrentSession().merge(entity);
    	T merged = (T)this.getSession().merge(entity);
    	this.getSession().update(merged);
    }

    public void saveOrUpdate(final T entity) {
    	this.getSession().saveOrUpdate(entity);
    }

    public T findById(final Serializable id) {
        return (T)this.getSession().get(this.getPersistentClass(), id);
    }

    @SuppressWarnings(UNCHECKED)
    public List<T> findAll() {
        return this.getHibernateTemplate().find("from " + this.getPersistentClass().getName() + " o");
    }

    public void deleteById(final Serializable id) {
        T obj = this.findById(id);
        this.getSession().delete(obj);
    }

    public void flushAndClear() {
    	this.getSession().flush();
    	this.getSession().clear();
    }

    protected Criteria createCriteria() {
        return this.getSession().createCriteria(this.getPersistentClass());
    }

}
