package com.xding.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * *      4
 * *    /   \
 * *   2     7
 * *  / \   / \
 * * 1   3 6   9
 * 输出：
 * *      4
 * *    /   \
 * *   7     2
 * *  / \   / \
 * * 9   6 3   1
 * <p>
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 *
 * @author xding
 * @date 2020/6/1 14:45
 */
public class InvertTree {

    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    /**
     * 深度优先搜索，对每个节点的左右节点进行交换
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            invertTree(root.left);
        }
        if (root.right != null) {
            invertTree(root.right);
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    /**
     * 广度优先搜索，依次对每一层的节点的左右子树进行交换
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }

        return root;
    }
}
