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
public class Piece implements Serializable {

    public int State;
    static public final int EMPTY = 0;
    static public final int WHITE = 1;
    static public final int BLACK = 2;

    public Piece() {
        State = EMPTY;
    }
}
