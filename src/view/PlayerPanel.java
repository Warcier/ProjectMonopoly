package view;

import java.awt.*;
import javax.swing.*;

public class PlayerPanel extends JPanel{
          // var todo 
    /*
    String playerName, playerBalance, playerLand?
    GameController control
    Player players[]
    */
        // function todo 
    /*
    add players to model (link with control) 
    get player 1,2,3,4 info from model
    set players label text
    change player balance every turn
    */
    
    private JButton addPlayer1;
    private JButton addPlayer1Name;
    private JButton addPlayer2;
    private JButton addPlayer2Name;
    private JButton addPlayer3;
    private JButton addPlayer3Name;
    private JButton addPlayer4;
    private JButton addPlayer4Name;
    private JLabel player1Balan;
    private JPanel player1Desc;
    private JLabel player1Name;
    private JTextField player1NameField;
    private JLayeredPane player1Panel;
    private JLabel player2Balan;
    private JPanel player2Desc;
    private JLabel player2Name;
    private JTextField player2NameField;
    private JLayeredPane player2Panel;
    private JLabel player3Balan;
    private JPanel player3Desc;
    private JLabel player3Name;
    private JTextField player3NameField;
    private JLayeredPane player3Panel;
    private JLabel player4Balan;
    private JPanel player4Desc;
    private JLabel player4Name;
    private JTextField player4NameField;
    private JLayeredPane player4Panel;


    public PlayerPanel(int xCoord,int yCoord) {
        initComponents();        
        // Set the preferred size of the container panel to ensure it's big enough
        //setPreferredSize(new Dimension(400, 300));
        setBounds(xCoord, yCoord, 400, 300);
        
        
        // add player button unvisible
        addPlayer1Name.setVisible(false);
        addPlayer2Name.setVisible(false);
        addPlayer3Name.setVisible(false);
        addPlayer4Name.setVisible(false);
        // player name text field unvisible
        player1NameField.setVisible(false);
        player2NameField.setVisible(false);
        player3NameField.setVisible(false);
        player4NameField.setVisible(false);
        // player info description panel unvisible
        player1Desc.setVisible(false);
        player2Desc.setVisible(false);
        player3Desc.setVisible(false);
        player4Desc.setVisible(false);
        // Set the bounds for each player panel
        player1Panel.setBounds(0, 0, 200, 150); // Top left corner
        player2Panel.setBounds(200, 0, 200, 150); // Top right corner
        player3Panel.setBounds(0, 150, 200, 150); // Bottom left corner
        player4Panel.setBounds(200, 150, 200, 150); // Bottom right corner
        // Set the bounds for each player info description panel
        player1Desc.setBounds(0, 0, 200, 150); 
        player2Desc.setBounds(200, 0, 200, 150); 
        player3Desc.setBounds(0, 150, 200, 150); 
        player4Desc.setBounds(200, 150, 200, 150); 

        
        
    }//end con
    
