package com.xding.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 *
 * @author xding
 * @date 2020/5/2 17:15
 */
public class IsValidBracket {

    /**
     * 使用栈进行匹配判断，用 hash 表缓存映射字符
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character top = stack.pop();
                    if (!top.equals(map.get(c))) {
                        return false;
                    }
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
