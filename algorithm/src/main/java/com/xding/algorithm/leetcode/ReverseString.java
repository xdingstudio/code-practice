package com.xding.algorithm.leetcode;

/**
 * 344. 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 * 示例 1：
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * <p>
 * 示例 2：
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-string
 *
 * @author xding
 * @date 2020/5/28 18:17
 */
public class ReverseString {

    /**
     * 要求原地修改输入数组、使用 O(1) 的额外空间，则不能使用递归
     * @param s
     */
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            System.out.println("");
            return;
        }

        for (int i = 0; i <= (s.length - 1) / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
            if (i != s.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");

        System.out.println(sb.toString());
    }
}
