package com.xding.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 151. 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * <p>
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <p>
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * 进阶：
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 *
 * @author xding
 * @date 2020/5/29 10:55
 */
public class ReverseWords {

    /**
     * 使用内置 API
     *
     * @param s
     * @return
     */
    public String reverseWords1(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    /**
     * 使用栈存储单词，使用 string 的 split 切分句子
     *
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        Stack<String> stack = new Stack<>();

        String[] wordArray = s.split(" ");
        for (String word : wordArray) {
            if (word == null || "".equals(word)) {
                continue;
            }
            stack.push(word);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            if (!stack.isEmpty()) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    /**
     * 将字符串去除多余空格并全部翻转，然后对每个单词再进行翻转
     *
     * @param s
     * @return
     */
    public String reverseWords3(String s) {
        // 去掉头尾空格
        int start = 0;
        int end = s.length() - 1;
        while (start <= end && s.charAt(start) == ' ') {
            start++;
        }
        while (start <= end && s.charAt(end) == ' ') {
            end--;
        }

        // 反转整个字符串，并去掉多余空格
        StringBuilder sb = new StringBuilder();
        for (int i = end; i >= start; i--) {
            if (s.charAt(i) == ' ' && s.charAt(i - 1) == ' ') {
                continue;
            }
            sb.append(s.charAt(i));
        }

        int wStart = 0;
        int wEnd = 0;
        // 反转每个单词
        for (int i = 0; i < sb.length(); i++) {
            if (i == sb.length() - 1) {
                wEnd = i;
                reverse(sb, wStart, wEnd);
            } else if (sb.charAt(i) == ' ') {
                wEnd = i - 1;
                reverse(sb, wStart, wEnd);
                wStart = i + 1;
            }
        }

        return sb.toString();
    }

    private void reverse(StringBuilder sb, int start, int end) {
        while (start <= end && end < sb.length()) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        System.out.println(reverseWords.reverseWords3("  hello world!  "));
    }

}
