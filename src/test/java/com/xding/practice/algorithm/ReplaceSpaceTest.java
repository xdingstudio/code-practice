package com.xding.practice.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReplaceSpaceTest {
    @Test
    public void test1() throws Exception {
        ReplaceSpace test = new ReplaceSpace();
        StringBuffer str = new StringBuffer();
        str.append("hello world");
        String result = test.replaceSpace(str);
        assertEquals(result, "hello%20world");
    }

    @Test
    public void test2() throws Exception {
        ReplaceSpace test = new ReplaceSpace();
        StringBuffer str = null;
        String result = test.replaceSpace(str);
        assertEquals(result, null);
    }

    @Test
    public void test3() throws Exception {
        ReplaceSpace test = new ReplaceSpace();
        StringBuffer str = new StringBuffer();
        str.append("  hello  world  ");
        String result = test.replaceSpace(str);
        assertEquals(result, "%20%20hello%20%20world%20%20");
    }

    @Test
    public void test4() throws Exception {
        ReplaceSpace test = new ReplaceSpace();
        StringBuffer str = new StringBuffer();
        str.append("     ");
        String result = test.replaceSpace(str);
        assertEquals(result, "%20%20%20%20%20");
    }

}