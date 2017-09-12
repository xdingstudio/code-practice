package com.xding.codepractice.DataStructure;

import java.nio.BufferUnderflowException;

/**
 * @author xding
 * @version 0.1 2017/9/4
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        public BinaryNode(T element) {
            this(element, null, null);
        }

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T t) {
        return contains(t, root);
    }

    public T findMin() {
        if (isEmpty())
            throw new BufferUnderflowException();
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty())
            throw new BufferUnderflowException();
        return findMax(root).element;
    }

    public void insert(T t) {
        root = insert(t, root);
    }

    public void remove(T t) {
        root = remove(t, root);
    }

    public void printTree() {

    }

    private boolean contains(T t, BinaryNode<T> node) {
        if (node == null) {
            return false;
        }

        int compareResult = t.compareTo(node.element);

        if (compareResult < 0) {
            return contains(t, node.left);
        } else if (compareResult > 0) {
            return contains(t, node.right);
        } else {
            return true;
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        } else {
            return findMin(node.left);
        }
    }

    private BinaryNode<T> findMax(BinaryNode<T> node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private BinaryNode<T> insert(T t, BinaryNode<T> node) {
        if (node == null) {
            return new BinaryNode<T>(t);
        }

        int compareResult = t.compareTo(node.element);

        if (compareResult < 0) {
            node.left = insert(t, node.left);
        } else if (compareResult > 0) {
            node.right = insert(t, node.right);
        } else {
        }
        return node;
    }

    private BinaryNode<T> remove(T t, BinaryNode<T> node) {
        if (node == null) {
            return node;
        }

        int compareResult = t.compareTo(node.element);

        if (compareResult < 0) {
            node.left = remove(t, node.left);
        } else if (compareResult > 0) {
            node.right = remove(t, node.right);
        } else if (node.left != null && node.right != null) {
            // 可改进，removeMin
            node.element = findMin(node.right).element;
            node.right = remove(t, node.right);
        } else {
            node = (node.left != null) ? node.left : node.right;
        }

        return node;
    }

    private void printTree(BinaryNode<T> node) {
    }

}
