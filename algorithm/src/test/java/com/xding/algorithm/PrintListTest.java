package com.xding.algorithm;

import org.junit.Test;
import com.xding.algorithm.PrintList.ListNode;

public class PrintListTest {
    @Test
    public void test1() throws Exception {
        ListNode node1 = new ListNode(58);
        ListNode node2 = new ListNode(24);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(67);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        PrintList test = new PrintList();
        System.out.println(test.printListFromTailToHead(node1));
    }

}