package org.zwl.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * @author zhenweiliu created on 3/6/14
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8080));
        Future<AsynchronousSocketChannel> acceptFuture = server.accept();

    }
}
