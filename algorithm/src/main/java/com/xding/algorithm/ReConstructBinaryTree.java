package com.xding.algorithm;

import java.util.Arrays;

public class ReConstructBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode solution(int[] pre, int[] in) {
        if (isEmpty(pre) || isEmpty(in)) {
            return null;
        }
        if (pre.length == 1 && in.length == 1 && pre[0] != in[0]) {
            throw new RuntimeException("输入的前序遍历序列和中序遍历序列不匹配");
        }
        int val = pre[0];
        TreeNode root = new TreeNode(val);
        int i = 0;
        for (int j = 0; j < in.length; j++) {
            if (val == in[j]) {
                i = j;
                break;
            }
        }
        int[] inLeft = getArray(in, 0, i);
        int[] inRight = getArray(in, i + 1, in.length);
        int[] preLeft = getArray(pre, 1, inLeft.length + 1);
        int[] preRight = getArray(pre, inLeft.length + 1, pre.length);

        root.left = solution(preLeft, inLeft);
        root.right = solution(preRight, inRight);
        return root;
    }

    private int[] getArray(int[] a, int from, int to) {
        if (a == null || from < 0 || from > to) {
            return null;
        }
        return Arrays.copyOfRange(a, from, to);
    }

    private boolean isEmpty(int[] a) {
        return a == null || a.length == 0;
    }

    public static void main(String[] args) {
        ReConstructBinaryTree reConstructBinaryTree = new ReConstructBinaryTree();
        // 不完全二叉树
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = reConstructBinaryTree.solution(pre, in);
        System.out.println("测试 " + "不完全二叉树" + " 完成!");
        // 完全二叉树
        int[] pre2 = {1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 13, 7, 14, 15};
        int[] in2 = {8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15};
        TreeNode root2 = reConstructBinaryTree.solution(pre2, in2);
        System.out.println("测试 " + "完全二叉树" + " 完成!");
        // 空二叉树
        int[] pre3 = {};
        int[] in3 = {};
        TreeNode root3 = reConstructBinaryTree.solution(pre3, in3);
        System.out.println("测试 " + "空二叉树" + " 完成!");
        // 只有一个节点的二叉树
        int[] pre4 = {1};
        int[] in4 = {1};
        TreeNode root4 = reConstructBinaryTree.solution(pre4, in4);
        System.out.println("测试 " + "只有一个节点的二叉树" + " 完成!");
        // 所有节点都没有右子节点的二叉树
        int[] pre5 = {1, 2, 3, 4};
        int[] in5 = {4, 3, 2, 1};
        TreeNode root5 = reConstructBinaryTree.solution(pre5, in5);
        System.out.println("测试 " + "所有节点都没有右子节点的二叉树" + " 完成!");
        // 所有节点都没有左子节点的二叉树
        int[] pre6 = {1, 2, 3, 4};
        int[] in6 = {1, 2, 3, 4};
        TreeNode root6 = reConstructBinaryTree.solution(pre6, in6);
        System.out.println("测试 " + "所有节点都没有左子节点的二叉树" + " 完成!");
        // 输入的前序遍历序列和中序遍历序列不匹配
        int[] pre7 = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in7 = {4, 5, 2, 1, 3, 7, 8, 6};
        TreeNode root7 = reConstructBinaryTree.solution(pre7, in7);
        System.out.println("测试 " + "输入的前序遍历序列和中序遍历序列不匹配" + " 完成!");
    }
}
