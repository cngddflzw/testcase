package org.zwl.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

/**
 * @author zhenweiliu created on 3/6/14
 */
public class NioClient {

    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        client.connect(new InetSocketAddress("localhost", 8080)).get();

        ByteBuffer message = ByteBuffer.wrap("ping".getBytes());
        client.write(message).get();
    }
}
