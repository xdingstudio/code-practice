package com.xding.practice.basic.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 视图缓冲器 view buffer，通过某个特定的基本数据类型的视图查看其底层的 ByteBuffer。ByteBuffer 是实际存储数据的地方，对视图的任何修改都会映射成对
 * ByteBuffer 中数据的修改
 *
 * @author xding
 * @version 0.1 2017/9/19
 */
public class ViewBuffer {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        IntBuffer intBuffer = buffer.asIntBuffer();
        intBuffer.put(new int[] {11, 42, 47, 99, 143, 811, 1016});
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            int i = intBuffer.get();
            System.out.println(i);
        }
    }
}
