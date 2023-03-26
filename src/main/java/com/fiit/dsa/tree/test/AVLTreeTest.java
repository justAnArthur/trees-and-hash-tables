package com.fiit.dsa.tree.test;

import com.fiit.dsa.tree.AVLTree;
import org.openjdk.jmh.annotations.*;

import java.util.Random;

@State(Scope.Thread)
public class AVLTreeTest {

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


    AVLTree insertRandomTree;

    public void insertRandomSetUp() {
        insertRandomTree = new AVLTree();
    }

    @Benchmark
    public void insert_random() {
        for (int i = 0; i <= size; i++) {
            insertRandomTree.insert(randomValues[i]);
        }
    }


    AVLTree insertSortedTree;

    public void insertSortedSetUp() {
        insertSortedTree = new AVLTree();
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
            insertToSizeTree.insert(randomValues[i]);
        }
    }

    @Benchmark
    public void insert_to_size() {
        insertSortedTree.insert(randomValues[size]);
    }


    AVLTree searchTree;

    public void searchSetUp() {
        searchTree = new AVLTree();
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


    AVLTree deleteTree;

    public void deleteSetUp() {
        deleteTree = new AVLTree();
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
