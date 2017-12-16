package com.xding.practice.algorithm;

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
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = new ReConstructBinaryTree().solution(pre, in);
        System.out.println(root);
    }
}
