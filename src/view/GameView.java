package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List; 
import java.util.OptionalInt;
import java.util.stream.IntStream;

import controller.GameController;
import model.Property;
import model.Player;
import model.list.*;


public class GameView extends javax.swing.JFrame {

    GameController gameController;
    private BoardPanel gameBoard;
    private PlayerPanel gamePlayer;
    private static JTextArea logText;
    private JScrollPane gameLog;
    private JButton startGameBut;
    private JButton nextPlayerBut;
    private JButton rollDiceBut;
    //private JButton buyBut;
    private DicePanel dice1;
    private DicePanel dice2;

    private CircularLinkedList board;
    private List<Property> properties;
    private List<Player> players;
    private List<Node> playersNode;
    private int diceNumber;
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
        gameLog.setBounds(950,370,400,200);
        gameLog.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Start Game Button
        startGameBut = new JButton("Start Game");
            // Start Game function
        startGameBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gameController.createBoard();
                gameController.updateViewBoard();
                gamePlayer.startGame();
                gameBoard.initPlayerChess();
                showGameMessage("The Game has started");
                // update current player (player 1) info box
                currentPlayer = players.get(currentPlayerNum-1);
                gamePlayer.updatePlayerInfoArea(currentPlayer.getCash(), currentPlayer.getPlayerProperty(), currentPlayerNum);
                
                startGameBut.setEnabled(false);
                //buyBut.setEnabled(false);
                nextPlayerBut.setEnabled(false);
            }});

        startGameBut.setFont(new Font("Arial", Font.PLAIN, 14));
        startGameBut.setBounds(1060,310,170,35);
        
        // Next Round Button          
        nextPlayerBut = new JButton("Next Player");
        nextPlayerBut.setFont(new Font("Arial", Font.PLAIN, 14));
        nextPlayerBut.setBounds(1060,630,200,35);
            //Next Round Button function
            // Function: change to next player panel
        nextPlayerBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gameController.checkWinCondition();
                nextPlayer = gameController.nextPlayer();
                gameController.updateViewBoard();
                gamePlayer.changePlayerPanel(nextPlayer,findPlayerIndexByName(nextPlayer.getName(), players));
                // set current next Player
                setCurrentPlayer(nextPlayer);

                rollDiceBut.setEnabled(true);
                //buyBut.setEnabled(false);
                nextPlayerBut.setEnabled(false);
            }});

        // Roll dice Button        
        rollDiceBut = new JButton("Roll Dice");
        rollDiceBut.setFont(new Font("Arial", Font.PLAIN, 14));
        rollDiceBut.setBounds(1060,580,200,35);
            //Roll dice Button function
            //TODO: change player chess location
        rollDiceBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gameController.rollDice();
                gameController.moveCurrentPlayer();
                // set players node
                setPlayersNode();
                diceNumber = gameController.getDiceNum();

                if (diceNumber == 0) {
                    showGameMessage("ERROR in roll dice, please roll again");
                    rollDiceBut.setEnabled(true);
                    //buyBut.setEnabled(false);
                    nextPlayerBut.setEnabled(false);
                }else if (diceNumber <= 6) {
                    dice1.getDiceFace((int) Math.round( diceNumber/2));
                    dice2.getDiceFace(diceNumber - (int) Math.round( diceNumber/2));
                }else{
                    dice1.getDiceFace(6);
                    dice2.getDiceFace(diceNumber-6);
                }
                showGameMessage("Roll Dice : "+ diceNumber);
                board.ShowAllPlayerPostion();
                // update chess position with the updated node list
                gameBoard.updateChessPos(playersNode);
                
                rollDiceBut.setEnabled(false);
                //buyBut.setEnabled(true);
                nextPlayerBut.setEnabled(true);
                gameController.updateViewBoard();
            }});
        // Buy slot Button
        /**buyBut = new JButton("Buy");
        buyBut.setFont(new Font("Arial", Font.PLAIN, 14));
        buyBut.setBounds(950,600,170,35);
        buyBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gameController.buyProperty();
                buyBut.setEnabled(false);
                nextPlayerBut.setEnabled(true);
            }});*/

        // Dice 1
        dice1 = new DicePanel(300,350,40,40);
        //Dice 2
        dice2 = new DicePanel(500,350,40,40);
        
        // add to main frame
        getContentPane().add(gameLog);
        getContentPane().add(startGameBut);
        getContentPane().add(nextPlayerBut);
        getContentPane().add(rollDiceBut);
        //getContentPane().add(buyBut);
        gameBoard.add(dice1);
        gameBoard.add(dice2);
        
        pack();
        this.setVisible(true);
        
    }

    public static void showGameMessage(String log){
        // show message in game log
        logText.append("> "+log+"\n");
    }

    public void updateBoard(CircularLinkedList board) {
        // update board on the view
        this.board = board;
        this.players = board.getPlayers();
        this.properties = board.getProperties();
        setPlayersNode();

    }

    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;    
    }

    public void setPlayersNode(){
        List<Node> playersNode = gameController.getPlayersNode();
        this.playersNode = playersNode;
        
    }

    // find player index with player name
    public int findPlayerIndexByName(String playerName,List<Player> players) {
        OptionalInt indexOpt = IntStream.range(0, players.size())
                                        .filter(i -> playerName.equals(players.get(i).getName()))
                                        .findFirst();

        return indexOpt.orElse(-1);
    }

    private void bBuyActionPerformed(java.awt.event.ActionEvent evt) {
        gameController.buyProperty();
    }




}
