package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static ExecutorService executeIt = Executors.newFixedThreadPool(2);


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7777); BufferedReader br = new BufferedReader((new InputStreamReader(System.in)))) {
            {
                System.out.println("Server started at " + 7777);
                while (!serverSocket.isClosed()) {
                    if (br.ready()) {
                        String serverCommand = br.readLine();
                        if (serverCommand.equalsIgnoreCase("quit")) {
                            System.out.println("Server shutdown...");
                            serverSocket.close();
                            break;
                        }
                    }
                    Socket client = serverSocket.accept();
                    executeIt.execute(new MonoThreadClient(client));
                    System.out.println("Connection accept. " + client.getInetAddress());
                }
            }

            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
