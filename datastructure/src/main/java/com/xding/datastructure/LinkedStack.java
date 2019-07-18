package com.xding.datastructure;

/**
 * @author xding
 * @version 0.1 2017/8/30
 */
public class LinkedStack<T> {
    private class Node {
        T item;
        Node next;

        Node() {
            this.item = null;
            this.next = null;
        }

        Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }

        boolean isEnd() {
            return item == null && next == null;
        }
    }

    /**
     * 末端哨兵 isEnd sentinel
     */
    private Node top = new Node();

    public void push(T item) {
        top = new Node(item, top);
    }

    public T pop() {
        T result = top.item;
        if (!top.isEnd()) {
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> lss = new LinkedStack<String>();
        for (String s : "Phasers on stun!".split(" ")) {
            lss.push(s);
        }
        String str;
        while ((str = lss.pop()) != null) {
            System.out.println(str);
        }
    }
}
