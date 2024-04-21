package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List; 

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

    private Player currentPlayer;
    private Player nextPlayer;
    private boolean winCondition = false;
    private Player winner;
    private CircularLinkedList board;
    private List<Player> players;
    private List<Property> properties;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setViewBoard(CircularLinkedList board){
        this.board = board;
    }

    public void setGameBoardController(GameController controller){
        this.gameBoard.setGameController(controller);
    }

    public void setPlayerPanelController(GameController controller){
        this.gamePlayer.setGameController(controller);
    }

    public GameView() {

        setTitle("COM3101 Project Group 7 - Monoploy");
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
                // First setup the game board in the view
                gameController.createBoard();
                gameController.updateViewBoard();
                setGameBoardController(gameController);
                gamePlayer.startGame();
                gameBoard.initPlayerChess();
                gameBoard.initializeSquares();
                gameBoard.add(dice1);
                gameBoard.add(dice2);
                // get current player
                currentPlayer = gameController.getCurrentPlayerToView();
                showGameMessage("The Game has started");
                // update current player (player 1) info box
                setPlayerPanelController(gameController);
                gamePlayer.updatePlayerInfoArea(currentPlayer);
                // enable buttons
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
                //First check if next player is already bankrupted else skip
                do{
                    gameController.nextPlayer();
                    nextPlayer = gameController.getCurrentPlayer();
                }while(nextPlayer.isBankrupt());

                // update board to latest
                gameController.updateViewBoard();
                // track player status
                if (!currentPlayer.isBankrupt()) {
                    showGameMessage("\n"+currentPlayer.getName()+ "'s round end."+"\n"+
                    "-----Status------"+"\n"+
                    "Current Cash: " + currentPlayer.getCash()+"\n"+
                    "Status: "+ (currentPlayer.isBankrupt() ? "Bankrupt" : "Active") +"\n"+
                    "Own Property: "+(gamePlayer.propertiesToString(currentPlayer.getPlayerProperty()))+"\n"+
                    "Position: "+ gameController.findPlayerNode(currentPlayer).getProperty().getLandName()+"\n"+
                    "-----------------"+"\n"+
                    "Next Player: " + nextPlayer.getName()+"\n"+
                    "-----------------"+"\n");
                }else{showGameMessage("\n"+"-----------------"+"\n"+
                                    "Next Player: " + nextPlayer.getName()+"\n"+
                                    "-----------------"+"\n");
                }

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
        rollDiceBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                // roll dice button logic
                gameController.rollDice();
                int diceNumber = gameController.getDiceNum();
                if (diceNumber == 0) {
                    showGameMessage("ERROR in roll dice, please roll again");
                    rollDiceBut.setEnabled(true);
                    //buyBut.setEnabled(false);
                    nextPlayerBut.setEnabled(false);
                }else if (diceNumber == 1) {
                    dice1.getDiceFace(1);
                    dice2.setVisible(false);
                }
                else if (diceNumber <= 6) {
                    dice1.getDiceFace((int) Math.round( diceNumber/2));
                    dice2.setVisible(true);
                    dice2.getDiceFace(diceNumber - (int) Math.round( diceNumber/2));
                }else{
                    dice1.getDiceFace(6);
                    dice2.setVisible(true);
                    dice2.getDiceFace(diceNumber-6);
                }
                showGameMessage("Roll Dice : "+ diceNumber);

                // move player to next node in model
                //First check if player is already bankrupted else skip
                /**if (currentPlayer.isBankrupt()) {
                    showGameMessage("Player "+currentPlayer.getName()+" is bankrupt");
                    rollDiceBut.setEnabled(false);
                    nextPlayerBut.setEnabled(true);
                    return;

                } else {
                    gameController.moveCurrentPlayer(currentPlayer, diceNumber);                                    
                    // update board to latest
                    updateBoard();
                    // update player position in the view
                    gameBoard.movePlayerChess(currentPlayer);
                }*/
                gameController.moveCurrentPlayer(currentPlayer, diceNumber);                                    
                // update board to latest
                updateBoard();
                // update player position in the view
                gameBoard.movePlayerChess(currentPlayer);
                if (currentPlayer.getPassedGo()){
                    showGameMessage(currentPlayer.getName()+" has passed GO and get bonus");
                    addPlayerTakenAction(currentPlayer,"Passed GO and get $2000 bonus");
                    currentPlayer.setPassedGo(false);
                }
                // check if Buy or Rent after moving to new node
                Node node = gameController.findPlayerNode(currentPlayer);
                // if node owner not null pay rent to the owner
                if (node.getOwner() != null) {
                    showGameMessage("Player "+currentPlayer.getName()+" has to pay rent to "+node.getOwner().getName());
                    addPlayerTakenAction(currentPlayer,"Pay Rent to "+node.getOwner().getName());
                    gameController.payRent(currentPlayer, node);
                }
                // if node owner is null Buy the node
                if (node.getOwner() == null) {
                    showGameMessage("Player "+currentPlayer.getName()+" bought the property");
                    addPlayerTakenAction(currentPlayer,"Buy "+node.getProperty().getLandName());
                    gameController.buyProperty(currentPlayer);                   
                }
                // update current player info and current player in player panel
                setCurrentPlayer(gameController.getCurrentPlayer());
                // Check if the player is bankrupt after buy/pay rent
                if (currentPlayer.isBankrupt()) {
                    // if yes show message
                    showGameMessage(currentPlayer.getName()+" is bankrupt");
                    addPlayerTakenAction(currentPlayer, "Bankrupt ! ! !");
                    gameController.getBoard().removeBankruptPlayerProperties(currentPlayer);
                    gameBoard.removePlayerOnBoard(currentPlayer);
                    showGameMessage(currentPlayer.getName()+" is remove from the game");                 
                }else{
                    // if not update the textArea in playerPanel
                    gamePlayer.updatePlayerInfoArea(currentPlayer);
                }

                // check if game over
                Player winner = gameController.checkWinCondition();
                if (winner != null) {
                    winCondition = true;
                    nextPlayerBut.setEnabled(false);
                    rollDiceBut.setEnabled(false); 
                    winCondition(winner);
                }else{
                    gameController.updateViewBoard();
                    rollDiceBut.setEnabled(false);
                    nextPlayerBut.setEnabled(true); 
                }

                gameController.ShowAllPlayerNode();
            }});
            rollDiceBut.setEnabled(false);

        // Dice 1
        dice1 = new DicePanel(320,350,50,50);
        // dice1 click 
        dice1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameController.dice1Clicked();            }
        });
        //Dice 2
        dice2 = new DicePanel(520,350,50,50);
        dice2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameController.dice2Clicked();            }
        });

        // add to main frame
        getContentPane().add(gameLog);
        getContentPane().add(startGameBut);
        getContentPane().add(nextPlayerBut);
        getContentPane().add(rollDiceBut);
        //getContentPane().add(buyBut);
    
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

    public void updateBoard() {
        // update board on the view
        this.board = gameController.getBoard();
        this.players = gameController.getPlayers();
        this.properties = gameController.getProperties();
    }

    public void setCurrentPlayer(Player player){
        // update the current player of the round
        this.currentPlayer = player;    
    }

   private void winCondition(Player winner){
        // game over dialog
        if (winner != null) {

            JDialog gameOverDialog = new JDialog();
            gameOverDialog.setTitle("Game Over !!!");
            gameOverDialog.setSize(500, 400);
            gameOverDialog.setLocationRelativeTo(null);  // Centers the dialog over the parent frame
            gameOverDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            gameOverDialog.setLayout(new BorderLayout());
    
            JPanel gameOverPanel = new JPanel();
            gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
            gameOverPanel.setBackground(new Color(102, 102, 102));
    
            JLabel gameOverLabel = new JLabel("Game Over !!!");
            gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameOverLabel.setFont(new Font("Arial", Font.BOLD, 40));
            gameOverLabel.setForeground(Color.WHITE);
    
            JLabel winPlayerJLabel = new JLabel(winner.getName() + " has won the game");
            winPlayerJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            winPlayerJLabel.setFont(new Font("Arial", Font.BOLD, 20));
            winPlayerJLabel.setForeground(Color.WHITE);
    
            // Adding vertical glue before and after labels to center them vertically
            gameOverPanel.add(Box.createVerticalGlue());  // Adds space at the top
            gameOverPanel.add(gameOverLabel);
            gameOverPanel.add(winPlayerJLabel);
            gameOverPanel.add(Box.createVerticalGlue());  // Adds space at the bottom
    
            gameOverDialog.add(gameOverPanel, BorderLayout.CENTER);
            gameOverDialog.setVisible(true);
        }
   }
}
