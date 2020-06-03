package com.xding.algorithm.leetcode.string;

/**
 * 208. 实现 Trie (前缀树)
 * <p>
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * <p>
 * 说明:
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 * <p>
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 *
 * @author xding
 * @date 2020/6/2 14:24
 */
public class Trie {

    private static class Node {
        char val;
        boolean isEnd;
        Node[] next;

        public Node() {
            next = new Node[26];
        }

        public Node(char val) {
            this.val = val;
            next = new Node[26];
        }
    }

    private final Node root = new Node();

    /**
     * Initialize your data structure here.
     */
    public Trie() {
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.next[index] == null) {
                Node newNode = new Node(word.charAt(i));
                node.next[index] = newNode;
            }
            node = node.next[index];
        }
        node.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return search(word, true);
    }

    private boolean search(String word, boolean endFlag) {
        Node node = root;
        if (word == null || word.length() == 0) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            node = node.next[word.charAt(i) - 'a'];
            if (node == null || node.val != word.charAt(i)) {
                return false;
            }
        }
        if (endFlag && !node.isEnd) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return search(prefix, false);
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        // 返回 true
        System.out.println(trie.search("apple"));
        // 返回 false
        System.out.println(trie.search("app"));
        // 返回 true
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        // 返回 true
        System.out.println(trie.search("app"));
    }
}
