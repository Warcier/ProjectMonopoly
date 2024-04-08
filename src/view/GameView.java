package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import view.DicePanel;
import view.BoardPanel;
import view.SlotSquare;
import view.PlayerPanel;

public class GameView extends javax.swing.JFrame {
    
    private BoardPanel gameBoard;
    private PlayerPanel gamePlayer;
    private JTextArea logText;
    private JScrollPane gameLog;
    private JButton startGameBut;
    private JButton nextRoundBut;
    private JButton rollDiceBut;
    private JButton payRentBut;
    private JButton soldBut;
    private JButton buyBut;
    private DicePanel dice1;
    private DicePanel dice2;

    public GameView() {
        //initComponents();
        setPreferredSize(new Dimension(1240, 850));
        setLayout(null);
        
        // Board
        gameBoard = new BoardPanel(2,2);
        getContentPane().add(gameBoard);
        // Player Section
        gamePlayer = new PlayerPanel(820,2);
        getContentPane().add(gamePlayer);

        // Game log
        logText = new JTextArea();
	    logText.setFont(new Font("Arial", Font.PLAIN, 12));
        gameLog = new JScrollPane(logText);
        // manual adjest gamelog coordinate (todo automate)
        gameLog.setBounds(820,350,400,200);
        gameLog.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Start Game Button
        startGameBut = new JButton("Start Game");
        startGameBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gamePlayer.startGame();
                showMessage("the Game has started");
                startGameBut.setEnabled(false);
            }});

        startGameBut.setFont(new Font("Arial", Font.PLAIN, 14));
        startGameBut.setBounds(920,310,170,35);
        
        // Next Round Button          
        nextRoundBut = new JButton("Next Round");
        nextRoundBut.setFont(new Font("Arial", Font.PLAIN, 14));
        // todo nextRound function
        nextRoundBut.setBounds(920,750,170,35);
        // Roll dice Button        
        rollDiceBut = new JButton("Roll Dice");
        rollDiceBut.setFont(new Font("Arial", Font.PLAIN, 14));
            // todo rollDice function
        rollDiceBut.setBounds(920,650,170,35);
        // Pay rent Button    
        payRentBut = new JButton("Pay Rent");
        payRentBut.setFont(new Font("Arial", Font.PLAIN, 14));
            // todo Pay rent function
        payRentBut.setBounds(1090,700,130,35);
        // Buy slot Button
        buyBut = new JButton("Buy");
        buyBut.setFont(new Font("Arial", Font.PLAIN, 14));
            // todo buy slot function
        buyBut.setBounds(820,700,130,35);
       // Sold slot Button
        soldBut = new JButton("Sold");
        soldBut.setFont(new Font("Arial", Font.PLAIN, 14));
            // todo sold slot function
        soldBut.setBounds(955,700,130,35);
        // Dice 1
        dice1 = new DicePanel(300,350,80,80);
        //Dice 2
        dice2 = new DicePanel(500,350,80,80);
        
        // add to main frame
        getContentPane().add(gameLog);
        getContentPane().add(startGameBut);
        getContentPane().add(nextRoundBut);
        getContentPane().add(rollDiceBut);
        getContentPane().add(buyBut);
        getContentPane().add(soldBut);
        getContentPane().add(payRentBut);
        gameBoard.add(dice1);
        gameBoard.add(dice2);
        
        pack();
        
    }

    public void showMessage(String log){
        logText.append("> "+log+"\n");
    }


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameView().setVisible(true);
            }
        });
    }
}
