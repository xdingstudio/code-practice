package com.xding.algorithm.leetcode;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 *
 * @author xding
 * @date 2020/4/30 22:47
 */
public class ReverseList {

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
     * 遍历链表，用一个变量暂存当前结点，并将当前结点从头部插入另一个链表
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode newHead = new ListNode(0);
        ListNode temp;
        while (head != null) {
            temp = head;
            head = head.next;
            temp.next = newHead.next;
            newHead.next = temp;
        }
        return newHead.next;
    }
}
