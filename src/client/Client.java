package client;

import server.Pocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread {
    private Socket socket;
    private int serverPort;
    private String serverIp;

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public Client(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(serverIp, serverPort);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Server not available");
        }


    }

    public void sendToServerObject(Pocket pocket)  {
        try {
            objectOutputStream.writeObject(pocket);
        } catch (IOException e) {
            System.out.println("Не удалось отправить");
        }
    }

    public Object receiveObjectFromServer() throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }
}
