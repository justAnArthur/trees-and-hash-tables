package com.fiit.dsa.tree.test;

import com.fiit.dsa.tree.AVLTree;
import org.openjdk.jmh.annotations.*;

import java.util.Random;

@State(Scope.Thread)
public class AVLTreeTest {

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


    AVLTree insertRandomTree;

    public void insertRandomSetUp() {
        insertRandomTree = new AVLTree();
    }

    @Benchmark
    public void insert_random() {
        for (int i = 0; i < size; i++) {
            insertRandomTree.insert(random.nextInt(size));
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
            insertToSizeTree.insert(random.nextInt(size));
        }
    }

    @Benchmark
    public void insert_to_size() {
        insertSortedTree.insert(random.nextInt(size + 1));
    }


    AVLTree searchTree;
    Integer[] searchedValues;

    public void searchSetUp() {
        searchTree = new AVLTree();
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
            insertSortedTree.search(searchedValues[i]);
        }
    }


    AVLTree deleteTree;
    Integer[] deletedValues;

    public void deleteSetUp() {
        deleteTree = new AVLTree();
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
