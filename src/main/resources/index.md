<style>
* {
    font-size: 10pt;
    text-align: justify;
}
</style>

# Content

1. Introduction
2. Binary search tree
    1. Implementation
        1. `getMinimum()` / `getMaximum()`
        2. `getSuccessor()`
        3. Main methods
            1. `insert()`
            2. `search()`
            3. `delete()`
    2. Self-balancing binary search tree
        1. Implementation
            1. `rotateLeft()`
            2. `rotateRight()`
        2. AVL tree
            1. `rebalance()`
            2. Tests
                1. For `insert()`
                2. For `search()`
                3. For `delete()`
        3. Splay tree
            1. `splay()`
            2. Tests
                1. For `insert()`
                2. For `search()`
                3. For `delete()`
        4. Comparing summary
3. Hash table
    1. Hashing
    2. Open addressing
        1. Implementation
            1. `class Node`
            2. `getIndex()`
            3. `get()`
            4. `put()`
            5. `remove()`
            6. Tests
    3. Closed addressing
        1. Implementation
            1. `class Node`
            2. `getIndex()`
            3. `get()`
            4. `put()`
            5. `remove()`
            6. Test
    4. Comparing summary

# Introduction

In this assignment we needed to implement two binary search trees and two hash table variations.

I chose to implement the AVL and Splay trees because they are the most common and popular. For hash tables, there are
only two: with open addressing and with closed addressing.

For the best testing I decided to move away from the idea of testing with `Date.now()`, because it's silly and can lead
to incorrect results. Therefore, I decided to use [`jmh-core`](https://github.com/openjdk/jmh), since I chose to use
Java.

> ⚠️ In my implementation I tested up to one million, because, with numbers higher than that - I got an
> error `java.lang.OutOfMemoryError: native memory extradition`. In addition, I think tests above this number are
> useless,
> because it's better to specify performance on a set of tests, not on how big those tests are.

# Binary search trees

A binary search tree (BST) is a rooted binary tree data structure with the key of each internal node being greater than
all the keys in the respective node’s left subtree and less than the ones in its right subtree. In other words, it is a
tree data structure where each node has at most two children, which are referred to as the left child and the right
child. The left subtree of a node contains only nodes with keys lesser than the node’s key while the right subtree of a
node contains only nodes with keys greater than the node’s key.

Binary search trees are used to quickly find a value in a large set of values. They are particularly useful for
searching through sorted data.

## Implementation

<table>
<tr>
<td>

`getMinimum()`

</td>
<td style="padding: 0; width: 40%">

[//]: # (@f:off)
```java
protected Node getMinimum(Node node) {
    while (node.left != null) {
        node = node.left;
    }
    return node;
}
```
[//]: # (@f:on)

</td>
</tr>
<tr>
<td>

`getMaximum()`

</td>
<td style="padding: 0;">

[//]: # (@f:off)
```java
protected Node getMaximum(Node node) {
    while (node.right != null) {
        node = node.right;
    }
    return node;
}
```
[//]: # (@f:on)

</td>
</tr>
<tr>
<td>

`getSuccessor()`

</td>
<td style="padding: 0;">

[//]: # (@f:off)
```java
protected Node getSuccessor(Node node) {
    // if there is right branch, then successor is leftmost node of that
    // subtree
    if (node.right != null) {
        return getMinimum(node.right);
    } else { // otherwise it is the lowest ancestor whose left child is also
        // ancestor of node
        Node currentNode = node;
        Node parentNode = node.parent;
        while (parentNode != null && currentNode == parentNode.right) {
            // go up until we find parent that currentNode is not in right
            // subtree.
            currentNode = parentNode;
            parentNode = parentNode.parent;
        }
        return parentNode;
    }
}
```
[//]: # (@f:on)

</td>
</tr>
</table>


