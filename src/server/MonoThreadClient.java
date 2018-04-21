package server;

import java.io.*;
import java.net.Socket;

public class MonoThreadClient implements Runnable {
    private static Socket clientDialog;


    public MonoThreadClient(Socket client) {
        MonoThreadClient.clientDialog = client;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(clientDialog.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientDialog.getInputStream());

            while (!clientDialog.isClosed()) {

            }

            in.close();
            out.close();
            clientDialog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
