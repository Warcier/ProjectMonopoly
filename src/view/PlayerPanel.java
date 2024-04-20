package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.util.List;
import java.util.stream.Collectors;


import controller.GameController;
import model.Property;
import model.Player;


public class PlayerPanel extends JLayeredPane{
    /**
     * @param playerName
     * @param playerColor
     * @param playerNum
     * @return
     */
    CardLayout card = new CardLayout(); 
    private static JPanel[] playerPanels = new JPanel[4];
    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private JPanel beforeStartPanel;
    GameController gameController;

    public PlayerPanel(int xCoord, int yCoord, int width, int height){
        this.gameController = gameController;
        // player panel for display player information
        setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(xCoord, yCoord, width, height);
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
		this.setLayout(card);

        beforeStartPanel = new JPanel();
        beforeStartPanel.setBackground(new Color(102,102,102));
        beforeStartPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel beforeStartLabel = new JLabel("<html><br><br><br><br><br><br>Welcome to the Monoploy !!!<br> There will be 4 player in th game. <br> Let get your frient to play together !!!</html>");
        beforeStartLabel.setForeground(Color.WHITE);
        beforeStartLabel.setFont(new Font("Arial", Font.BOLD, 14));

        beforeStartPanel.add(beforeStartLabel);
        this.add(beforeStartPanel);
        
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }



    // create the player information display panel
    private JPanel createPlayerPanel(int panelWidth,int panelHeight,String playerName,Color playerColor, int playerNum){
        // Create the panel
        JPanel playerPanel = new JPanel();
        playerPanel.setBackground(playerColor);
        playerPanel.setLayout(null);  

        // Create and configure the title label
        JLabel titleLabel = new JLabel(playerName);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 6, panelWidth, 20);
        playerPanel.add(titleLabel);

        // Create and configure the text area
        JTextArea playerInfoArea = new JTextArea();
        playerInfoArea.setBounds(10, 34, panelWidth-20, panelHeight-100);
        JScrollPane infoScrollPane = new JScrollPane(playerInfoArea);
        infoScrollPane.setBounds(10, 34, panelWidth - 20, panelHeight - 80);
        playerPanel.add(infoScrollPane, BorderLayout.CENTER);

        // Create and configure the player action label
        JLabel actionLabel = new JLabel("Player Action :");
        actionLabel.setForeground(Color.WHITE);
        actionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        actionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        actionLabel.setVerticalAlignment(SwingConstants.CENTER); 
        actionLabel.setBounds(10, panelHeight-45, panelWidth/3, 40);
        playerPanel.add(actionLabel);

        // Create and configure the title label
        JLabel playerTakeActionJLabel = new JLabel();
        playerTakeActionJLabel.setForeground(Color.WHITE);
        playerTakeActionJLabel.setFont(new Font("Arial", Font.BOLD, 18));
        playerTakeActionJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        playerTakeActionJLabel.setVerticalAlignment(SwingConstants.CENTER); 
        playerTakeActionJLabel.setBounds(20, panelHeight-45, panelWidth - panelWidth/3, 40);
        playerPanel.add(playerTakeActionJLabel);

        // Add the panel to the card layout with a unique identifier
        this.add(playerPanel, String.valueOf(playerNum));

        return playerPanel;
    }

    public void updatePlayerInfoArea(Player player){
        // update the player information in the player panel area
        if (player == null) {
            System.out.println("Error player panel (Invalid player) : " + player.getName());
            return;
        }
        JTextArea playerInfoArea = getjTextArea(player);
        // Display player current position
        //playerInfoArea.append("Position: "+ board.findPlayerNode(player).getSlot()+"\n");
        // Display player current Property
        playerInfoArea.append("Own Property: "+propertiesToString(player.getPlayerProperty())+"\n");
    }

    private static JTextArea getjTextArea(Player player) {
        int playerNum = 0;

        if ("Player 1".equals(player.getName())) {
            playerNum = 0;
        }else if ("Player 2".equals(player.getName())) {
            playerNum = 1;
        }else if ("Player 3".equals(player.getName())) {
            playerNum = 2;
        }else if ("Player 4".equals(player.getName())) {
            playerNum = 3;
        }

        JPanel currentPlayerPanel = playerPanels[playerNum];
        JScrollPane infoScrollPane = (JScrollPane) currentPlayerPanel.getComponent(1); // Assuming the JScrollPane is the second component
        JTextArea playerInfoArea = (JTextArea) infoScrollPane.getViewport().getView();
        // Display player current cash
        playerInfoArea.setText("Current Cash: "+ player.getCash()+"\n");
        return playerInfoArea;
    }

    private String propertiesToString(List<Property> playerProperties) {
        // convert list of properties to string
        if (playerProperties == null) {
            return "";
        } else {
            return playerProperties.stream()
                                   .map(Property::getLandName) // Transform Property to its land name
                                   .collect(Collectors.joining(", ")); // Join all names with a comma and a space
        }
    }


    public void changePlayerPanel(Player changePlayer){
        // change player information panel logic
        int playerNum = 0;

        if (changePlayer.getName() == "Player 1") {
            playerNum = 1;
        }else if (changePlayer.getName() == "Player 2") {
            playerNum = 2;
        }else if (changePlayer.getName() == "Player 3") {
            playerNum = 3;
        }else if (changePlayer.getName() == "Player 4") {
            playerNum = 4;
        }
        card.show(this, ""+(playerNum));
        updatePlayerInfoArea(changePlayer);
        GameView.showGameMessage("Change to "+changePlayer.getName()+" trun.");
    }

    public void startGame(){

        beforeStartPanel.setVisible(false);
        playerPanels[0] = createPlayerPanel(PANEL_WIDTH, PANEL_HEIGHT,"Player 1", Color.RED, 1);
        playerPanels[1] = createPlayerPanel(PANEL_WIDTH, PANEL_HEIGHT,"Player 2", Color.BLUE, 2);
        playerPanels[2] = createPlayerPanel(PANEL_WIDTH, PANEL_HEIGHT,"Player 3", new Color(0,102,0), 3);
        playerPanels[3] = createPlayerPanel(PANEL_WIDTH, PANEL_HEIGHT,"Player 4", new Color(255,102,0), 4);
        GameView.showGameMessage("Created 4 players");
        card.show(this, ""+1);

    }
    public static void setPlayerActionLabel(Player player, String action){
        int playerNum = 0;
        if (player.getName() == "Player 1") {
            playerNum = 1;
        }else if (player.getName() == "Player 2") {
            playerNum = 2;
        }else if (player.getName() == "Player 3") {
            playerNum = 3;
        }else if (player.getName() == "Player 4") {
            playerNum = 4;
        }
        JPanel currentPlayerPanel = playerPanels[playerNum-1];
        JLabel playerTakeAction = (JLabel) currentPlayerPanel.getComponent(3);
        playerTakeAction.setText(action);


    }
}
