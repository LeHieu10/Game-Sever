/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Share;

import ServerSide.Server;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Room implements Serializable {

    int id;
    public Server server1 = null;
    public Server server2 = null;

    public Piece[][] pieceses;
    int Dx[];
    int Dy[];

    static final int D_UP = 0;
    static final int D_UPRIGHT = 1;
    static final int D_RIGHT = 2;
    static final int D_DOWNRIGHT = 3;
    static final int D_DOWN = 4;
    static final int D_DOWNLEFT = 5;
    static final int D_LEFT = 6;
    static final int D_UPLEFT = 7;

    static final int BOARDSIZE = 25;

    static final int NOT5 = 0;
    static final int OK5 = 1;

    public ArrayList<Server> listClientView = null;
    
    public List<Broad> list;

    public Room(int _id) {
        list = new ArrayList<>();
        listClientView = new ArrayList<>();
        id = _id;
        Dx = new int[8];
        Dy = new int[8];

        NewGame();

        Dx[0] = 0;
        Dy[0] = -1;
        Dx[1] = 1;
        Dy[1] = -1;
        Dx[2] = 1;
        Dy[2] = 0;
        Dx[3] = 1;
        Dy[3] = 1;
        Dx[4] = 0;
        Dy[4] = 1;
        Dx[5] = -1;
        Dy[5] = 1;
        Dx[6] = -1;
        Dy[6] = 0;
        Dx[7] = -1;
        Dy[7] = -1;
    }

    public final void NewGame() {
        pieceses = new Piece[BOARDSIZE][BOARDSIZE];
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                pieceses[i][j] = new Piece();
            }
        }
    }

    public Boolean add(Server server) throws IOException {
        if (server1 == null) {
            server1 = server;
            return true;
        }
        if (server2 == null) {
            server2 = server;
            return true;
        }
        return false;
    }

    private void addFrist(List<Broad> list){
        Piece[][] p = new Piece[25][25];
        for (int x = 0; x < 25; x++) {
            for (int y = 0; y < 25; y++) {
                p[x][y] = new Piece();
            }
        }
        Broad b = new Broad(p, 0);
        list.add(b);
    }
    
    public void addList(Broad b){
        list.add(b);
    }
    
    public int countAvailable() {
        int n = 2;
        if (server1 != null) {
            n--;
        }
        if (server2 != null) {
            n--;
        }
        return n;
    }

    public void Reset() throws IOException {
        NewGame();
        list.removeAll(list);
        addFrist(list);
//        server1.SendMessage(41, null);
//        server2.SendMessage(41, null);
        server1.SendMessage(31, null);
        server2.SendMessage(36, null);
        for (Server ser : listClientView) {
            ser.SendMessage(30, pieceses);
        }
        server1.SendMessage(30, pieceses);
        server2.SendMessage(30, pieceses);
    }

    public void clientExit(Server server) throws IOException {
        for (Server ser : listClientView) {
            if (ser == server) {
                listClientView.remove(ser);
                ser.room = null;
                return;
            }
        }
        if (countAvailable() == 0) {
            if (server1.user == server.user) {
                server2.SendMessage(33, server1.user);
                server2.SendMessage(32, null);
                server2.room.NewGame();
                server1.room = null;
                server1 = server2;
                server2 = null;
            } else if (server2.user == server.user) {
                server1.SendMessage(33, server2.user);
                server1.room.NewGame();
                server1.SendMessage(32, null);
                server2.room = null;
                server2 = null;
            }
        } else {
            if (server1 != null) {
                server1.room = null;
                server1 = null;
            }
        }
    }

    public int GetSequence(int color, int x, int y, int direction) {
        int num = 0;
        int dx = Dx[direction];
        int dy = Dy[direction];
        Boolean Space = false;

        while (pieceses[x][y].State == color) {
            num++;
            x += dx;
            y += dy;
            System.out.println("x = dx + x = " + x + "; y = " + y);
            if (x < 0 || x >= BOARDSIZE || y < 0 || y >= BOARDSIZE) {
                break;
            }
            if (pieceses[x][y].State == Piece.EMPTY) {
                Space = true;
                break;
            }
        }
        return num;
    }

    public int Find5Block(int color, int x, int y) {

        int max, a;

        max = GetSequence(color, x, y, D_UP) + GetSequence(color, x, y, D_DOWN) - 1;
        a = GetSequence(color, x, y, D_LEFT) + GetSequence(color, x, y, D_RIGHT) - 1;
        max = Math.max(max, a);
        a = GetSequence(color, x, y, D_UPLEFT) + GetSequence(color, x, y, D_DOWNRIGHT) - 1;
        max = Math.max(max, a);
        a = GetSequence(color, x, y, D_UPRIGHT) + GetSequence(color, x, y, D_DOWNLEFT) - 1;
        max = Math.max(max, a);

        if (max >= 5) {
            return OK5;
        }

        return NOT5;
    }

    public void clientWinLose(Server server, Boolean isWin) {
        int sum = server.user.getScore();
        if (isWin) {
            sum += 100;
        } else {
            sum -= 100;
        }
        server.user.setScore(sum);
    }

    public int put(Server server, Pos gPos) throws IOException {
        Users[] user = new Users[2];
        user[0] = server1.user;
        user[1] = server2.user;
        if (server == server1) {
            pieceses[gPos.getX()][gPos.getY()].State = Piece.BLACK;
            if (Find5Block(Piece.BLACK, gPos.getX(), gPos.getY()) == OK5) {
                System.out.printf("Black win");
                clientWinLose(server1, true);
                server1.SendMessage(35, "win");
                clientWinLose(server2, false);
                server2.SendMessage(35, "lose");

                server1.SendMessage(34, user);
                server2.SendMessage(34, user);

            } else {
                server2.SendMessage(31, null);
                server1.SendMessage(36, null);
            }
        } else {
            pieceses[gPos.getX()][gPos.getY()].State = Piece.WHITE;
            if (Find5Block(Piece.WHITE, gPos.getX(), gPos.getY()) == OK5) {
                System.out.printf("WHITE win");
                clientWinLose(server2, true);
                server2.SendMessage(35, "win");
                clientWinLose(server1, false);
                server1.SendMessage(35, "lose");

                server1.SendMessage(34, user);
                server2.SendMessage(34, user);
            } else {
                server1.SendMessage(31, null);
                server2.SendMessage(36, null);
            }
        }
        server1.SendMessage(30, pieceses);
        server2.SendMessage(30, pieceses);

        return 1;
    }

    @Override
    public String toString() {
        int n = 2;
        if (server1 != null) {
            n--;
        }
        if (server2 != null) {
            n--;
        }
        return "Room " + id + ": " + n + " available";
    }
}
