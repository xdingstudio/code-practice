package com.xding.practice.algorithm;

/**
 * @author xding
 * @version 0.1 2017/9/30
 */
public class ReplaceSpace {
    public String replaceSpace(StringBuffer str) {
        if (str == null ) {
            return null;
        }
        int start;
        while (true) {
            start = str.indexOf(" ");
            if (start == -1) {
                break;
            }
            str = str.replace(start, start + 1, "%20");
        }
        return str.toString();
    }
}
