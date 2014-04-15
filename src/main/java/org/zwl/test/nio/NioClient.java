package org.zwl.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import static java.nio.channels.SelectionKey.OP_CONNECT;
import static java.nio.channels.SelectionKey.OP_READ;
import static java.nio.channels.SelectionKey.OP_WRITE;

/**
 * @author zhenweiliu created on 3/6/14
 */
public class NioClient {

    public NioClient(String host, int port) throws Exception {
        Selector selector = Selector.open();
        SocketChannel sc = SocketChannel.open(new InetSocketAddress(host, port));
        sc.configureBlocking(false);
        SelectionKey sk = sc.register(selector, OP_CONNECT | OP_READ | OP_WRITE);
        new ClientWriteThread(sk).start();
        while (selector.select() > 0) {
            for (SelectionKey key : selector.selectedKeys()) {
                selector.selectedKeys().remove(key);
                SocketChannel sc2 = (SocketChannel) key.channel();
                if (key.isConnectable()) {
                    System.out.println("Connection established");
                } else if (key.isReadable()) {
                    System.out.println("Read Server");
                    ByteBuffer bb = ByteBuffer.allocate(128);
                    bb.clear();
                    while (sc2.read(bb) > 0) {
                        bb.flip();
                        System.out.print(StandardCharsets.UTF_8.decode(bb));
                        bb.clear();
                    }
                    System.out.println();
                }
            }
        }
    }

    private static class ClientWriteThread extends Thread {

        private SelectionKey key;

        private Scanner scanner = new Scanner(System.in);

        public ClientWriteThread(SelectionKey key) {
            this.key = key;
        }

        @Override
        public void run() {
            while (key.isWritable()) {
                String msg = scanner.nextLine();
                ByteBuffer bb = ByteBuffer.allocate(msg.getBytes().length);
                bb.clear();
                bb.put(msg.getBytes());
                bb.flip();
                try {
                    while (bb.hasRemaining()) {
                        ((SocketChannel) key.channel()).write(bb);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new NioClient("localhost", 8089);

    }
}
