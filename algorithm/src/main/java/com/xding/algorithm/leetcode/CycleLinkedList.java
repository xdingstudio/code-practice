package com.xding.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * <p>
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 *
 * @author xding
 * @date 2020/4/26 16:18
 */
public class CycleLinkedList {
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
     * 哈希表法
     */
    public boolean hashTable(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<>();
        ListNode listNode = head;
        while (listNode.next != null) {
            if (nodeSet.contains(listNode)) {
                return true;
            } else {
                nodeSet.add(listNode);
            }
            listNode = listNode.next;
        }
        return false;
    }

    /**
     * 双指针法
     *
     * @param head
     * @return
     */
    public boolean twoNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
