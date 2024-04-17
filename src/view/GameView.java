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
    private static PlayerPanel gamePlayer;
    private static JTextArea logText;
    private JScrollPane gameLog;
    private JButton startGameBut;
    private JButton nextPlayerBut;
    private JButton rollDiceBut;
    //private JButton buyBut;
    private DicePanel dice1;
    private DicePanel dice2;

    private static CircularLinkedList board;
    private List<Property> properties;
    private List<Player> players;
    private List<Node> playersNode;
    private int diceNumber;
    private Player currentPlayer;
    private Player nextPlayer;
    private boolean winCondition = false;
    
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
                // Start Game Logic
                gameController.createBoard();
                gameController.updateViewBoard();
                gamePlayer.startGame();
                gameBoard.initPlayerChess();
                currentPlayer = gameController.getCurrentPlayerToView();

                showGameMessage("The Game has started");
                // update current player (player 1) info box
                gamePlayer.updatePlayerInfoArea(currentPlayer);
                
                startGameBut.setEnabled(false);
                rollDiceBut.setEnabled(true);
                nextPlayerBut.setEnabled(false);
            }});

        startGameBut.setFont(new Font("Arial", Font.PLAIN, 14));
        startGameBut.setBounds(1060,310,200,35);
        
        // Next Round Button          
        nextPlayerBut = new JButton("Next Player");
        nextPlayerBut.setFont(new Font("Arial", Font.PLAIN, 14));
        nextPlayerBut.setBounds(1060,630,200,35);
            //Next Round Button function
            // Function: change to next player panel
        nextPlayerBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                // set next player to model
                nextPlayer = gameController.nextPlayer();
                // update board to latest
                gameController.updateViewBoard();
                // change player panel
                gamePlayer.changePlayerPanel(nextPlayer);
                // set player for next round
                setCurrentPlayer(nextPlayer);

                rollDiceBut.setEnabled(true);
                //buyBut.setEnabled(false);
                nextPlayerBut.setEnabled(false);
            }});

            nextPlayerBut.setEnabled(false);

        // Roll dice Button        
        rollDiceBut = new JButton("Roll Dice");
        rollDiceBut.setFont(new Font("Arial", Font.PLAIN, 14));
        rollDiceBut.setBounds(1060,580,200,35);
            //Roll dice Button function
            //TODO: change player chess location
        rollDiceBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                // roll dice button logic
                gameController.rollDice();
                gameController.moveCurrentPlayer();
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

                // update chess position with the updated node list
                // update board to latest
                gameController.updateViewBoard();
                gameBoard.movePlayerChess(currentPlayer);
                
                rollDiceBut.setEnabled(false);
                //buyBut.setEnabled(true);
                nextPlayerBut.setEnabled(true);
                // check if any one win
                checkWinCondition();
                //update the board if no one win
                if (!winCondition) {
                    gameController.updateViewBoard();    
                }                
            }});

            rollDiceBut.setEnabled(false);
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
        dice1 = new DicePanel(320,350,50,50);
        //Dice 2
        dice2 = new DicePanel(520,350,50,50);
        
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

    public static void addPlayerTakenAction(Player player, String action){
        // show player action that have taken by program
        gamePlayer.setPlayerActionLabel(player, action);

    }

    public void updateBoard(CircularLinkedList board) {
        // update board on the view
        this.board = board;
        this.players = board.getPlayers();
        this.properties = board.getProperties();
        setPlayersNode();

    }

    public void setCurrentPlayer(Player player){
        // update the current player of the round
        this.currentPlayer = player;    
    }

    public void setPlayersNode(){
        // update the player node list to get least player location
        List<Node> playersNode = gameController.getPlayersNode();
        this.playersNode = playersNode;
        
    }

    public static CircularLinkedList getboardList(){
        // transfer board to other Panel
        if (board == null) {
            return null;
        }
        return board;
   }

   private void checkWinCondition(){
        if (gameController.checkWinCondition()) {
            winCondition = true;
            nextPlayerBut.setEnabled(false);

            JDialog gameOverDialog = new JDialog();
            gameOverDialog.setTitle("Game Over !!!");
            gameOverDialog.setSize(500, 400);
            gameOverDialog.setLocationRelativeTo(null);
            gameOverDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            gameOverDialog.setLayout(new BorderLayout());

            JPanel gameOverPanel = new JPanel();
            gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
            gameOverPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameOverPanel.setBackground(new Color(102,102,102));
    
            JLabel gameOverLabel = new JLabel("Game Over !!!");
            gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameOverLabel.setFont(new Font("Arial", Font.BOLD, 18));
            gameOverLabel.setForeground(Color.WHITE);
    
            JLabel winPlayerJLabel = new JLabel(currentPlayer.getName()+" has win the game");    
            winPlayerJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            winPlayerJLabel.setFont(new Font("Arial", Font.BOLD, 18));
            winPlayerJLabel.setForeground(Color.WHITE);

            // Pushes all items to the center
            gameOverPanel.add(gameOverLabel);
            gameOverPanel.add(winPlayerJLabel);
            gameOverDialog.add(gameOverPanel, BorderLayout.CENTER);
            gameOverDialog.setVisible(true);
        
        }
   }
}
