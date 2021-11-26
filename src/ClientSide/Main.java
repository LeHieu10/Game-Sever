/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide;

import Share.Broad;
import Share.Message;
import Share.Piece;
import Share.Pos;
import Share.Users;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author ASUS
 */
public class Main extends javax.swing.JFrame implements ReceiveMessage {

    /**
     * Creates new form Main
     */
    int sec = 0, timeTurn = -1, timeMax = 100;
    int timeThink, check = 1;
    Timer think;
    Timer timer;
    public Client client;
    JBanCo Goban;
    JScrollPane jScroll;
    int GameState = 0;
    static final int WAIT = 0;
    static final int MY_TURN = 1;
    static final int YOU_WIN = 2;
    static final int YOU_LOSE = 3;
    static final int Dul = 4;
    ArrayList<Piece[][]> repeat;
//    Main(){
//        initComponents();
//        setTitle("Game Caro");
//        setLayout(new BorderLayout());
//        panelCaro.setSize(507, 507);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(1000, 750);
//        txtChat.requestFocus();
//        TimeGame();
//        Thinking();
//    }

    public Main(Client client) {
        initComponents();
        setTitle("Game Caro");
        setLayout(new BorderLayout());
        panelCaro.setSize(507, 507);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 750);
        this.client = client;
        this.client.receive = this;
        InitGame();

