package com.xding.algorithm.leetcode;

import java.util.Stack;

/**
 * 根据逆波兰表示法，求表达式的值。
 * <p>
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * <p>
 * 说明：
 * <p>
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 * <p>
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 * <p>
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 * <p>
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * <p>
 * 链接：https://leetcode-cn.com/problems/evaluate-reverse-polish-notation
 *
 * @author xding
 * @date 2020/5/2 22:37
 */
public class EvalRPN {

    /**
     * 栈实现
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        int op1, op2;
        for (String token : tokens) {
            if ("+".equals(token)) {
                op2 = stack.pop();
                op1 = stack.pop();
                stack.push(op1 + op2);
            } else if ("-".equals(token)) {
                op2 = stack.pop();
                op1 = stack.pop();
                stack.push(op1 - op2);
            } else if ("*".equals(token)) {
                op2 = stack.pop();
                op1 = stack.pop();
                stack.push(op1 * op2);
            } else if ("/".equals(token)) {
                op2 = stack.pop();
                op1 = stack.pop();
                stack.push(op1 / op2);
            } else {
                try {
                    stack.push(Integer.parseInt(token));
                } catch (NumberFormatException e) {
                    stack.push(evalRPN(token.split("")));
                }
            }
        }
        return stack.pop();
    }

    /**
     * 数组实现
     *
     * @param tokens
     * @return
     */
    public int evalRPN2(String[] tokens) {
        int[] stack = new int[tokens.length / 2 + 1];
        int index = 0;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack[index - 2] += stack[--index];
                    break;
                case "-":
                    stack[index - 2] -= stack[--index];
                    break;
                case "*":
                    stack[index - 2] *= stack[--index];
                    break;
                case "/":
                    stack[index - 2] /= stack[--index];
                    break;
                default:
                    try {
                        stack[index++] = Integer.parseInt(token);
                    } catch (NumberFormatException e) {
                        stack[index++] = evalRPN(token.split(""));
                    }
                    break;
            }
        }
        return stack[0];
    }
}
