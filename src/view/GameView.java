package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

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
    private JButton tradeBut;
    private JButton buyBut;
    private DicePanel dice1;
    private DicePanel dice2;

    private Player currentPlayer;
    private Player nextPlayer;
    private boolean winCondition = false;
    private Player winner;
    private CircularLinkedList board;


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
        logText.setLineWrap(true);
        logText.setWrapStyleWord(true);
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
            }});

        startGameBut.setFont(new Font("Arial", Font.PLAIN, 14));
        startGameBut.setBounds(1060,310,200,35);

        // Trade Button
        tradeBut = new JButton("Trade Property");
        tradeBut.setFont(new Font("Arial", Font.PLAIN, 14));
        tradeBut.setBounds(950,590,190,35);
        tradeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Trade Logic
                initTradeDialog();
            }});

        // Roll dice Button
        rollDiceBut = new JButton("Roll Dice");
        rollDiceBut.setFont(new Font("Arial", Font.PLAIN, 14));
        rollDiceBut.setBounds(1160,590,190,35);
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
                gameController.moveCurrentPlayer(currentPlayer, diceNumber);

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
                    if (node.getOwner().getName().equals(currentPlayer.getName())) {
                        showGameMessage(currentPlayer.getName()+" has land on his own property (" + node.getProperty().getLandName()+")");
                        addPlayerTakenAction(currentPlayer,"Land on Own Property");
                    }else{
                        showGameMessage(currentPlayer.getName() + " has to pay rent to " + node.getOwner().getName());
                        addPlayerTakenAction(currentPlayer, "Pay Rent to " + node.getOwner().getName());
                        gameController.payRent(currentPlayer, node);
                    }
                }
                // if node owner is null Buy the node
                /**if (node.getOwner() == null) {
                    showGameMessage(currentPlayer.getName()+" bought the property");
                    addPlayerTakenAction(currentPlayer,"Buy "+node.getProperty().getLandName());
                    gameController.buyProperty(currentPlayer);
                }*/
                // update current player info and current player in player panel
                setCurrentPlayer(gameController.getCurrentPlayer());
                // Check if the player is bankrupt after buy/pay rent
                if (currentPlayer.isBankrupt()) {
                    // if yes show message
                    playerBankrupt(currentPlayer);
                }
                gamePlayer.updatePlayerInfoArea(currentPlayer);

                // check if game over
                Player winner = gameController.checkWinCondition();
                if (winner != null) {
                    winCondition = true;
                    nextPlayerBut.setEnabled(false);
                    rollDiceBut.setEnabled(false);
                    winCondition(winner);
                }else{
                    rollDiceBut.setEnabled(false);
                    nextPlayerBut.setEnabled(true);
                }

                //gameController.ShowAllPlayerNode();
            }});


        // Next Round Button
        nextPlayerBut = new JButton("Next Player");
        nextPlayerBut.setFont(new Font("Arial", Font.PLAIN, 14));
        nextPlayerBut.setBounds(1160,635,190,35);
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

                // track player status
                trackPlayerMessage(currentPlayer);
                // change player panel
                gamePlayer.changePlayerPanel(nextPlayer);
                // set player for next round
                setCurrentPlayer(nextPlayer);

                rollDiceBut.setEnabled(true);
                buyBut.setEnabled(false);
                nextPlayerBut.setEnabled(false);
            }});

         // Buy Button
        buyBut = new JButton("Buy");
        buyBut.setFont(new Font("Arial", Font.PLAIN, 14));
        buyBut.setBounds(950,635,190,35);
        //Buy Button function
        buyBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Buy logic
                Node node = gameController.findPlayerNode(currentPlayer);
                // if node owner is null Buy the node
                if (node.getOwner() == null) {
                    showGameMessage(currentPlayer.getName()+" bought the property");
                    addPlayerTakenAction(currentPlayer,"Buy "+node.getProperty().getLandName());
                    gameController.buyProperty(currentPlayer);
                }
            }});



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

        // set buttons to not enable at the start
        nextPlayerBut.setEnabled(false);
        rollDiceBut.setEnabled(false);
        buyBut.setEnabled(false);
        tradeBut.setEnabled(false);

        // add to main frame
        getContentPane().add(gameLog);
        getContentPane().add(startGameBut);
        getContentPane().add(tradeBut);
        getContentPane().add(rollDiceBut);
        getContentPane().add(nextPlayerBut);
        getContentPane().add(buyBut);

        pack();
        this.setVisible(true);
    }


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


    public static void showGameMessage(String log){
        // show message in game log
        logText.append("> "+log+"\n");
    }

    public static void addPlayerTakenAction(Player player, String action){
        // show player action that have taken by program
        gamePlayer.setPlayerActionLabel(player, action);

    }

    public void setCurrentPlayer(Player player){
        // update the current player of the round
        this.currentPlayer = player;    
    }

    private void playerBankrupt(Player player){
        // player bankrupt logic
        showGameMessage(player.getName()+" is bankrupt");
        addPlayerTakenAction(player, "Bankrupt ! ! !");
        gameController.getBoard().removeBankruptPlayerProperties(currentPlayer);
        gameBoard.removePlayerOnBoard(player);
        showGameMessage(player.getName()+" is remove from the game");  
    }

    private void trackPlayerMessage(Player player){
        // Check if the player is not bankrupt
        if (!player.isBankrupt()) {
            StringBuilder message = new StringBuilder();
            message.append(player.getName() + "'s round end." + "\n\n" +
                           "-----" + player.getName() + " Status------" + "\n" +
                           "Current Cash: " + player.getCash() + "\n" +
                           "Status: " + (player.isBankrupt() ? "Bankrupt" : "Active") + "\n");
            String properties = player.getPlayerProperty() != null ? gamePlayer.propertiesToString(player.getPlayerProperty()) : "None";
            message.append("Own Property: " + properties + "\n");
            message.append("Position: " + gameController.findPlayerNode(player).getProperty().getLandName() + "\n" +
                           "-----------------" + "\n" +
                           "Next Player: " + nextPlayer.getName() + "\n" +
                           "-----------------" + "\n");
            showGameMessage(message.toString());
        } else {
            showGameMessage("\n" + "-----------------" + "\n" +
                            "Next Player: " + nextPlayer.getName() + "\n" +
                            "-----------------" + "\n");
        }
    }

   private void winCondition(Player winner){
        // game over dialog
        if (winner != null) {
            winCondition = true;
            this.nextPlayerBut.setEnabled(false);
            this.rollDiceBut.setEnabled(false);

            JDialog gameOverDialog = new JDialog();
            gameOverDialog.setTitle("Game Over !!!");
            gameOverDialog.setSize(550, 400);
            gameOverDialog.setLocationRelativeTo(null);  
            gameOverDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            gameOverDialog.setLayout(new BorderLayout());
            gameOverDialog.setModalityType(Dialog.ModalityType.MODELESS);
    
            JPanel gameOverPanel = new JPanel();
            gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
            gameOverPanel.setBackground(new Color(102, 102, 102));
    
            JLabel gameOverLabel = new JLabel("Game Over !!!");
            gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            gameOverLabel.setFont(new Font("Arial", Font.BOLD, 40));
            gameOverLabel.setForeground(Color.WHITE);
    
            JLabel winPlayerJLabel = new JLabel(winner.getName() + " has won the game ! ! !");
            winPlayerJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            winPlayerJLabel.setFont(new Font("Arial", Font.BOLD, 30));
            winPlayerJLabel.setForeground(Color.WHITE);
    
            // Adding vertical glue before and after labels to center them vertically
            gameOverPanel.add(Box.createVerticalGlue());  // Adds space at the top
            gameOverPanel.add(gameOverLabel);
            gameOverPanel.add(Box.createVerticalStrut(30));
            gameOverPanel.add(winPlayerJLabel);
            gameOverPanel.add(Box.createVerticalGlue());  // Adds space at the bottom
    
            gameOverDialog.add(gameOverPanel, BorderLayout.CENTER);
            gameOverDialog.setVisible(true);
        }
   }

    private void initTradeDialog(){
        JDialog tradeDialog = new JDialog();
        tradeDialog.setTitle("Trading Property between player");
        tradeDialog.setSize(200, 100);
        tradeDialog.setLocationRelativeTo(null);  
        tradeDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        tradeDialog.setLayout(null);
        tradeDialog.setModalityType(Dialog.ModalityType.MODELESS);

        JLabel tradePlayerLabel = new JLabel("Player :");
        tradePlayerLabel.setForeground(Color.BLACK);
        tradePlayerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        //tradePlayerLabel.setHorizontalAlignment(JLabel.RIGHT);
        tradePlayerLabel.setBounds(20, 20, 20, 30);
        
        String[] player = {"Player 1","Player 2","Player 3", "Player 4"};
        JComboBox tradePlayerBox = new JComboBox<>(player);
        tradePlayerBox.setBounds(50, 20, 20, 30);

        JLabel statusPlayerLabel = new JLabel("Player :");
        statusPlayerLabel.setForeground(Color.BLACK);
        statusPlayerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        statusPlayerLabel.setHorizontalAlignment(JLabel.RIGHT);
        statusPlayerLabel.setBounds(80, 20, 20, 30);

        List<Property> property = gameController.getBoard().getProperties();
        List<String> propertyNames = property.stream().map(Property::getLandName).collect(Collectors.toList());
        JComboBox tradePropertyBox = new JComboBox<>(propertyNames.toArray(new String[0]));
        tradePropertyBox.setBounds(100, 20, 20, 30);

        JButton buyTradeBut = new JButton("Buy");
        buyTradeBut.setFont(new Font("Arial", Font.PLAIN, 14));
        buyTradeBut.setBounds(950,580,200,35);
        buyTradeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Buy player properties Logic
                Player seller = gameController.getBoard().findPlayer(tradePlayerBox.getSelectedItem().toString());
                Node slot = gameController.getBoard().getSlot(tradePropertyBox.getSelectedIndex());
                
            }});

        JButton sellTradeBut = new JButton("Sell");
        sellTradeBut.setFont(new Font("Arial", Font.PLAIN, 14));
        sellTradeBut.setBounds(950,580,200,35);
        sellTradeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sell properties to player Logic
                Player buyer = gameController.getBoard().findPlayer(tradePlayerBox.getSelectedItem().toString());
                Node slot = gameController.getBoard().getSlot(tradePropertyBox.getSelectedIndex());
            }});

    }

   public void updatePlayerInfo(Player player){
    // update the playerInfo showing on the panel (editor)
    if (player != null && player.getName().equals(currentPlayer.getName())) {
        gamePlayer.updatePlayerInfoArea(player);
        }
    }

    public void changeCurrentPlayer(Player player){
        // update the playerInfo showing on the panel (editor)
        if (player != null) {
            setCurrentPlayer(player);
            gamePlayer.changePlayerPanel(player);
        }
    }

    public void changeStatus(Player player){
        // change player status view logic (editor)
        if (player.isBankrupt()) {
            playerBankrupt(player);
            // check game over
            Player winner = gameController.checkWinCondition();
            if (winner != null) {
                winCondition(winner);
            }else{
                if (player.getName().equals(currentPlayer.getName())) {
                    do{
                        gameController.nextPlayer();
                        nextPlayer = gameController.getCurrentPlayer();
                    }while(nextPlayer.isBankrupt());
                    // change player panel
                    gamePlayer.changePlayerPanel(nextPlayer);
                    // set player for next round
                    setCurrentPlayer(nextPlayer);
                }}
        }else{
            gameBoard.addPlayerOnBoard(player);
            gamePlayer.setPlayerActionLabel(player, "");
            updatePlayerInfo(player);
        }
    }

    public void movePlayerLoca(Player player){
        gameBoard.movePlayerChess(player);
    }
}