        lbName1.setText("Name: " + client.user.getName());
        lbScore1.setText("Score: " + client.user.getScore());
        txtChat.requestFocus();
        NewGame_btn.setVisible(true);
        Repeat.setVisible(false);
        try {
            client.SendMessage(28, null); //Lay thong tin 2 ng
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void InitGame() {
        repeat = new ArrayList<>();
        Goban = new JBanCo();
        Goban.setPreferredSize(new Dimension(500, 500));
        panelCaro.setViewportView(Goban);
        Goban.init(501, 501);
        Goban.Initialize();
        Goban.Draw();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.        
    }

    void putStatus(String strStt) {
        lbStatus.setText(strStt);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCaro = new javax.swing.JScrollPane();
        lbName1 = new javax.swing.JLabel();
        lbScore1 = new javax.swing.JLabel();
        lbName2 = new javax.swing.JLabel();
        lbScore2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        historyChat = new javax.swing.JTextArea();
        txtChat = new javax.swing.JTextField();
        Send_btn = new javax.swing.JButton();
        lbStatus = new javax.swing.JLabel();
        Exit_btn = new javax.swing.JButton();
        NewGame_btn = new javax.swing.JButton();
        Repeat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        Timelb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelCaro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelCaroMouseClicked(evt);
            }
        });

        lbName1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName1.setText("PLAYER 1:");

        lbScore1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbScore1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbScore1.setText("SCORE:");

        lbName2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName2.setText("PLAYER 2:");

        lbScore2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbScore2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbScore2.setText("SCORE: ");

        historyChat.setColumns(20);
        historyChat.setRows(5);
        jScrollPane2.setViewportView(historyChat);

        Send_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Send_btn.setText("Send");
        Send_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Send_btnMouseClicked(evt);
            }
        });
        Send_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send_btnActionPerformed(evt);
            }
        });

        lbStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbStatus.setText("Doi nguoi choi  khac");

        Exit_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Exit_btn.setText("Exit");
        Exit_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Exit_btnMouseClicked(evt);
            }
        });

        NewGame_btn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        NewGame_btn.setText("New Game");
        NewGame_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGame_btnActionPerformed(evt);
            }
        });

        Repeat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Repeat.setText("Repeat");
        Repeat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RepeatActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GAME CARO");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("INFORMATION");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(" VS");

        jLabel4.setBackground(new java.awt.Color(153, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Thinking");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jProgressBar1.setForeground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setAutoscrolls(true);
        jProgressBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Timelb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Timelb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Timelb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(NewGame_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                    .addComponent(panelCaro)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(Repeat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Exit_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lbName1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbScore2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbName2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtChat, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbScore1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lbName1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbScore1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbName2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(lbScore2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtChat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(Exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Repeat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Timelb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(panelCaro, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NewGame_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Send_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Send_btnMouseClicked

        String strMess = client.user.getName() + ": " + txtChat.getText();
        if (historyChat.getText().isEmpty()) {
            historyChat.setText(strMess);
        } else {
            historyChat.setText(historyChat.getText() + '\n' + strMess);
        }
        txtChat.setText("");
        try {
            client.SendMessage(40, strMess);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_Send_btnMouseClicked

    private void Exit_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit_btnMouseClicked
        // TODO add your handling code here:
        try {
            client.SendMessage(39, null);
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new RoomFrame(client).setVisible(true);
                }
            });
            this.dispose();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_Exit_btnMouseClicked

    private void panelCaroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCaroMouseClicked
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        System.out.println(x + ": " + y);
        if (GameState == MY_TURN) {
            Pos pos = new Pos();
            int offetX = Goban.getX();
            int offetY = Goban.getY();
            if (!Goban.GetPos(x - offetX, y - offetY, pos)) {
                return;
            }
            if (Goban.Pieces[pos.getX()][pos.getY()].State == Piece.EMPTY) {
                try {
                    GameState = WAIT;
                    putStatus("Doi...");
                    check = 0;
                    client.SendMessage(30, pos);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            } else {
                putStatus("Ban khong duoc danh vao vung nay!");
            }
        }
    }//GEN-LAST:event_panelCaroMouseClicked

//    public static void main(String[] args) {
//        new Main().setVisible(true);
//    }
    private void Send_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Send_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Send_btnActionPerformed

    private void NewGame_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewGame_btnActionPerformed
        try {
            // TODO add your handling code here:
            stratTimeGame();
            client.SendMessage(43, null);
            client.SendMessage(38, null);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_NewGame_btnActionPerformed

    private void RepeatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RepeatActionPerformed
        try {
            client.SendMessage(44, null);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_RepeatActionPerformed
    
    // thoi gian ca van dau
    private void stratTimeGame() {
        sec = 600; // 600s = 10'
        String value = sec / 60 + " : " + sec % 60;
        Timelb.setText(value);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                sec--;
                String value = "";
                if (sec % 60 < 10) {
                    value = "0" + sec / 60 + " : 0" + sec % 60;
                } else {
                    value = "0" + sec / 60 + " : " + sec % 60;
                }
                Timelb.setText(value);
                if (sec == 0) {
                    GameState = Dul; // Dul = hoa
                    lbStatus.setText("Het thoi gian");
                    NewGame_btn.setVisible(true);
                    Repeat.setVisible(true);
                    jProgressBar1.setValue(0);
                    timer.cancel();
                    think.cancel();
                    JOptionPane.showMessageDialog(null, "TIME'S UP");
                }
            }
        }, 0, 1000);
    }
    // thoi gian 1 luot danh
    private void Thinking() {
        think = new Timer();
        timeThink = timeMax; // timemax = 10s
        jProgressBar1.setValue(timeThink);
        think.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                jProgressBar1.setValue(--timeThink);
                if (check == 0) {
                    timeTurn = timeMax - jProgressBar1.getValue();
                    jProgressBar1.setValue(0);
                    think.cancel();
                    return;
                }
                if (timeThink == 0 && GameState == MY_TURN) {
                    try {
                        timeTurn = timeMax;
                        Pos pos = Goban.autoGetPos(); // autoGetPos la may tu danh
                        GameState = WAIT;
                        jProgressBar1.setValue(0);
                        think.cancel(); // huy timer
                        client.SendMessage(30, pos);
                        return;
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        }, 0, 100);
    }
    
    private void saveRepeat(Piece[][] p){
        if(timeTurn > -1){ 
            Broad broad = new Broad(p, timeTurn);
            try {
                client.SendMessage(42, broad);
            } catch (IOException ex) {
                System.out.println(ex);
            }
            timeTurn = -1;
        }
    }
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exit_btn;
    private javax.swing.JButton NewGame_btn;
    private javax.swing.JButton Repeat;
    private javax.swing.JButton Send_btn;
    private javax.swing.JLabel Timelb;
    private javax.swing.JTextArea historyChat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbName1;
    private javax.swing.JLabel lbName2;
    private javax.swing.JLabel lbScore1;
    private javax.swing.JLabel lbScore2;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JScrollPane panelCaro;
    private javax.swing.JTextField txtChat;
    // End of variables declaration//GEN-END:variables

    @Override
    public void ReceiveMessage(Message msg) {
        switch (msg.getType()) {
            case 30: // get ban co
            {
                Goban.Pieces = (Piece[][]) msg.getObject();
                saveRepeat(Goban.Pieces);
                Goban.Draw();
                break;
            }
            case 31: {
                check = 1;
                Thinking();
                putStatus("Den luot ban");
                GameState = MY_TURN;
                NewGame_btn.setVisible(false);
                Repeat.setVisible(false);
                break;
            }
            case 32: {
                putStatus("Doi thu da thoat");
                GameState = WAIT;
                break;
            }
            case 33: {
                Users user = (Users) msg.getObject();
                if (lbName1.getText().equals("Name: " + user.getName())) {
                    lbName1.setText(lbName2.getText());
                    lbScore1.setText(lbScore2.getText());

                }
                lbName2.setText("Name: ");
                lbScore2.setText("Score: ");
                break;
            }
            case 34: // Thong tin 2 ng choi
            {
                Users[] arrUser = (Users[]) msg.getObject();
                if (arrUser[0] != null && arrUser[1] != null) {
                    lbName1.setText("Name: " + arrUser[0].getName());
                    lbScore1.setText("Score: " + arrUser[0].getScore());
                    lbName2.setText("Name: " + arrUser[1].getName());
                    lbScore2.setText("Score: " + arrUser[1].getScore());
                }
                break;
            }
            case 35: {
                timer.cancel();
                if ("win".equalsIgnoreCase(msg.getObject().toString())) {
                    GameState = YOU_WIN;
                    putStatus("Ban da thang");
                    client.user.setScore(client.user.getScore() + 100);
                    JOptionPane.showMessageDialog(null, "You Win");
                    NewGame_btn.setVisible(true);
                    Repeat.setVisible(true);
                } else if ("lose".equalsIgnoreCase(msg.getObject().toString())) {
                    GameState = YOU_LOSE;
                    putStatus("Ban da thua");
                    client.user.setScore(client.user.getScore() - 100);
                    JOptionPane.showMessageDialog(null, "You Lose");
                    NewGame_btn.setVisible(true);
                    Repeat.setVisible(true);
                }
                break;
            }
            case 36: {
                putStatus("Den luot doi thu");
                NewGame_btn.setVisible(false);
                Repeat.setVisible(false);
                break;
            }
            case 37: {
                putStatus("Ban dang o che do view");
                break;
            }
            case 40: // Recieve Message
            {
                String strMess = msg.getObject().toString();
                if (historyChat.getText().isEmpty()) {
                    historyChat.setText(strMess);
                } else {
                    historyChat.setText(historyChat.getText() + '\n' + strMess);
                }
                break;
            }
            case 41: {
                repeat.removeAll(repeat);
                break;
            }
            case 43: {
                stratTimeGame();
                break;
            }
            case 44:
            {
                List<Broad> list = (List<Broad>) msg.getObject();
                new Repeat(list).setVisible(true);
                break;
            }
        }
    }

}