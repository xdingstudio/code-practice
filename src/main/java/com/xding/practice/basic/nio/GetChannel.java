package com.xding.practice.basic.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 参考文章： 学习NIO——http://blog.csdn.net/linsongbin1/article/details/54865371
 * <p>
 * Buffer类的四个索引：mark（标记）、position（位置）、limit（界限）、capacity（容量）
 *
 * @author xding
 * @version 0.1 2017/9/19
 */
public class GetChannel {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        // 可以向通道传送用于读写的 ByteBuffer
        FileChannel channel = null;
        // Write a file
        try {
            channel = new FileOutputStream("data.txt").getChannel();
            channel.write(ByteBuffer.wrap("Some text ".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Add to the end of the file
        try {
            channel = new RandomAccessFile("data.txt", "rw").getChannel();
            channel.position(channel.size());
            channel.write(ByteBuffer.wrap("Some more".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Read the file
        try {
            channel = new FileInputStream("data.txt").getChannel();
            // 显示分配 ByteBuffer
            ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
            channel.read(buffer);
            // flip() 将 Buffer 的索引 limit 设置为 position，position 设置为0
            // 用于准备从缓冲区读取已经写入的数据
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
