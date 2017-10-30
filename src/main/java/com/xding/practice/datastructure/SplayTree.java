package com.xding.practice.datastructure;

/**
 * @author xding
 * @version 0.1 2017/10/26
 */
public class SplayTree<T extends Comparable<? super T>> {
    public SplayTree() {
        nullNode = new BinaryNode<T>(null);
        nullNode.left = nullNode;
        nullNode.right = nullNode;
        root = nullNode;
    }

    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        BinaryNode(T element) {
            this(element, null, null);
        }

        BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    private BinaryNode<T> root;
    private BinaryNode<T> nullNode;
    /**
     * 伸展时使用，用来存储 L、R 树的节点，左右节点分别引用左树的根和右树的根
     */
    private BinaryNode<T> header = new BinaryNode<T>(null);
    /**
     * 不同插入时使用
     */
    private BinaryNode<T> newNode = null;

    public void makeEmpty() {
        root = nullNode;
    }

    public boolean isEmpty() {
        return root == nullNode;
    }

    /**
     * 自顶向下展开，最后到达的节点最为新的根节点
     *
     * @param element
     * @param node
     * @return
     */
    private BinaryNode<T> splay(T element, BinaryNode<T> node) {
        /**
         * 指向树L的最大节点，类似游标的功能，将节点连接到 header.right 上
         */
        BinaryNode<T> leftTreeMax;
        /**
         * 指向树R的最小节点，类似游标的功能，将节点连接到 header.left 上
         */
        BinaryNode<T> rightTreeMin;
        header.left = header.right = nullNode;
        // 游标从 header 开始
        leftTreeMax = rightTreeMin = header;
        // 设置空节点的值与查询的值相同，若未查到该值，最后将与空节点比较，从而退出循环
        nullNode.element = element;

        while (true) {
            if (element.compareTo(node.element) < 0) {
                if (element.compareTo(node.left.element) < 0) {
                    // 一字型，将节点与左节点进行一次单旋转，使节点的左节点作为子树的新根
                    node = rotateWithLeftChild(node);
                }
                // 查找的值小于节点值，但节点的左节点为空，则结束查找
                if (node.left == nullNode) {
                    break;
                }
                // 链接右侧，单旋转时的父节点必小于 R 树中最小的节点，将父节点连接到 header 上，然后将引用只想 R 树中最小的节点
                rightTreeMin.left = node;
                // 移动游标到下一层最小的节点
                rightTreeMin = rightTreeMin.left;
                // 向下一层移动需查找的节点
                node = node.left;
            } else if (element.compareTo(node.element) > 0) {
                if (element.compareTo(node.right.element) > 0) {
                    node = rotateWithRightChild(node);
                }
                if (node.right == nullNode) {
                    break;
                }
                leftTreeMax.right = node;
                leftTreeMax = leftTreeMax.right;
                node = node.right;
            } else {
                break;
            }
        }
        // 合并时，将根的左节点插入 L 的右侧，根的右节点插入 R 的左侧
        leftTreeMax.right = node.left;
        rightTreeMin.left = node.right;
        // 将 L、R 节点连接到根节点，因为 header 左右节点初始为空，根据程序中链的指向，header.right 引用的是 L 树的根，header.left 引用的是 R 树的根
        node.left = header.right;
        node.right = header.left;
        return node;
    }

    private BinaryNode<T> rotateWithLeftChild(BinaryNode<T> node) {
        BinaryNode<T> head = node.left;
        node.left = head.right;
        head.right = node;
        return head;
    }

    private BinaryNode<T> rotateWithRightChild(BinaryNode<T> node) {
        BinaryNode<T> head = node.right;
        node.right = head.left;
        head.left = node;
        return head;
    }

    public void insert(T element) {
        if (newNode == null) {
            newNode = new BinaryNode<T>(null);
        }
        newNode.element = element;

        if (root == nullNode) {
            newNode.left = newNode.right = nullNode;
            root = newNode;
        } else {
            root = splay(element, root);
            if (element.compareTo(root.element) < 0) {
                // 新节点值小于根节点时， 将根节点的左子树插入新节点的左侧，将根节点及右子树插入新节点的右侧
                newNode.left = root.left;
                root.left = nullNode;
                newNode.right = root;
                root = newNode;
            } else if (element.compareTo(root.element) > 0) {
                newNode.right = root.right;
                root.right = nullNode;
                newNode.left = root;
                root = newNode;
            } else {
                // 不插入重复数据，并为将来的插入保留 newNode
                return;
            }
            newNode = null;
        }
    }

    public void remove(T element) {
        BinaryNode<T> newTree;
        root = splay(element, root);
        if (element.compareTo(root.element) != 0) {
            return;
        }
        if (root.left == nullNode) {
            newTree = root.right;
        } else {
            // 展开查找左子树中最大的节点作为根节点
            newTree = root.left;
            newTree = splay(element, newTree);
            newTree.right = root.right;
        }
        root = newTree;
    }
}
