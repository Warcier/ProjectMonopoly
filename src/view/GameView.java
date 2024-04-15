package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.GameController;
import model.Property;
import model.Player;
import java.util.List; 

public class GameView extends javax.swing.JFrame {

    GameController gameController;
    private BoardPanel gameBoard;
    private PlayerPanel gamePlayer;
    private JTextArea logText;
    private JScrollPane gameLog;
    private JButton startGameBut;
    private JButton nextPlayerBut;
    private JButton rollDiceBut;
    private JButton payRentBut;
    private JButton buyBut;
    private DicePanel dice1;
    private DicePanel dice2;

    private int diceNumber;
    private List<Property> properties;
    //private List<Property> currentPlayherProperties;
    private List<Player> players;
    private int currentPlayerNum = 1;
    private Player currentPlayer;
    private Player nextPlayer;
    
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public GameView() {
        //initComponents();
        setPreferredSize(new Dimension(1400, 750));
        setLayout(null);
        setGameController(new GameController());
        
        // Board
        gameBoard = new BoardPanel(10,20);
        gameBoard.setBackground(new Color(51, 255, 153));
        getContentPane().add(gameBoard);
        // Player Section
        gamePlayer = new PlayerPanel(950,10,400,280);
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
            // Start Game function
        startGameBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gameController.createBoard();
                gameController.updateViewPlayers();
                gamePlayer.startGame();
                gameBoard.initPlayerChess();
                showGameMessage("The Game has started");
                // update current player (player 1) info box
                currentPlayer = players.get(currentPlayerNum);
                gamePlayer.updatePlayerInfoArea(currentPlayer.getCash(), currentPlayer.getPlayerProperty(), currentPlayerNum);
                
                startGameBut.setEnabled(false);
                payRentBut.setEnabled(false);
                buyBut.setEnabled(false);
                nextPlayerBut.setEnabled(false);
            }});

        startGameBut.setFont(new Font("Arial", Font.PLAIN, 14));
        startGameBut.setBounds(1060,310,170,35);
        
        // Next Round Button          
        nextPlayerBut = new JButton("Next Player");
        nextPlayerBut.setFont(new Font("Arial", Font.PLAIN, 14));
        nextPlayerBut.setBounds(1060,650,170,35);
            //Next Round Button function
            // Function: change to next player panel
        nextPlayerBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gameController.checkWinCondition();
                nextPlayer = gameController.nextPlayer();
                gameController.updateViewPlayers();
                gamePlayer.changePlayerPanel(nextPlayer,gamePlayer.findPlayerIndexByName(nextPlayer.getName(), players));

                rollDiceBut.setEnabled(true);
                payRentBut.setEnabled(false);
                buyBut.setEnabled(false);
                nextPlayerBut.setEnabled(false);
            }});

        // Roll dice Button        
        rollDiceBut = new JButton("Roll Dice");
        rollDiceBut.setFont(new Font("Arial", Font.PLAIN, 14));
        rollDiceBut.setBounds(1060,550,170,35);
            //Roll dice Button function
            //TODO: change player chess location
        rollDiceBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gameController.rollDice();
                diceNumber = gameController.getDiceNum();

                if (diceNumber == 0) {
                    showGameMessage("ERROR in roll dice, please roll again");
                    rollDiceBut.setEnabled(true);
                    payRentBut.setEnabled(false);
                    buyBut.setEnabled(false);
                    nextPlayerBut.setEnabled(false);
                }else if (diceNumber <= 6) {
                    dice1.getDiceFace((int) Math.round( diceNumber/2));
                    dice2.getDiceFace(diceNumber - (int) Math.round( diceNumber/2));
                }else{
                    dice1.getDiceFace(6);
                    dice2.getDiceFace(diceNumber-6);
                }
                showGameMessage("Roll Dice : "+ diceNumber);
                
                rollDiceBut.setEnabled(false);
                payRentBut.setEnabled(true);
                buyBut.setEnabled(true);
                nextPlayerBut.setEnabled(true);
            }});

        // Pay rent Button    
        payRentBut = new JButton("Pay Rent");
        payRentBut.setFont(new Font("Arial", Font.PLAIN, 14));
            // todo Pay rent function
        payRentBut.setBounds(1170,600,170,35);
        // Buy slot Button
        buyBut = new JButton("Buy");
        buyBut.setFont(new Font("Arial", Font.PLAIN, 14));
        buyBut.setBounds(950,600,170,35);
        buyBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gameController.buyProperty();

                payRentBut.setEnabled(false);
                buyBut.setEnabled(false);
                nextPlayerBut.setEnabled(true);
            }});

        // Dice 1
        dice1 = new DicePanel(300,350,40,40);
        //Dice 2
        dice2 = new DicePanel(500,350,40,40);
        
        // add to main frame
        getContentPane().add(gameLog);
        getContentPane().add(startGameBut);
        getContentPane().add(nextPlayerBut);
        getContentPane().add(rollDiceBut);
        getContentPane().add(buyBut);
        getContentPane().add(payRentBut);
        gameBoard.add(dice1);
        gameBoard.add(dice2);
        
        pack();
        this.setVisible(true);
        
    }

    public void showGameMessage(String log){
        // show message in game log
        logText.append("> "+log+"\n");
    }

    public void updatePlayers(List<Player> players) {
        // update properties on the board
        this.players = players;
        //displayProperties();
    }

    public void showPropertyInfo(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    private void bBuyActionPerformed(java.awt.event.ActionEvent evt) {
        gameController.buyProperty();
    }




}