    // disable add player after start game button press
    public void startGame() {
        addPlayer1.setEnabled(false);
        addPlayer2.setEnabled(false);
        addPlayer3.setEnabled(false);
        addPlayer4.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        player3Panel = new JLayeredPane();
        addPlayer3 = new JButton();
        player3NameField = new JTextField();
        addPlayer3Name = new JButton();
        player3Desc = new JPanel();
        player3Name = new JLabel();
        player3Balan = new JLabel();
        player2Panel = new JLayeredPane();
        addPlayer2 = new JButton();
        player2NameField = new JTextField();
        addPlayer2Name = new JButton();
        player2Desc = new JPanel();
        player2Name = new JLabel();
        player2Balan = new JLabel();
        player1Panel = new JLayeredPane();
        addPlayer1 = new JButton();
        player1NameField = new JTextField();
        addPlayer1Name = new JButton();
        player1Desc = new JPanel();
        player1Name = new JLabel();
        player1Balan = new JLabel();
        player4Panel = new JLayeredPane();
        addPlayer4 = new JButton();
        player4NameField = new JTextField();
        addPlayer4Name = new JButton();
        player4Desc = new JPanel();
        player4Name = new JLabel();
        player4Balan = new JLabel();

        setLayout(null);

        player3Panel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        player3Panel.setPreferredSize(new java.awt.Dimension(200, 150));

        addPlayer3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addPlayer3.setText("Add Player");
        addPlayer3.setAlignmentX(0.5F);
        addPlayer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayer3ActionPerformed(evt);
            }
        });

        player3NameField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        player3NameField.setText("jTextField1");

        addPlayer3Name.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addPlayer3Name.setText("Add Player's Name");
        addPlayer3Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayer3NameActionPerformed(evt);
            }
        });

        player3Desc.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        player3Name.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        player3Name.setText("Player 3 Name");

        player3Balan.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        player3Balan.setText("Player 3 Balance:");

        GroupLayout player3DescLayout = new GroupLayout(player3Desc);
        player3Desc.setLayout(player3DescLayout);
        player3DescLayout.setHorizontalGroup(
            player3DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player3DescLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(player3DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(player3Balan, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(player3Name, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addContainerGap())
        );
        player3DescLayout.setVerticalGroup(
            player3DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player3DescLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player3Name, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player3Balan, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        player3Panel.setLayer(addPlayer3, JLayeredPane.DEFAULT_LAYER);
        player3Panel.setLayer(player3NameField, JLayeredPane.DEFAULT_LAYER);
        player3Panel.setLayer(addPlayer3Name, JLayeredPane.DEFAULT_LAYER);
        player3Panel.setLayer(player3Desc, JLayeredPane.PALETTE_LAYER);

        GroupLayout player3PanelLayout = new GroupLayout(player3Panel);
        player3Panel.setLayout(player3PanelLayout);
        player3PanelLayout.setHorizontalGroup(
            player3PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player3PanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(player3PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(player3PanelLayout.createSequentialGroup()
                        .addComponent(addPlayer3Name)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(player3PanelLayout.createSequentialGroup()
                        .addComponent(player3NameField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(player3PanelLayout.createSequentialGroup()
                        .addComponent(addPlayer3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))))
            .addGroup(player3PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(player3Desc, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        player3PanelLayout.setVerticalGroup(
            player3PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player3PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player3NameField, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addPlayer3, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addPlayer3Name, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(player3PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(player3Desc, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(player3Panel);
        player3Panel.setBounds(0, 150, 200, 150);

        player2Panel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        player2Panel.setPreferredSize(new java.awt.Dimension(200, 150));

        addPlayer2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addPlayer2.setText("Add Player");
        addPlayer2.setAlignmentX(0.5F);
        addPlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayer2ActionPerformed(evt);
            }
        });

        player2NameField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        player2NameField.setText("jTextField1");

        addPlayer2Name.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addPlayer2Name.setText("Add Player's Name");
        addPlayer2Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayer2NameActionPerformed(evt);
            }
        });

        player2Desc.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        player2Desc.setPreferredSize(new java.awt.Dimension(200, 150));

        player2Name.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        player2Name.setText("Player 2 Name");

        player2Balan.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        player2Balan.setText("Player 2 Balance:");

        GroupLayout player2DescLayout = new GroupLayout(player2Desc);
        player2Desc.setLayout(player2DescLayout);
        player2DescLayout.setHorizontalGroup(
            player2DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player2DescLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(player2DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(player2Balan, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(player2Name, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addContainerGap())
        );
        player2DescLayout.setVerticalGroup(
            player2DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player2DescLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2Name, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player2Balan, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        player2Panel.setLayer(addPlayer2, JLayeredPane.DEFAULT_LAYER);
        player2Panel.setLayer(player2NameField, JLayeredPane.DEFAULT_LAYER);
        player2Panel.setLayer(addPlayer2Name, JLayeredPane.DEFAULT_LAYER);
        player2Panel.setLayer(player2Desc, JLayeredPane.PALETTE_LAYER);

        GroupLayout player2PanelLayout = new GroupLayout(player2Panel);
        player2Panel.setLayout(player2PanelLayout);
        player2PanelLayout.setHorizontalGroup(
            player2PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player2PanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(player2PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(player2PanelLayout.createSequentialGroup()
                        .addComponent(addPlayer2Name)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(player2PanelLayout.createSequentialGroup()
                        .addComponent(player2NameField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(player2PanelLayout.createSequentialGroup()
                        .addComponent(addPlayer2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))))
            .addGroup(player2PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(player2Desc, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
        );
        player2PanelLayout.setVerticalGroup(
            player2PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2NameField, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addPlayer2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addPlayer2Name, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(player2PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(player2Desc, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
        );

        add(player2Panel);
        player2Panel.setBounds(200, 0, 200, 150);

        player1Panel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addPlayer1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addPlayer1.setText("Add Player");
        addPlayer1.setAlignmentX(0.5F);
        addPlayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayer1ActionPerformed(evt);
            }
        });

        player1NameField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        player1NameField.setText("jTextField1");

        addPlayer1Name.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addPlayer1Name.setText("Add Player's Name");
        addPlayer1Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayer1NameActionPerformed(evt);
            }
        });

        player1Desc.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        player1Name.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        player1Name.setText("Player 1 Name");

        player1Balan.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        player1Balan.setText("Player 1 Balance:");

        GroupLayout player1DescLayout = new GroupLayout(player1Desc);
        player1Desc.setLayout(player1DescLayout);
        player1DescLayout.setHorizontalGroup(
            player1DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player1DescLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(player1DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(player1Balan, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(player1Name, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        player1DescLayout.setVerticalGroup(
            player1DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player1DescLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1Name, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player1Balan, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        player1Panel.setLayer(addPlayer1, JLayeredPane.DEFAULT_LAYER);
        player1Panel.setLayer(player1NameField, JLayeredPane.DEFAULT_LAYER);
        player1Panel.setLayer(addPlayer1Name, JLayeredPane.DEFAULT_LAYER);
        player1Panel.setLayer(player1Desc, JLayeredPane.PALETTE_LAYER);

        GroupLayout player1PanelLayout = new GroupLayout(player1Panel);
        player1Panel.setLayout(player1PanelLayout);
        player1PanelLayout.setHorizontalGroup(
            player1PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player1PanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(player1PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(player1PanelLayout.createSequentialGroup()
                        .addComponent(addPlayer1Name)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(player1PanelLayout.createSequentialGroup()
                        .addComponent(player1NameField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(player1PanelLayout.createSequentialGroup()
                        .addComponent(addPlayer1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))))
            .addGroup(player1PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(player1Desc, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        player1PanelLayout.setVerticalGroup(
            player1PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1NameField, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addPlayer1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addPlayer1Name, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(player1PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(player1Desc, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(player1Panel);
        player1Panel.setBounds(0, 1, 200, 150);

        player4Panel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        player4Panel.setPreferredSize(new java.awt.Dimension(200, 150));

        addPlayer4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addPlayer4.setText("Add Player");
        addPlayer4.setAlignmentX(0.5F);
        addPlayer4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayer4ActionPerformed(evt);
            }
        });

        player4NameField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        player4NameField.setText("jTextField1");

        addPlayer4Name.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addPlayer4Name.setText("Add Player's Name");
        addPlayer4Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayer4NameActionPerformed(evt);
            }
        });

        player4Desc.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        player4Name.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        player4Name.setText("Player 4 Name");

        player4Balan.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        player4Balan.setText("Player 4 Balance:");

        GroupLayout player4DescLayout = new GroupLayout(player4Desc);
        player4Desc.setLayout(player4DescLayout);
        player4DescLayout.setHorizontalGroup(
            player4DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player4DescLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(player4DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(player4Balan, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(player4Name, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addContainerGap())
        );
        player4DescLayout.setVerticalGroup(
            player4DescLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player4DescLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player4Name, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player4Balan, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        player4Panel.setLayer(addPlayer4, JLayeredPane.DEFAULT_LAYER);
        player4Panel.setLayer(player4NameField, JLayeredPane.DEFAULT_LAYER);
        player4Panel.setLayer(addPlayer4Name, JLayeredPane.DEFAULT_LAYER);
        player4Panel.setLayer(player4Desc, JLayeredPane.PALETTE_LAYER);

        GroupLayout player4PanelLayout = new GroupLayout(player4Panel);
        player4Panel.setLayout(player4PanelLayout);
        player4PanelLayout.setHorizontalGroup(
            player4PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player4PanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(player4PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(player4PanelLayout.createSequentialGroup()
                        .addComponent(addPlayer4Name)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(player4PanelLayout.createSequentialGroup()
                        .addComponent(player4NameField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(player4PanelLayout.createSequentialGroup()
                        .addComponent(addPlayer4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))))
            .addGroup(player4PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(player4Desc, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        player4PanelLayout.setVerticalGroup(
            player4PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(player4PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player4NameField, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addPlayer4, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addPlayer4Name, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(player4PanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(player4Desc, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(player4Panel);
        player4Panel.setBounds(200, 150, 200, 150);
    }// </editor-fold>//GEN-END:initComponents

    private void addPlayer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayer1ActionPerformed
        // TODO add your handling code here:
        addPlayer1.setVisible(false);
        addPlayer1Name.setVisible(true);
        player1NameField.setVisible(true);
    }//GEN-LAST:event_addPlayer1ActionPerformed

    private void addPlayer1NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayer1NameActionPerformed
    // add player 1 to model
    // TODO add your handling code here:
    // try get player 1 name -> show message add player 1 in game log
        player1Desc.setVisible(true);
    }//GEN-LAST:event_addPlayer1NameActionPerformed

    private void addPlayer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayer2ActionPerformed
        // TODO add your handling code here:
        addPlayer2.setVisible(false);
        addPlayer2Name.setVisible(true);
        player2NameField.setVisible(true);
    }//GEN-LAST:event_addPlayer2ActionPerformed

    private void addPlayer2NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayer2NameActionPerformed
    // add player 2 to model
    // TODO add your handling code here:
    // try get player 2 name
        player2Desc.setVisible(true);
    }//GEN-LAST:event_addPlayer2NameActionPerformed

    private void addPlayer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayer3ActionPerformed
        // TODO add your handling code here:
        addPlayer3.setVisible(false);
        addPlayer3Name.setVisible(true);
        player3NameField.setVisible(true);
    }//GEN-LAST:event_addPlayer3ActionPerformed

    private void addPlayer3NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayer3NameActionPerformed
    // add player 3 to model
    // TODO add your handling code here:
    // try get player 3 name
        player3Desc.setVisible(true);
    }//GEN-LAST:event_addPlayer3NameActionPerformed

    private void addPlayer4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayer4ActionPerformed
        // TODO add your handling code here:
        addPlayer4.setVisible(false);
        addPlayer4Name.setVisible(true);
        player4NameField.setVisible(true);
    }//GEN-LAST:event_addPlayer4ActionPerformed

    private void addPlayer4NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayer4NameActionPerformed
    // add player 4 to model
    // TODO add your handling code here:
    // try get player 4 name
        player4Desc.setVisible(true);
    }//GEN-LAST:event_addPlayer4NameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}


