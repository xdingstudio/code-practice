package com.xding.practice.algorithm;

import java.util.ArrayList;

public class PrintListFromTailToHead {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        if(listNode == null) {
            return list;
        }
        while (listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        int length = list.size();
        ArrayList<Integer> newList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            newList.add(list.get(length - 1 - i));
        }
        return newList;
    }
}
