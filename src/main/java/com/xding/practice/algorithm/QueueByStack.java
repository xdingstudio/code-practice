package com.xding.practice.algorithm;

import java.util.Stack;

public class QueueByStack {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.empty()) {
            Integer i;
            if (stack1.empty()) {
                throw new RuntimeException("queue is empty");
            }
            while (!stack1.empty()) {
                i = stack1.pop();
                stack2.push(i);
            }
        }
        return stack2.pop();
    }
}
