package com.fiit.dsa.hashTable;

import java.util.HashSet;
import java.util.Set;

public class ClosedHashTable<K, V> implements HashTable<K, V> {
    private int size; // Number of entries in the hashtable
    private final int capacity; // Size of the array
    private final HashNode<K, V>[] buckets; // Array of linked lists

    // Constructor
    public ClosedHashTable(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.buckets = new HashNode[capacity];
    }

    // Get the index of a key using hash function
    public int getIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % capacity);
    }

    // Get the value associated with a key
    public V get(K key) {
        int index = getIndex(key);
        HashNode<K, V> head = buckets[index];
        while (head != null) {
            if (head.getKey().equals(key)) {
                return head.getValue();
            }
            head = head.getNext();
        }
        return null; // Key not found
    }

    // Put a key-value pair into the hashtable
    public void put(K key, V value) {
        int index = getIndex(key);
        HashNode<K, V> head = buckets[index];
        while (head != null) {
            if (head.getKey().equals(key)) {
                head.setValue(value); // Update the value if key already exists
                return;
            }
            head = head.getNext();
        }
        size++;
        head = buckets[index];
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.setNext(head); // Insert at the beginning of the linked list
        buckets[index] = newNode;
    }

    // Remove a key-value pair from the hashtable
    public void remove(K key) {
        int index = getIndex(key);
        HashNode<K, V> head = buckets[index];
        HashNode<K, V> prev = null;
        while (head != null) {
            if (head.getKey().equals(key)) {
                break;
            }
            prev = head;
            head = head.getNext();
        }
        if (head == null) {
            return; // Key not found
        }
        size--;
        if (prev != null) {
            prev.setNext(head.getNext()); // Remove from the middle or end of the linked list
        } else {
            buckets[index] = head.getNext(); // Remove from the beginning of the linked list
        }
    }

    // Check if the hashtable is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the number of entries in the hashtable
    public int getSize() {
        return size;
    }

    public boolean containsKey(K key) {
        return keySet().stream().anyMatch(local -> local == key);
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (HashNode<K, V> bucket : buckets) {
            if (bucket != null)
                keys.add(bucket.getKey());
        }
        return keys;
    }

    public static void main(String[] args) {
        ClosedHashTable<String, Integer> local = new ClosedHashTable<>(10);

        // Put some key-value pairs
        local.put("Alice", 25);
        local.put("Bob", 30);
        local.put("Charlie", 35);

        // Get the value associated with a key
        int age = local.get("Bob");
        System.out.println("Bob's age is " + age);

        // Remove a key-value pair
        local.remove("Charlie");

        // Check if the hashtable contains a key
        boolean hasKey = local.containsKey("Alice");
        System.out.println("Does the hashtable contain Alice? " + hasKey);

        // Iterate over the keys
        for (String key : local.keySet()) {
            System.out.println(key + " -> " + local.get(key));
        }
    }
}
