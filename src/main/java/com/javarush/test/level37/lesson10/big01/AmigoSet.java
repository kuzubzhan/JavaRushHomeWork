package com.javarush.test.level37.lesson10.big01;

import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
    private static final Object PRESENT = new Object();
    private HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max((int) (collection.size() / .75f) + 1, 16));
        addAll(collection);
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public int size() {
        return 0;
    }
}
