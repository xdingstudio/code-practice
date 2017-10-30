package com.xding.practice.datastructure;

/**
 * @author xding
 * @version 0.1 2017/10/25
 */
public class RedBlackTree<T extends Comparable<? super T>> {
    private static final int RED = 0;
    private static final int BLACK = 1;

    private static class RedBlackNode<T> {
        T element;
        RedBlackNode<T> left;
        RedBlackNode<T> right;
        int color;

        RedBlackNode(T element) {
            this(element, null, null);
        }

        RedBlackNode(T element, RedBlackNode<T> left, RedBlackNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.color = BLACK;
        }
    }

    private RedBlackNode<T> header;
    private RedBlackNode<T> nullNode;

    public RedBlackTree() {
        nullNode = new RedBlackNode<T>(null);
        nullNode.left = nullNode.right = nullNode;
        header = new RedBlackNode<T>(null);
        header.left = header.right = nullNode;

    }

    private RedBlackNode<T> current;
    private RedBlackNode<T> parent;
    private RedBlackNode<T> grand;
    private RedBlackNode<T> great;

    /**
     * 当当前节点的两个子节点都为红色时，进行颜色翻转，若父节点也为红色，
     *
     * @param item
     */
    private void handleReorient(T item) {
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        // 父节点也为红色时，进行旋转
        if (parent.color == RED) {
            // 不论是一字型还是之字型，grand 最后都是置为红色
            grand.color = RED;
            // item 在祖父节点和父节点的值之间，构成之字型，需进行双旋转，双旋转又可以拆为两个单旋转，在之字型时先做一次单旋转，然后与一字型一样做一次单旋转即可。
            if ((compare(item, grand) < 0) != (compare(item, parent) < 0)) {
                // 先进行一次单旋转，此时 current 与 parent 旋转，current 上升一层，parent 与 current 指向同一节点
                parent = rotate(item, grand);
            }
            // 一字型时，parent 与 grand 旋转；之字型时，已做过一次旋转，再将 current 与 grand 旋转，故均以 great 为根
            // current 指向的节点未改变，grand 指向的节点成为 current 的子节点
            current = rotate(item, great);
            // 将旋转后子树的根置为黑色
            current.color = BLACK;
        }

    }

    public void insert(T item) {
        current = parent = grand = header;
        nullNode.element = item;

        while (compare(item, current) != 0) {
            // 向下移动一层
            great = grand;
            grand = parent;
            parent = current;
            current = compare(item, current) < 0 ? current.left : current.right;
            if (current.left.color == RED && current.right.color == RED) {
                handleReorient(item);
            }
        }
        if (current != nullNode) {
            return;
        }
        current = new RedBlackNode<T>(item, nullNode, nullNode);

        if (compare(item, parent) < 0) {
            parent.left = current;
        } else {
            parent.right = current;
        }
        handleReorient(item);
    }

    private final int compare(T item, RedBlackNode<T> node) {
        if (node == header) {
            return 1;
        } else {
            return item.compareTo(node.element);
        }
    }

    /**
     * 根据 parent 子树的方向，将 parent 的子节点与孙子节点进行单旋转，并返回旋转后的 parent 子节点，即经旋转后子树的根节点
     *
     * @param item
     * @param parent
     * @return
     */
    private RedBlackNode<T> rotate(T item, RedBlackNode<T> parent) {
        if (compare(item, parent) < 0) {
            return parent.left = compare(item, parent.left) < 0 ?
                    // LL
                    rotateWithLeftChild(parent.left) :
                    // LR
                    rotateWithRightChild(parent.left);
        } else {
            return parent.right = compare(item, parent.right) < 0 ?
                    // RL
                    rotateWithLeftChild(parent.right) :
                    // RR
                    rotateWithRightChild(parent.right);
        }
    }

    private RedBlackNode<T> rotateWithLeftChild(RedBlackNode<T> node) {
        RedBlackNode<T> head = node.left;
        node.left = head.right;
        head.right = node;
        return head;
    }

    private RedBlackNode<T> rotateWithRightChild(RedBlackNode<T> node) {
        RedBlackNode<T> head = node.right;
        node.right = head.left;
        head.left = node;
        return head;
    }
}
