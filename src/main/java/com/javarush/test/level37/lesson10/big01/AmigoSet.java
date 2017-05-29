package com.javarush.test.level37.lesson10.big01;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
