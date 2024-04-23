package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import controller.GameController;
import model.list.*;
import model.*;

import java.util.List;
import view.SlotSquare;


public class BoardPanel extends javax.swing.JLayeredPane {

    /**
     * Creates new form BoardPanel
     */
    // set required var
    GameController gameController;
    private JLabel[] playerChess = new JLabel[4];
    private static final int PANEL_WIDTH = 896;
    private static final int PANEL_HEIGHT = 693;
    private static final int SQUARE_COUNT = 23;
    private static final int SQUARES_PER_SIDE = 7;
    int squareWidth;
    int squareHeight;
    SlotSquare[] squares = new SlotSquare[SQUARE_COUNT];
    private int[] squareXCoord = new int[SQUARE_COUNT];
    private int[] squareYCoord = new int[SQUARE_COUNT];
    private int[] rotationAngles = {135,180,180,180,180,180,-135,
                                        -90,-90,-90,-90,-90,-45,
                                        0,0,0,0,0,45,
                                        90,90,90,90,90};
    

    public BoardPanel(int xCoord, int yCoord) {
        this.gameController = gameController;
        // create monoploy game board
        setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(xCoord, yCoord, PANEL_WIDTH, PANEL_HEIGHT);
        setOpaque(true);
        this.setLayout(null);
        //setSlotTexts();
        
        squareWidth = (int) Math.round(PANEL_WIDTH /SQUARES_PER_SIDE);
        squareHeight = (int) Math.round(PANEL_HEIGHT /SQUARES_PER_SIDE);
 
        // add slot square to board
        initializeMonoLabel(200,220);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void initializeSquares() {
        // creating slots on the game board
        for (int slotNum = 0; slotNum < SQUARE_COUNT; slotNum++) {
            int xCoordSquare = 0, yCoordSquare = 0;
            // Top row
            if (slotNum < 7) { 
                xCoordSquare = slotNum * squareWidth;
            }
            // Right column
            else if (slotNum < 12) { 
                xCoordSquare = 6 * squareWidth;
                yCoordSquare = (slotNum - 6) * squareHeight;
            } 
            // Bottom row
            else if (slotNum < 19) { 
                xCoordSquare = (6 - (slotNum - 12)) * squareWidth;
                yCoordSquare = 6 * squareHeight;
            } 
            // Left column
            else { 
                xCoordSquare = 0;
                squareHeight = (int) Math.round(PANEL_HEIGHT / 5);
                yCoordSquare = ((5-(slotNum - 18)) * squareHeight)-65;
            }
            // Store coordinates in arrays
            squareXCoord[slotNum] = xCoordSquare;
            squareYCoord[slotNum] = yCoordSquare;

            squares[slotNum] = new SlotSquare(xCoordSquare, yCoordSquare, squareWidth, squareHeight, slotNum, rotationAngles[slotNum], gameController);
            this.add(squares[slotNum],PALETTE_LAYER);
            this.validate();
            this.repaint();
        }
    }

    private void initializeMonoLabel(int xCoordLabel, int yCoordLabel){
        // create monoploy label on the middle of the board
        JLabel lblMonopoly = new JLabel("MONOPOLY"){
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                super.paintComponent(g);
            }
		};
		lblMonopoly.setForeground(Color.WHITE);
		lblMonopoly.setBackground(Color.RED);
		lblMonopoly.setOpaque(true);
		lblMonopoly.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonopoly.setFont(new Font("Lucida Grande", Font.BOLD, 60));
		lblMonopoly.setBounds(xCoordLabel, yCoordLabel, 500, 80);
		this.add(lblMonopoly);

    }

    public JLabel createPlayerChess(int xCoordChess, int yCoordChess,int playerNum, Color color) {
        // function to create player on the game board    
		JLabel playerChess = new JLabel(""+playerNum);
        playerChess.setBorder(new LineBorder(new Color(0,0,0)));
		playerChess.setFont(new Font("Arial", Font.BOLD, 15));
		playerChess.setForeground(Color.WHITE);
        playerChess.setBackground(color);
        playerChess.setOpaque(true);
        playerChess.setHorizontalAlignment(SwingConstants.CENTER);
        playerChess.setBounds(xCoordChess,yCoordChess,20,20);
		return playerChess;
	}

    public void initPlayerChess(){     
        // create player on game board
        playerChess[0] = createPlayerChess(squareXCoord[0]+10,squareYCoord[0]+5,1, Color.RED);
        playerChess[1] = createPlayerChess(squareXCoord[0]+10,squareYCoord[0]+25,2, Color.BLUE);
        playerChess[2] = createPlayerChess(squareXCoord[0]+10,squareYCoord[0]+45,3, new Color(0,102,0));
        playerChess[3] = createPlayerChess(squareXCoord[0]+10,squareYCoord[0]+65,4, new Color(255,102,0));

        this.add(playerChess[0],JLayeredPane.MODAL_LAYER);
        this.add(playerChess[1],JLayeredPane.MODAL_LAYER);
        this.add(playerChess[2],JLayeredPane.MODAL_LAYER);
        this.add(playerChess[3],JLayeredPane.MODAL_LAYER);
        GameView.showGameMessage("Add Player to the monoploy board.");
    }

    public void movePlayerChess(Player player) {
        // Move player chess Position
        if (player == null) {
            System.out.println(" Error Player not found.");
            return;
        }
        int playerNum = 0;
        switch (player.getName()) {
            case "Player 1": playerNum = 0; break;
            case "Player 2": playerNum = 1; break;
            case "Player 3": playerNum = 2; break;
            case "Player 4": playerNum = 3; break;
            default:
                System.out.println("Invalid Player");
                return;
        }
        
        Node playerNode = gameController.findPlayerNode(player);
        if (playerNode == null) {
            System.out.println("Player not found in the board");
            return;
        }
        // get players node slot num [player location]
        int slotNum  = playerNode.getSlot();
        if (slotNum < 0 ) {
            System.out.println("Invalid square number .");
            return;
        }

        // Calculate new positions for the chess based on the player number to avoid overlap
        int xOffset = 10 + (playerNum % 2) * 15; 
        int yOffset = 5 + (playerNum / 2) * 20; 
    
        // Set the new bounds for the player's chess label
        playerChess[playerNum].setBounds(squareXCoord[slotNum] + xOffset, squareYCoord[slotNum] + yOffset, 20, 20);
        // repaint the slot to avoid slot name be cover
        this.setComponentZOrder(playerChess[playerNum], 0); 
        this.repaint(); 
    }

    public void removePlayerOnBoard(Player bankruptPlayer){
        // Remove player from the board
        int playerNum = 0;
        switch (bankruptPlayer.getName()) {
            case "Player 1": playerNum = 0; break;
            case "Player 2": playerNum = 1; break;
            case "Player 3": playerNum = 2; break;
            case "Player 4": playerNum = 3; break;
            default:
                System.out.println("Invalid Player");
                return;
        }
        playerChess[playerNum].setVisible(false);
        this.validate();
        this.repaint();
    }

    public void addPlayerOnBoard(Player addPlayer){
        // add player back to board
        int playerNum = 0;
        switch (addPlayer.getName()) {
            case "Player 1": playerNum = 0; break;
            case "Player 2": playerNum = 1; break;
            case "Player 3": playerNum = 2; break;
            case "Player 4": playerNum = 3; break;
            default:
                System.out.println("Invalid Player");
                return;
        }if (!playerChess[playerNum].isVisible()) {
            playerChess[playerNum].setVisible(true);
            this.setComponentZOrder(playerChess[playerNum], 0); 
            this.revalidate();
            this.repaint();
        }
    }
}