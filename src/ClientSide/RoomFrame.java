/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientSide;

import Share.Message;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class RoomFrame extends javax.swing.JFrame implements ReceiveMessage {

    /**
     * Creates new form RoomFrame
     */
    Client client;

    public RoomFrame(Client client) {
        initComponents();
        this.client = client;
        this.client.receive = this;

        try {
            client.SendMessage(21, null); // lay bang room
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listRoom = new java.awt.List();
        Refresh_btn = new javax.swing.JButton();
        Join_btn = new javax.swing.JButton();
        View_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Refresh_btn.setText("Refresh");
        Refresh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Refresh_btnActionPerformed(evt);
            }
        });

        Join_btn.setText("Join room");
        Join_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Join_btnActionPerformed(evt);
            }
        });

        View_btn.setText("View");
        View_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                View_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(listRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Refresh_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(Join_btn)
                        .addGap(53, 53, 53)
                        .addComponent(View_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Refresh_btn)
                    .addComponent(Join_btn)
                    .addComponent(View_btn))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Join_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Join_btnActionPerformed
        // TODO add your handling code here:
        try {
            if (listRoom.getSelectedIndex() >= 0) {
                client.SendMessage(20, listRoom.getSelectedIndex());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_Join_btnActionPerformed

    private void Refresh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Refresh_btnActionPerformed
        // TODO add your handling code here:
        try {
            client.SendMessage(21, null);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_Refresh_btnActionPerformed

    private void View_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_View_btnActionPerformed
        // TODO add your handling code here:
        try {
            if (listRoom.getSelectedIndex() >= 0) {
                client.SendMessage(23, listRoom.getSelectedIndex());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_View_btnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Join_btn;
    private javax.swing.JButton Refresh_btn;
    private javax.swing.JButton View_btn;
    private java.awt.List listRoom;
    // End of variables declaration//GEN-END:variables

    @Override
    public void ReceiveMessage(Message msg) {
        switch (msg.getType()) {
            case 20: // get ban co
            {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new Main(client).setVisible(true);
                    }
                });
                this.dispose();
                break;
            }
            case 21: {
                listRoom.removeAll();
                int[] arrRoom = (int[]) msg.getObject();
                for (int i = 0; arrRoom != null && i < arrRoom.length; i++) {
                    listRoom.add("Room " + (i + 1) + ": " + arrRoom[i]);
                }
                listRoom.select(0);
                break;
            }
            case 22: { //Room nay da full
                listRoom.removeAll();
                int[] arrRoom = (int[]) msg.getObject();
                for (int i = 0; arrRoom != null && i < arrRoom.length; i++) {
                    listRoom.add("Room " + (i + 1) + ": " + arrRoom[i]);
                }
                listRoom.select(0);
                JOptionPane.showMessageDialog(null, "Room nay da full, lua chon room khac", "Error", 1);
                break;
            }
        }
    }

}
