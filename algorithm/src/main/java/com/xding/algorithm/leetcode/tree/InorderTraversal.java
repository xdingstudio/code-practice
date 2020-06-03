package com.xding.algorithm.leetcode.tree;

import java.util.*;

/**
 * 94. 二叉树的中序遍历
 * <p>
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * 输入: [1,null,2,3]
 * *    1
 * *     \
 * *      2
 * *     /
 * *    3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 *
 * @author xding
 * @date 2020/6/3 16:07
 */
public class InorderTraversal {

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
     * 递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private void helper(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            helper(root.left, result);
        }
        result.add(root.val);
        if (root.right != null) {
            helper(root.right, result);
        }
    }

    /**
     * 迭代
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new LinkedList<>();

        TreeNode current = root;
        while (!(current == null && stack.isEmpty())) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }

        return result;
    }

}
