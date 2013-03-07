package com.lecoding.dao.impl;

import com.lecoding.dao.IBaseDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Customer: Usbuild
 * DateTime: 13-1-27-上午10:31
 */
public abstract class BaseDAOSupport<T extends Serializable> implements IBaseDAO<T> {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public BaseDAOSupport() {
//        Try to get T.class
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(T t) {
        getSession().save(t);
    }

    public void delete(T t) {
        getSession().delete(t);
    }

    public void update(T t) {
        getSession().update(t);
    }

    @SuppressWarnings("unchecked")
    public T findById(int id) {
        return (T) getSession().get(getPersistentClass(), id);
    }


    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByCriteria(Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria.list();
    }
}
