package com.xding.algorithm.leetcode.list;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 *
 * @author xding
 * @date 2020/4/30 23:27
 */
public class ReverseBetween {

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
     * 插入结点时，处理好该节点的next
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(0);
        ListNode newTail = dummyHead;
        ListNode lm = dummyHead;
        ListNode temp;
        int i = 1;
        while (head != null) {
            temp = head;
            head = head.next;
            if (i < m) {
                temp.next = newTail.next;
                newTail.next = temp;
                newTail = newTail.next;
                lm = newTail;
            } else if (i == m) {
                temp.next = newTail.next;
                newTail.next = temp;
                newTail = newTail.next;
            } else if (i > n) {
                newTail.next = temp;
                break;
            } else {
                temp.next = lm.next;
                lm.next = temp;
            }
            i++;
        }
        return dummyHead.next;
    }
}
