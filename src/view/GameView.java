package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controller.GameController;
import view.DicePanel;
import view.BoardPanel;
import view.SlotSquare;
import view.PlayerPanel;
import controller.*;

public class GameView extends javax.swing.JFrame {

    GameController gameController;
    private BoardPanel gameBoard;
    private PlayerPanel gamePlayer;
    private JTextArea logText;
    private JScrollPane gameLog;
    private JButton startGameBut;
    private JButton nextRoundBut;
    private JButton rollDiceBut;
    private JButton payRentBut;
    private JButton buyBut;
    private DicePanel dice1;
    private DicePanel dice2;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public GameView() {
        //initComponents();
        setPreferredSize(new Dimension(1400, 750));
        setLayout(null);
        
        // Board
        gameBoard = new BoardPanel(10,20);
        gameBoard.setBackground(new Color(51, 255, 153));
        getContentPane().add(gameBoard);
        // Player Section
        gamePlayer = new PlayerPanel(950,10);
        getContentPane().add(gamePlayer);

        // Game log
        logText = new JTextArea();
	    logText.setFont(new Font("Arial", Font.PLAIN, 12));
        gameLog = new JScrollPane(logText);

        // manual adjest gamelog coordinate (todo automate)
        gameLog.setBounds(950,350,400,200);
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
        startGameBut.setBounds(1060,310,170,35);
        
        // Next Round Button          
        nextRoundBut = new JButton("Next Round");
        nextRoundBut.setFont(new Font("Arial", Font.PLAIN, 14));
        // todo nextRound function
        nextRoundBut.setBounds(1060,650,170,35);
        // Roll dice Button        
        rollDiceBut = new JButton("Roll Dice");
        rollDiceBut.setFont(new Font("Arial", Font.PLAIN, 14));
            // todo rollDice function
        rollDiceBut.setBounds(1060,550,170,35);
        // Pay rent Button    
        payRentBut = new JButton("Pay Rent");
        payRentBut.setFont(new Font("Arial", Font.PLAIN, 14));
            // todo Pay rent function
        payRentBut.setBounds(1170,600,170,35);
        // Buy slot Button
        buyBut = new JButton("Buy");
        buyBut.setFont(new Font("Arial", Font.PLAIN, 14));
            // todo buy slot function
        buyBut.setBounds(950,600,170,35);
        // Dice 1
        dice1 = new DicePanel(300,350,40,40);
        //Dice 2
        dice2 = new DicePanel(500,350,40,40);
        
        // add to main frame
        getContentPane().add(gameLog);
        getContentPane().add(startGameBut);
        getContentPane().add(nextRoundBut);
        getContentPane().add(rollDiceBut);
        getContentPane().add(buyBut);
        getContentPane().add(payRentBut);
        gameBoard.add(dice1);
        gameBoard.add(dice2);
        
        pack();
        
    }

    public void showMessage(String log){
        logText.append("> "+log+"\n");
    }

    public void showPropertyInfo(String message){
        JOptionPane.showMessageDialog(this, message);
    }


    private void bNextPlayerActionPerformed(java.awt.event.ActionEvent evt) {
        gameController.nextPlayer();
    }

    private void bRollDiceActionPerformed(java.awt.event.ActionEvent evt) {
        gameController.rollDice();
    }

    private void bBuyActionPerformed(java.awt.event.ActionEvent evt) {
        gameController.buyProperty();
    }


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
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
