package com.fiit.dsa.hashTable.test;

import com.fiit.dsa.hashTable.ClosedHashTable;
import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@State(Scope.Thread)
public class ClosedHashTableTest {
    @Param({"100"})
    public int size;

    @Setup(Level.Invocation)
    public void setUp() {
        randomSetUp();
        putSetUp();
        getSetUp();
        removeSetUp();
    }

    private record KeyValuePair<K, V>(K key, V value) {
    }

    Map<Integer, KeyValuePair<String, Integer>> randomValues;

    private void randomSetUp() {
        Random random = new Random();
        randomValues = new HashMap<>();
        for (int i = 0; i <= size; i++) {
            randomValues.put(i, new KeyValuePair<>(UUID.randomUUID().toString(), random.nextInt(size)));
        }
    }


    ClosedHashTable<String, Integer> putOpenHT;

    public void putSetUp() {
        putOpenHT = new ClosedHashTable<>(size);
    }

    @Benchmark
    public void put() {
        for (int i = 0; i < size; i++) {
            putOpenHT.put(randomValues.get(i).key, randomValues.get(i).value);
        }
    }


    ClosedHashTable<String, Integer> getOpenHT;

    public void getSetUp() {
        getOpenHT = new ClosedHashTable<>(size);
        for (int i = 0; i < size; i++) {
            getOpenHT.put(randomValues.get(i).key, randomValues.get(i).value);
        }
    }

    @Benchmark
    public void get() {
        for (int i = 0; i < size; i++) {
            getOpenHT.get(randomValues.get(i).key);
        }
    }


    ClosedHashTable<String, Integer> removeOpenHT;

    public void removeSetUp() {
        removeOpenHT = new ClosedHashTable<>(size);
        for (int i = 0; i < size; i++) {
            removeOpenHT.put(randomValues.get(i).key, randomValues.get(i).value);
        }
    }

    @Benchmark
    public void remove() {
        for (int i = 0; i < size; i++) {
            removeOpenHT.get(randomValues.get(i).key);
        }
    }
}
