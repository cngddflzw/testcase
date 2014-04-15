package org.zwl.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhenweiliu created on 3/6/14
 */
public class NioServer {

    private Selector selector;
    private AtomicInteger acceptCount = new AtomicInteger(0);
    private AtomicInteger readCount = new AtomicInteger(0);
    private AtomicInteger writeCount = new AtomicInteger(0);

    public NioServer(int port) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(port));
        selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            for (SelectionKey key : selector.selectedKeys()) {
                selector.selectedKeys().remove(key);
                if (key.isAcceptable()) {
                    System.out.println("Accept client " + acceptCount.incrementAndGet());
                    SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                } else if (key.isReadable()) {
                    System.out.println("Read client " + readCount.incrementAndGet());
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer bb = ByteBuffer.allocate(1024);
                    bb.clear();
                    try {
                        while (sc.read(bb) > 0) {
                            bb.flip();
                            System.out.print(StandardCharsets.UTF_8.decode(bb));
                            bb.clear();
                        }
                        System.out.println();
                    } catch (IOException e) {
                        sc.close();
                    }

                    if (key.isWritable()) {
                        System.out.println("Write client " + writeCount.incrementAndGet());
                        bb.clear();
                        bb.put("Hello Client".getBytes());
                        bb.flip();
                        try {
                            while (bb.hasRemaining()) {
                                sc.write(bb);
                            }
                        } catch (Exception e) {
                            sc.close();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new NioServer(8089);
    }
}
