package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import model.Property;
import model.Player;
import view.GameView;


public class PlayerPanel extends JLayeredPane{
    /**
     * @param playerName
     * @param playerColor
     * @param playerNum
     * @return
     */
    CardLayout card = new CardLayout(); 
    private JPanel[] playerPanels = new JPanel[4];
    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private JPanel beforeStartPanel;
    private List<Property> playerProperties;
    private List<Player> players;
    

    public PlayerPanel(int xCoord, int yCoord, int width, int height){
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
        playerInfoArea.setBounds(10, 34, panelWidth-20, panelHeight-50);
        JScrollPane infoScrollPane = new JScrollPane(playerInfoArea);
        infoScrollPane.setBounds(10, 34, panelWidth - 20, panelHeight - 50);
        playerPanel.add(infoScrollPane, BorderLayout.CENTER);

        // Add the panel to the card layout with a unique identifier
        this.add(playerPanel, String.valueOf(playerNum));

        return playerPanel;
    }

    public void updatePlayerInfoArea(int cash, List<Property> currentPlayerProperty, int playerNum){
        if (playerNum < 1 || playerNum > playerPanels.length) {
            System.out.println("Error player panel : Invalid player number: " + playerNum);
            return;
        }
        this.playerProperties = currentPlayerProperty;
        JPanel currentPlayerPanel = playerPanels[playerNum-1];
        JScrollPane infoScrollPane = (JScrollPane) currentPlayerPanel.getComponent(1); // Assuming the JScrollPane is the second component
        JTextArea playerInfoArea = (JTextArea) infoScrollPane.getViewport().getView();
        // Display player current cash
        playerInfoArea.setText("Current Cash: "+cash+"\n");
        // Display player current Property

        playerInfoArea.append("Own Property: "+propertiesToString()+"\n");
    }

    private String propertiesToString() {
        // convert list of properties to string
        if (playerProperties == null) {
            return "";
        } else {
            return playerProperties.stream()
                                   .map(Property::getLandName) // Transform Property to its land name
                                   .collect(Collectors.joining(", ")); // Join all names with a comma and a space
        }
    }

    // find player index with player name
    public int findPlayerIndexByName(String playerName,List<Player> players) {
        OptionalInt indexOpt = IntStream.range(0, players.size())
                                        .filter(i -> playerName.equals(players.get(i).getName()))
                                        .findFirst();

        return indexOpt.orElse(-1);
    }

    public void changePlayerPanel(Player changePlayer,int playerNum){
        card.show(this, ""+(playerNum+1));
        updatePlayerInfoArea(changePlayer.getCash(), changePlayer.getPlayerProperty(), playerNum+1);
        GameView.showGameMessage("Change to "+changePlayer.getName()+" trun.");
    }

    public void startGame(){

        beforeStartPanel.setVisible(false);
        playerPanels[0] = createPlayerPanel(PANEL_WIDTH, PANEL_HEIGHT,"Player 1", Color.RED, 1);
        playerPanels[1] = createPlayerPanel(PANEL_WIDTH, PANEL_HEIGHT,"Player 2", Color.BLUE, 2);
        playerPanels[2] = createPlayerPanel(PANEL_WIDTH, PANEL_HEIGHT,"Player 3", new Color(0,102,0), 3);
        playerPanels[3] = createPlayerPanel(PANEL_WIDTH, PANEL_HEIGHT,"Player 4", new Color(255,102,0), 4);
        card.show(this, ""+1);

    }

}
