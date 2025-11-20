import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
        import java.util.Iterator;
import java.util.Set;

public class NonBlockingTCPServer {

    public static void main(String[] args) {
        try {
            // 1. Open ServerSocketChannel
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(5000));
            serverChannel.configureBlocking(false); // Non-blocking mode

            // 2. Open Selector
            Selector selector = Selector.open();

            // 3. Register server channel with selector to accept connections
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server started on port 5000...");

            // 4. Event loop
            while (true) {
                selector.select(); // Blocks until at least one channel is ready

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    if (key.isAcceptable()) {
                        // Accept client connection
                        ServerSocketChannel srv = (ServerSocketChannel) key.channel();
                        SocketChannel clientChannel = srv.accept();
                        clientChannel.configureBlocking(false);

                        clientChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("Connected to: " + clientChannel.getRemoteAddress());
                    }

                    if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        int bytesRead = clientChannel.read(buffer);
                        if (bytesRead == -1) {
                            clientChannel.close();
                            System.out.println("Client disconnected.");
                        } else {
                            buffer.flip();
                            String msg = new String(buffer.array(), 0, buffer.limit());
                            System.out.println("Received: " + msg);

                            // Echo message back
                            buffer.rewind();
                            clientChannel.write(buffer);
                        }
                    }

                    iter.remove(); // Remove the processed key
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
