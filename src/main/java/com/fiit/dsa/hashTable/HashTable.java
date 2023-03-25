package com.fiit.dsa.hashTable;

import java.util.Set;

public interface HashTable<K, V> {
    int getIndex(K key);

    V get(K key);

    void put(K key, V value);

    void remove(K key);

    boolean isEmpty();

    int getSize ();

    Set<K> keySet();

    boolean containsKey(K key);
}
