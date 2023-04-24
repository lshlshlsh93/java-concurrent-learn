package concurrent.part02.chapter12.exe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author lishaohui
 * @Date 2023/4/24 11:43
 */
public class Server extends Thread {

    private int port;

    public static final int DEF_PORT = 9003;

    private volatile boolean start = true;

    private List<ClientHandler> clientHandlers = new ArrayList<>();

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private ServerSocket serverSocket;

    public Server() {
        this(DEF_PORT);
    }

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(port);
            Socket client = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(client);
            while (start) {
                executor.submit(clientHandler);
                this.clientHandlers.add(clientHandler);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.dispose();
        }
    }

    private void dispose() {
        System.out.println("dispose");
        this.clientHandlers.forEach(ClientHandler::stop);

        this.executor.shutdown();
    }


    public void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        this.serverSocket.close();
    }

}
