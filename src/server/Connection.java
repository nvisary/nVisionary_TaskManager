package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Thread {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private String ip;

    public Connection(Socket socket) throws IOException {
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ip = socket.getInetAddress().toString();

    }

    @Override
    public void run() {
        Thread receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Pocket data = (Pocket) objectInputStream.readObject();
                    System.out.println(data.type);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        receiveThread.start();


        while (true) {
            try {
                objectOutputStream.writeObject("good");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
