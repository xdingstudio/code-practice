package com.xding.practice.datastructure;

/**
 * @author xding
 * @version 0.1 2017/10/24
 */
public class AvlTree<T extends Comparable<? super T>> {
    private static class AvlNode<T> {
        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;

        AvlNode(T element, AvlNode<T> left, AvlNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }

        AvlNode(T element) {
            this(element, null, null);
        }

    }

    private AvlNode<T> root;

    public AvlTree() {
        this.root = null;
    }

    public void insert(T element) {
        root = insert(element, root);
    }

    /**
     * 返回节点 node 的高度，若 node 为 null，则返回 -1
     *
     * @param node
     * @return
     */
    private int height(AvlNode<T> node) {
        return node == null ? -1 : node.height;
    }

    private AvlNode<T> insert(T element, AvlNode<T> node) {
        if (node == null) {
            return new AvlNode<T>(element);
        }
        int compareResult = compare(element, node.element);
        if (compareResult < 0) {
            node.left = insert(element, node.left);
            if (height(node.left) - height(node.right) == 2) {
                if (compare(element, node.left.element) < 0) {
                    node = rotateWithLeftChild(node);
                } else {
                    node = doubleWithLeftChild(node);
                }
            }
        } else if (compareResult > 0) {
            node.right = insert(element, node.right);
            if (height(node.right) - height(node.left) == 2) {
                if (compare(element, node.right.element) > 0) {
                    node = rotateWithRightChild(node);
                } else {
                    node = doubleWithRightChild(node);
                }
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private int compare(T a, T b) {
        return a.compareTo(b);
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(k2.height, height(k1.right)) + 1;
        return k1;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3) {
        k3.right = rotateWithLeftChild(k3.left);
        return rotateWithRightChild(k3);

    }
}
