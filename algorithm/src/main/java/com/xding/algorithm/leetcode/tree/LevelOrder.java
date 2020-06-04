package com.xding.algorithm.leetcode.tree;

import java.util.*;

/**
 * 102. 二叉树的层序遍历
 * <p>
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * *     3
 * *    / \
 * *   9  20
 * *     /  \
 * *    15   7
 * 返回其层次遍历结果：
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 *
 * @author xding
 * @date 2020/6/3 17:10
 */
public class LevelOrder {

    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    /**
     * 基于 BFS 的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                levelList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(levelList);
        }

        return result;
    }

    public static void main(String[] args) {
        LevelOrder obj = new LevelOrder();
        TreeNode node3 = new TreeNode(3);
        node3.left = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        node3.right = node20;
        node20.left = new TreeNode(15);
        node20.right = new TreeNode(7);

        List<List<Integer>> result = obj.levelOrder(node3);
        System.out.println(result);
    }
}
