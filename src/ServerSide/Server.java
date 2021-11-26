/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide;

import Share.Broad;
import Share.Message;
import Share.Piece;
import Share.Pos;
import Share.Room;
import Share.Users;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Server extends Thread {

    public Room room = null;

    private final Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    public Users user;
    Boolean execute;
    

    public Server(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        execute = true;
    }
    
    void ReceiveMessage(Message msg) throws IOException {

        switch (msg.getType()) {
            case 10: {
                user = (Users) msg.getObject();
                if (Main.listServer.isEmpty()) {
                    System.out.println("Server: Xin chao " + user.getName());
                    Main.uslist.add(user);
                } else if (user != null) {
                    Boolean flag = true;
                    // Kiem tra coi da co ai dang nhap hay chua
                    for (Server ser : Main.listServer) {
                        if (ser != this && ser.user != null && ser.user.getName().equalsIgnoreCase(user.getName())) {
                            user = null;
                            break;
                        }
                    }
                    if (user != null) {
                        System.out.println("Server: Xin chao " + user.getName());
                        Main.uslist.add(user);
                    }
                    SendMessage(10, user);
                    break;
                }
            }
            //Room
            case 20: {// Join room
                room = Main.listRoom.get(Integer.parseInt(msg.getObject().toString()));
                if (room.add(this) == false) //full
                {
                    int[] arrRoom = new int[Main.listRoom.size()];
                    for (int i = 0; i < Main.listRoom.size(); i++) {
                        arrRoom[i] = Main.listRoom.get(i).countAvailable();
                    }
                    SendMessage(22, arrRoom);
                } else {
                    SendMessage(20, null);
                }

                break;
            }
            case 21: //Get all room
            {
                int[] arrRoom = new int[Main.listRoom.size()];
                for (int i = 0; i < Main.listRoom.size(); i++) {
                    arrRoom[i] = Main.listRoom.get(i).countAvailable();
                }
                SendMessage(21, arrRoom);
                break;
            }
            case 23: {
                room = Main.listRoom.get(Integer.parseInt(msg.getObject().toString()));
                room.listClientView.add(this);
                SendMessage(20, null);
                break;
            }
            case 28: {
                if (room.server1 != null && room.server2 != null) {
                    Users[] arrUser = new Users[2];
                    arrUser[0] = room.server1.user;
                    arrUser[1] = room.server2.user;
                    room.server1.SendMessage(34, arrUser);
                    room.server2.SendMessage(34, arrUser);
                    for (Server ser : room.listClientView) {
                        ser.SendMessage(34, arrUser);
                        ser.SendMessage(37, null);
                    }
                }
                break;
            }
            case 30: // Lay ban co
            {
                Pos gPos = (Pos) msg.getObject();
                if (gPos != null) {
                    room.put(this, gPos);
                }

                if (room != null) {
                    for (Server ser : room.listClientView) {
                        ser.SendMessage(30, room.pieceses);
                    }
                }
                break;
            }
            case 38: {
                if (room != null) {
                    room.Reset();
                }
                break;
            }
            case 39: //Exit room
            {
                if (room != null) {
                    room.clientExit(this);
                }
                break;
            }
            case 40: //Chat
            {
                if (room != null) {
                    // Gui cho 2 client
                    if (room.server1 != this) {
                        room.server1.SendMessage(msg);
                    }
                    if (room.server2 != this) {
                        room.server2.SendMessage(msg);
                    }
                    for (Server ser : room.listClientView) {
                        if (ser != this) {
                            ser.SendMessage(msg);
                        }
                    }
                }
                break;
            }
            case 42:
            {
                Broad b =  (Broad) msg.getObject();
                room.addList(b);
                break;
            }
            case 43: 
            {
                if (room.server1 != this) {
                    room.server1.SendMessage(43, null);
                } else {
                    room.server2.SendMessage(43, null);
                }
                break;
            }
            case 44:
            {
                SendMessage(44, room.list);
                break;
            }
        }
    }

    public void SendMessage(int ty, Object obj) throws IOException {
        Message temp = new Message(ty, obj);
        outputStream.reset();
        outputStream.writeObject(temp);
    }

    public void SendMessage(Message temp) throws IOException {
        outputStream.reset();
        outputStream.writeObject(temp);
    }

    @Override
    public void run() {

        while (execute) {

            try {
                Object o = inputStream.readObject();
                if (o != null) {
                    ReceiveMessage((Message) o);
                }
                //Guilai();
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println(ex);
            }
        }
    }
}
