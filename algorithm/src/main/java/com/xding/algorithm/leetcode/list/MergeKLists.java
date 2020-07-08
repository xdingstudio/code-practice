package com.xding.algorithm.leetcode.list;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 *
 * @author xding
 * @date 2020/4/28 17:21
 */
public class MergeKLists {
    /**
     * Definition for singly-linked list.
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * K 指针法：K 个指针分别指向 K 条链表
     * <p>
     * 假设共有 K 个链表，链表最大长度为 N。每次循环找到最小值是时间复杂度是 O(K)，需要做 K*N 次比较，时间复杂度为O(K*N)。
     * 总时间复杂度：O(K^2*N)
     * 空间复杂度：O(1)（改变原数组的情况下，如果不改变原数组，需要O(K)的空间）
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists1(ListNode[] lists) {

        ListNode head = new ListNode(0);
        ListNode tail = head;
        ListNode minNode;
        int minPointer;

        while (true) {
            minNode = null;
            minPointer = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (minNode == null || lists[i].val < minNode.val) {
                    minNode = lists[i];
                    minPointer = i;
                }
            }

            if (minNode == null) {
                break;
            }

            tail.next = minNode;
            tail = tail.next;
            lists[minPointer] = minNode.next;
        }

        return head.next;
    }

    /**
     * 使用小顶堆对 算法1 中的查找最小值进行优化，使每次查最小值的时间复杂度变为 O(logK)， 仍需要做 K*N 次比较
     * 总时间复杂度：O(NlogK)
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = minNode;
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return head.next;
    }

    /**
     * 采用递归对两两链表进行合并
     *
     * @param lists
     * @return
     */
    public ListNode recursionMerge(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge2List(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        ListNode minNode;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                minNode = l1;
                l1 = l1.next;
            } else {
                minNode = l2;
                l2 = l2.next;
            }
            tail.next = minNode;
            tail = tail.next;
        }

        if (l1 == null) {
            tail.next = l2;
        } else {
            tail.next = l1;
        }

        return head.next;
    }

    private ListNode merge(ListNode[] lists, int low, int high) {
        if (low == high) {
            return lists[low];
        }
        int mid = low + (high - low) / 2;
        ListNode list1 = merge(lists, low, mid);
        ListNode list2 = merge(lists, mid + 1, high);

        return merge2List(list1, list2);
    }
}

