package com.xding.practice.algorithm;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author xding
 * @version 0.1 2017/9/30
 */
public class PrintList {

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
