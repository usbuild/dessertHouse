package com.lecoding.dao;

import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午4:44
 */
public interface IBaseDAO<T> {
    void save(T t);

    void update(T t);

    void delete(T t);

    T findById(int i);

    List<T> findByCriteria(Criterion... criterions);
}
