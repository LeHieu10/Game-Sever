/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide;

import Share.Piece;
import Share.Pos;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class JBanCo extends JPanel {

    static final int BOARDSIZE = 25;
    static final int PIECESIZE = 20;
    static final int MAXPIECENUM = BOARDSIZE * BOARDSIZE;

    public Piece Pieces[][];
    Graphics bufferGraphics;

    Image offscreen;
    Image blackImage;
    Image whiteImage;

    public JBanCo() {

        Pieces = new Piece[BOARDSIZE][BOARDSIZE];

        try {
            URL url = this.getClass().getResource("/image/cross.png");
            blackImage = ImageIO.read(url);
            url = this.getClass().getResource("/image/circle.png");
            whiteImage = ImageIO.read(url);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void Initialize() {
        for (int x = 0; x < BOARDSIZE; x++) {
            for (int y = 0; y < BOARDSIZE; y++) {
                Pieces[x][y] = new Piece();
            }
        }
    }

    public void init(int width, int height) {
        offscreen = createImage(width, height);
        bufferGraphics = offscreen.getGraphics();
    }

    public boolean GetPos(int x, int y, Pos pos) {
        if (x <= 0 || x >= PIECESIZE * BOARDSIZE) {
            return false;
        }
        if (y <= 0 || y >= PIECESIZE * BOARDSIZE) {
            return false;
        }
        pos.setX(x / PIECESIZE);
        pos.setY(y / PIECESIZE);
        return true;
    }

    public Pos autoGetPos() {
        Pos pos = new Pos();
        Random rand = new Random();
        int x = rand.nextInt(25);
        int y = rand.nextInt(25);
        while (Pieces[x][y].State != Piece.EMPTY) {
            x = rand.nextInt(25);
            y = rand.nextInt(25);
        }
        pos.setX(x);
        pos.setY(y);
        return pos;
    }

    public void Draw() {
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x, y;
        bufferGraphics.setColor(Color.white);
        bufferGraphics.clearRect(0, 0, offscreen.getWidth(this), offscreen.getHeight(this));
        bufferGraphics.setColor(Color.black);
        for (x = 0; x <= BOARDSIZE; x++) {
            bufferGraphics.drawLine(x * PIECESIZE, 0, x * PIECESIZE, BOARDSIZE * PIECESIZE);
        }
        for (y = 0; y <= BOARDSIZE; y++) {
            bufferGraphics.drawLine(0, y * PIECESIZE, BOARDSIZE * PIECESIZE, y * PIECESIZE);
        }

        for (x = 0; x < BOARDSIZE; x++) {
            for (y = 0; y < BOARDSIZE; y++) {
                switch (Pieces[x][y].State) {
                    case Piece.WHITE:

                        bufferGraphics.drawImage(whiteImage, x * PIECESIZE, y * PIECESIZE, this);
                        break;
                    case Piece.BLACK:

                        bufferGraphics.drawImage(blackImage, x * PIECESIZE, y * PIECESIZE, this);
                        break;
                }
            }
        }
        g.drawImage(offscreen, 0, 0, this);
    }
}
