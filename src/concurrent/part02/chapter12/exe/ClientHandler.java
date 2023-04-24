package concurrent.part02.chapter12.exe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author lishaohui
 * @Date 2023/4/24 11:49
 */
public class ClientHandler implements Runnable {

    private final Socket socket;

    private volatile boolean isRunning = true;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter pw = new PrintWriter(outputStream)) {
            while (isRunning) {
                String message = br.readLine();
                if (message == null) {
                    break;
                }
                System.out.println("Come from client >" + message);
                pw.write("echo " + message + "\n");
                pw.flush();
            }
        } catch (IOException e) {
//            e.printStackTrace();
            this.isRunning = false;
        } finally {
            this.stop();
        }
    }

    public void stop() {
        if (!isRunning) {
            return;
        }
        this.isRunning = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            System.out.println("occur error" + e);
        }

    }
}
