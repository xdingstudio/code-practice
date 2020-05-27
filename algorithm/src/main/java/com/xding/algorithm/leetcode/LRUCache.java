package com.xding.algorithm.leetcode;

import java.util.HashMap;

/**
 * 146. LRU缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 示例:
 * LRUCache cache = new LRUCache( 2 /缓存容量/ );
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 * <p>
 * 链接：https://leetcode-cn.com/problems/lru-cache
 *
 * @author xding
 * @date 2020/5/27 16:41
 */
public class LRUCache {

    Node head;
    Node tail;
    HashMap<Integer, Node> map;
    int size;
    int capacity;

    private class Node {
        int key;
        int value;
        Node pre;
        Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * LRU：最近最少使用，存入或者查询数据都会延长数据的寿命，当容量满时，先删除最旧的及最少使用的数据
     *
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        // 使用伪头部和伪尾部节点
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 获取一个数据时，判断缓存中是否存在 key，存在则返回对应的值，不存在则返回-1，
     * 找到数据之后，还需要将它移动到双向链表的尾部。
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeToTail(node);
            return node.value;
        } else {
            return -1;
        }
    }

    /**
     * 写入数据时，先判断缓存中是否存在，若不存在，且队列没满时，将值放到队列的尾部；若队列已满，则删除队列头部数据，并将写入数据放到队列尾部；
     * 若缓存从存在该关键字，则将对应的值移动到队列尾部，若 value 不同则更新 value。
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeToTail(node);
        } else {
            if (size < capacity) {
                Node node = new Node(key, value);
                addToTail(node);
            } else {
                removeHead();
                Node node = new Node(key, value);
                addToTail(node);
            }
        }
    }

    private void removeHead() {
        Node node = head.next;
        head.next = node.next;
        node.next.pre = head;
        node.pre = null;
        node.next = null;
        map.remove(node.key);
        size--;
    }

    private void removeNode(Node node) {
        // 移除 node
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void removeToTail(Node node) {
        // 移除 node
        removeNode(node);

        // 插入到最后一个节点后面
        tail.pre.next = node;
        node.pre = tail.pre;

        // 插入到尾部前面
        tail.pre = node;
        node.next = tail;
    }

    private void addToTail(Node node) {
        // 插入到最后一个节点后面
        tail.pre.next = node;
        node.pre = tail.pre;

        // 插入到尾部前面
        tail.pre = node;
        node.next = tail;

        map.put(node.key, node);
        size++;
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
        System.out.println("\n");

        LRUCache cache1 = new LRUCache(1);
        cache1.put(2, 1);
        System.out.println(cache1.get(2));
        cache1.put(3, 2);
        System.out.println(cache1.get(2));
        System.out.println(cache1.get(3));

        LRUCache cache2 = new LRUCache(2);
        cache2.put(2, 1);
        cache2.put(2, 2);
        System.out.println(cache2.get(2));
        cache2.put(1, 1);
        cache2.put(4, 1);
        System.out.println(cache2.get(2));
    }
}
