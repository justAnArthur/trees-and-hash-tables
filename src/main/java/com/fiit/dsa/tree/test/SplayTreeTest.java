package com.fiit.dsa.tree.test;

import com.fiit.dsa.tree.AVLTree;
import com.fiit.dsa.tree.SplayTree;
import org.openjdk.jmh.annotations.*;

import java.util.Random;

@State(Scope.Thread)
public class SplayTreeTest {
    @Param({"100"})
    public int size;

    private Random random;

    @Setup(Level.Invocation)
    public void setUp() {
        random = new Random();
        insertRandomSetUp();
        insertSortedSetUp();
        insertToSizeSetUp();
        searchSetUp();
        deleteSetUp();
    }


    SplayTree insertRandomTree;

    public void insertRandomSetUp() {
        insertRandomTree = new SplayTree();
    }

    @Benchmark
    public void insert_random() {
        for (int i = 0; i < size; i++) {
            insertRandomTree.insert(random.nextInt(size));
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


    AVLTree insertToSizeTree;

    public void insertToSizeSetUp() {
        insertToSizeTree = new AVLTree();
        for (int i = 0; i < size; i++) {
            insertToSizeTree.insert(random.nextInt(size));
        }
    }

    @Benchmark
    public void insert_to_size() {
        insertSortedTree.insert(random.nextInt(size + 1));
    }


    SplayTree searchTree;
    Integer[] searchedValues;

    public void searchSetUp() {
        searchTree = new SplayTree();
        searchedValues = new Integer[size];
        for (int i = 0; i < size; i++) {
            int number = random.nextInt(size);
            searchTree.insert(number);
            searchedValues[i] = number;
        }
    }

    @Benchmark
    public void search() {
        for (int i = 0; i < size; i++) {
            deleteTree.search(deletedValues[i]);
        }
    }


    SplayTree deleteTree;
    Integer[] deletedValues;

    public void deleteSetUp() {
        deleteTree = new SplayTree();
        deletedValues = new Integer[size];
        for (int i = 0; i < size; i++) {
            int number = random.nextInt(size);
            deleteTree.insert(number);
            deletedValues[i] = number;
        }
    }

    @Benchmark
    public void delete() {
        for (int i = 0; i < size; i++) {
            deleteTree.delete(deletedValues[i]);
        }
    }
}
