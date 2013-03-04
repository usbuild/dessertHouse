package com.lecoding.components;

import org.hibernate.HibernateException;

/**
 * Created with IntelliJ IDEA.
 * User: usbuild
 * DateTime: 13-3-4 下午2:36
 */
public class DessertException extends HibernateException {
    public DessertException(String message) {
        super(message);
    }
}
