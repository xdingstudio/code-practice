package com.xding.algorithm;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author xding
 * @version 0.1 2017/9/30
 */
public class PrintList {
    public static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (true) {
            if (listNode == null) {
                break;
            }
            arrayList.add(listNode.val);
            listNode = listNode.next;
        }
        int length = arrayList.size();
        ArrayList<Integer> newArrayList = new ArrayList<>(length);
        Collections.reverse(arrayList);
        for (int i = 0; i < length; i++) {
            newArrayList.add(i, arrayList.get(length - 1 - i));
        }
        return newArrayList;
    }
}
