package com.fiit.dsa.hashTable;

import java.util.HashSet;
import java.util.Set;

public class OpenHashTable<K, V> implements HashTable<K, V> {
    private int size; // Number of entries in the hashtable
    private final int capacity; // Size of the array
    private final HashEntry<K, V>[] table; // Array of entries

    // Constructor
    public OpenHashTable(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.table = new HashEntry[capacity];
    }

    // Get the index of a key using hash function and linear probing
    public int getIndex(K key) {
        int hashCode = key.hashCode();
        int index = Math.abs(hashCode % capacity);
        int i = index;
        while (table[i] != null && !table[i].getKey().equals(key)) {
            i = (i + 1) % capacity; // Increment by one and wrap around if necessary
            if (i == index) { // The array is full
                return -1;
            }
        }
        return i;
    }

    // Get the value associated with a key
    public V get(K key) {
        int index = getIndex(key);
        if (index == -1 || table[index] == null || table[index].isDeleted()) {
            return null; // Key not found
        }
        return table[index].getValue();
    }

    // Put a key-value pair into the hashtable
    public void put(K key, V value) {
        int index = getIndex(key);
        if (index == -1) {
            throw new RuntimeException("The hashtable is full");
        }
        if (table[index] == null || table[index].isDeleted()) {
            size++;
        }
        table[index] = new HashEntry<>(key, value);
    }

    // Remove a key-value pair from the hashtable
    public void remove(K key) {
        int index = getIndex(key);
        if (index == -1 || table[index] == null || table[index].isDeleted()) {
            return; // Key not found
        }
        size--;
        V value = table[index].getValue();
        table[index].setDeleted(true); // Mark the entry as deleted
    }

    // Check if the hashtable is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the number of entries in the hashtable
    public int getSize() {
        return size;
    }

    // If table have a provided key
    public boolean containsKey(K key) {
        return keySet().stream().anyMatch(local -> local == key);
    }

    // Get all table keys
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (HashEntry<K, V> bucket : table) {
            if (bucket != null)
                keys.add(bucket.getKey());
        }
        return keys;
    }


    public static void main(String[] args) {
        OpenHashTable<String, Integer> ht = new OpenHashTable<>(10);

        // Put some key-value pairs
        ht.put("Alice", 25);
        ht.put("Bob", 30);
        ht.put("Charlie", 35);

        // Get the value associated with a key
        int age = ht.get("Bob");
        System.out.println("Bob's age is " + age);

        // Remove a key-value pair
        ht.remove("Charlie");

        // Check if the hashtable contains a key
        boolean hasKey = ht.containsKey("Alice");
        System.out.println("Does the hashtable contain Alice? " + hasKey);

        // Iterate over the keys
        for (String key : ht.keySet()) {
            System.out.println(key + " -> " + ht.get(key));
        }
    }
}