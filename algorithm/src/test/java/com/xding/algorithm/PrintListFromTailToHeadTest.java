package com.xding.algorithm;

import org.junit.Test;

import java.util.ArrayList;

import com.xding.algorithm.PrintListFromTailToHead.ListNode;

public class PrintListFromTailToHeadTest {
    @Test
    public void test1() throws Exception {
        PrintListFromTailToHead solution = new PrintListFromTailToHead();
        ListNode node1 = new ListNode(67);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(24);
        ListNode node4 = new ListNode(58);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ArrayList<Integer> list = solution.printListFromTailToHead(node1);
        int[] data = {58, 24, 0, 67};
        System.out.print(list);
    }

}