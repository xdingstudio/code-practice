package com.xding.practice.algorithm;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import com.xding.practice.algorithm.PrintListFromTailToHead.ListNode;

public class PrintListFromTailToHeadTest {
    @Test
    public void test1() throws Exception {
        PrintListFromTailToHead solution = new PrintListFromTailToHead();
        ListNode node1 = solution.new ListNode(67);
        ListNode node2 = solution.new ListNode(0);
        ListNode node3 = solution.new ListNode(24);
        ListNode node4 = solution.new ListNode(58);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ArrayList<Integer> list = solution.printListFromTailToHead(node1);
        int[] data = {58, 24, 0, 67};
        System.out.print(list);
    }

}