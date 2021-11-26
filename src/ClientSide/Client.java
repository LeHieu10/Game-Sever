/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide;

import Share.Message;
import Share.Users;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author ASUS
 */
public class Client extends Thread {

    Socket socket;
    OutputStream outputStream;
    ObjectOutputStream objectOutputStream;
    InputStream inputStream;
    ObjectInputStream objectInputStream;

    public Users user;

    public ReceiveMessage receive;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        outputStream = socket.getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);
        inputStream = socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object o = objectInputStream.readObject();

                if (o != null) {
                    receive.ReceiveMessage((Message) o);
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }
        }
    }

    public void SendMessage(int ty, Object obj) throws IOException {
        Message temp = new Message(ty, obj);
        SendMessage(temp);
    }

    public void SendMessage(Message temp) throws IOException {
        objectOutputStream.reset();
        objectOutputStream.writeObject(temp);
    }

}
