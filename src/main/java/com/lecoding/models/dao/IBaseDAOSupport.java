package com.lecoding.models.dao;

/**
 * Created with IntelliJ IDEA.
 * User: Usbuild
 * DateTime: 13-2-3-下午5:44
 */
public interface IBaseDAOSupport<T> {
    public void save(T t);

    public T findById(int id);
}
