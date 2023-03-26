package com.fiit.dsa.tree.test;

import com.fiit.dsa.tree.SplayTree;
import org.openjdk.jmh.annotations.*;

import java.util.Random;

@State(Scope.Thread)
public class SplayTreeTest {

    @Param({"100"})
    public int size;

    @Setup(Level.Invocation)
    public void setUp() {
        setUpRandom();

        insertRandomSetUp();
        insertSortedSetUp();
        insertToSizeSetUp();
        searchSetUp();
        deleteSetUp();
    }

    private int[] randomValues;

    private void setUpRandom() {
        Random random = new Random();
        randomValues = new int[size + 1];
        for (int i = 0; i < size; i++)
            randomValues[i] = random.nextInt(size);
    }


    SplayTree insertRandomTree;

    public void insertRandomSetUp() {
        insertRandomTree = new SplayTree();
    }

    @Benchmark
    public void insert_random() {
        for (int i = 0; i <= size; i++) {
            insertRandomTree.insert(randomValues[i]);
        }
    }


    SplayTree insertSortedTree;

    public void insertSortedSetUp() {
        insertSortedTree = new SplayTree();
    }

    @Benchmark
    public void insert_sorted() {
        for (int i = 0; i < size; i++) {
            insertSortedTree.insert(i);
        }
    }


    SplayTree insertToSizeTree;

    public void insertToSizeSetUp() {
        insertToSizeTree = new SplayTree();
        for (int i = 0; i < size; i++) {
            insertToSizeTree.insert(randomValues[i]);
        }
    }

    @Benchmark
    public void insert_to_size() {
        insertSortedTree.insert(randomValues[size]);
    }


    SplayTree searchTree;

    public void searchSetUp() {
        searchTree = new SplayTree();
        for (int i = 0; i < size; i++) {
            searchTree.insert(randomValues[i]);
        }
    }

    @Benchmark
    public void search() {
        for (int i = 0; i < size; i++) {
            insertSortedTree.search(randomValues[i]);
        }
    }


    SplayTree deleteTree;

    public void deleteSetUp() {
        deleteTree = new SplayTree();
        for (int i = 0; i < size; i++) {
            deleteTree.insert(randomValues[i]);
        }
    }

    @Benchmark
    public void delete() {
        for (int i = 0; i < size; i++) {
            deleteTree.delete(randomValues[i]);
        }
    }
}
