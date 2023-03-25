package com.fiit.dsa.hashTable.test;

import com.fiit.dsa.hashTable.OpenHashTable;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.UUID;

@State(Scope.Thread)
public class OpenHashTableTest {
    @Param({"100"})
    public int size;

    private Random random;

    @Setup(Level.Invocation)
    public void setUp() {
        random = new Random();
        putSetUp();
        getSetUp();
        removeSetUp();
    }

    OpenHashTable<String, Integer> putOpenHT;

    public void putSetUp() {
        putOpenHT = new OpenHashTable<>(size);
    }

    @Benchmark
    public void put() {
        for (int i = 0; i < size; i++) {
            putOpenHT.put(UUID.randomUUID().toString(), random.nextInt(size));
        }
    }


    OpenHashTable<String, Integer> getOpenHT;
    String[] gottenValues;

    public void getSetUp() {
        getOpenHT = new OpenHashTable<>(size);
        gottenValues = new String[size];
        for (int i = 0; i < size; i++) {
            String key = UUID.randomUUID().toString();
            getOpenHT.put(key, random.nextInt(size));
            gottenValues[i] = key;
        }
    }

    @Benchmark
    public void get() {
        for (int i = 0; i < size; i++) {
            getOpenHT.get(gottenValues[i]);
        }
    }


    OpenHashTable<String, Integer> removeOpenHT;
    String[] toRemoveValues;

    public void removeSetUp() {
        removeOpenHT = new OpenHashTable<>(size);
        toRemoveValues = new String[size];
        for (int i = 0; i < size; i++) {
            String key = UUID.randomUUID().toString();
            removeOpenHT.put(key, random.nextInt(size));
            toRemoveValues[i] = key;
        }
    }

    @Benchmark
    public void remove() {
        for (int i = 0; i < size; i++) {
            removeOpenHT.get(toRemoveValues[i]);
        }
    }
}
