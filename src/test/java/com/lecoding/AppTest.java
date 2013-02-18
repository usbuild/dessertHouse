package com.lecoding;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;

/**
 * Unit test for simple App.
 */
public class AppTest {

    class A <T>{
        void hello() {
            System.out.println(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        }
    }

    class B extends A<Object> {

    }

    @Test
    public void testApp() {
        A a = new B();
        a.hello();
    }
}
