package com.javarush.test.level34.lesson08.bonus01;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        if (!cache.containsKey(key)) {
            V value = clazz.getConstructor(key.getClass()).newInstance(key);
            cache.put(key, value);
        }
        return cache.get(key);
    }

    public boolean put(V obj) {
        try {
            Method m1 = obj.getClass().getDeclaredMethod("getKey");
            m1.setAccessible(true);
            K key = (K) m1.invoke(obj);
            cache.put(key, obj);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public int size() {
        return cache.size();
    }
}
