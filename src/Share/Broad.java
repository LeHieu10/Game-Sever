/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Share;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class Broad implements Serializable{
    private Piece[][] piece;
    private int time;
    
    public Broad(Piece[][] piece, int time)
    {
        this.piece = piece;
        this.time = time;
    }

    public Piece[][] getPiece() {
        return piece;
    }

    public void setPiece(Piece[][] piece) {
        this.piece = piece;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
}
