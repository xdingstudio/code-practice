package com.xding.algorithm.leetcode.tree;

/**
 * 98. 验证二叉搜索树
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 示例 1:
 * 输入:
 * *     2
 * *    / \
 * *   1   3
 * 输出: true
 * <p>
 * 示例 2:
 * 输入:
 * *     5
 * *    / \
 * *   1   4
 * *      / \
 * *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 *
 * @author xding
 * @date 2020/6/1 16:05
 */
public class IsValidBST {

    /**
     * Definition for a binary tree node.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * 递归，判断子树中所有节点的值是否都在 (min,max) 的范围内（注意是开区间）
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(n)
     *
     * @param root
     * @param min
     * @param max
     * @return
     */
    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min) {
            return false;
        }
        if (max != null && root.val >= max) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

}
