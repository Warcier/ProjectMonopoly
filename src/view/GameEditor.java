package view;

import javax.sound.sampled.Line;
import java.util.stream.Collectors;
import java.util.List; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import controller.GameController;
import model.*;

public class GameEditor extends JFrame{
    private GameController controller;

    private final int PANEL_WIDTH = 400;
    private final int PANEL_HEIGHT = 800;
    private JPanel editorJPanel;

    public GameEditor(GameController controller) {
        this.controller = controller;
        initializeUI();
    }

    public void initializeUI(){
        setTitle("Game Editor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);
        String[] player = {"Player 1","Player 2","Player 3", "Player 4"};
        String[] status = {"Active","Bankrupt"};
        List<Property> property = controller.getBoard().getProperties();
        List<String> propertyNames = property.stream().map(Property::getLandName).collect(Collectors.toList());
        int yCoord = 0;
        int TITLE_XCOORD = 15;
        int LABEL_XCOORD = 30;
        Font btnFont = new Font("Lucida Grande", Font.PLAIN, 14);
        Font titleFont = new Font("Arial", Font.BOLD, 19);
        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        editorJPanel = new JPanel();
        editorJPanel.setBackground(new Color(204,204,204));
        editorJPanel.setLayout(null);
        editorJPanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        // change slot owner
        JLabel changeOwnerTitle = new JLabel("Change Slot Owner");
        changeOwnerTitle.setForeground(Color.BLACK);
        changeOwnerTitle.setFont(titleFont);
        changeOwnerTitle.setBounds(TITLE_XCOORD, yCoord+=10, PANEL_WIDTH-50, 30); 
        //changeOwnerTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel ownerSlotLabel = new JLabel("Slot :");
        ownerSlotLabel.setForeground(Color.BLACK);
        ownerSlotLabel.setFont(labelFont);
        //ownerSlotLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        ownerSlotLabel.setHorizontalAlignment(JLabel.RIGHT);
        ownerSlotLabel.setBounds(LABEL_XCOORD, yCoord+=40, (PANEL_WIDTH/2)-50, 35); 

        JComboBox ownerSlotBox = new JComboBox<>(propertyNames.toArray(new String[0]));
        ownerSlotBox.setBounds(200, yCoord, PANEL_WIDTH/2-40, 30);

        JLabel ownerPlayerLabel = new JLabel("Player :");
        ownerPlayerLabel.setForeground(Color.BLACK);
        ownerPlayerLabel.setFont(labelFont);
        ownerPlayerLabel.setHorizontalAlignment(JLabel.RIGHT);
        ownerPlayerLabel.setBounds(LABEL_XCOORD, yCoord+=35, (PANEL_WIDTH/2)-50, 30); 
        
        JComboBox ownerPlayerBox = new JComboBox<>(player);
        ownerPlayerBox.setBounds(200, yCoord, PANEL_WIDTH/2-40, 30);

        JButton changeOwnerBut = new JButton("Change Owner");
        changeOwnerBut.setFont(btnFont);
        changeOwnerBut.setBounds(100, yCoord+=40, 200, 35); 
        changeOwnerBut.setHorizontalAlignment(SwingConstants.CENTER);
        changeOwnerBut.addActionListener(new ActionListener() {
			@Override
            // TODO: changeOwnerBut logic
			public void actionPerformed(ActionEvent e) {
                Player player = controller.getBoard().findPlayer(ownerPlayerBox.getSelectedItem().toString());
                int slot = ownerSlotBox.getSelectedIndex();
                controller.changeOwnerBut(slot, player);

            }});

        // give cash
        JLabel giveCashTitle = new JLabel("Give Money To Player");
        giveCashTitle.setForeground(Color.BLACK);
        giveCashTitle.setFont(titleFont);
        giveCashTitle.setBounds(TITLE_XCOORD, yCoord+=40, PANEL_WIDTH-50, 30); 
        //giveCashTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel cashPlayerLabel = new JLabel("Player :");
        cashPlayerLabel.setForeground(Color.BLACK);
        cashPlayerLabel.setFont(labelFont);
        cashPlayerLabel.setHorizontalAlignment(JLabel.RIGHT);
        cashPlayerLabel.setBounds(LABEL_XCOORD, yCoord+=40, (PANEL_WIDTH/2)-50, 30);
        
        JComboBox cashPlayerBox = new JComboBox<>(player);
        cashPlayerBox.setBounds(200, yCoord, PANEL_WIDTH/2-40, 30);

        JLabel amountLabel = new JLabel("Amount :");
        amountLabel.setForeground(Color.BLACK);
        amountLabel.setFont(labelFont);
        amountLabel.setHorizontalAlignment(JLabel.RIGHT);
        amountLabel.setBounds(LABEL_XCOORD, yCoord+=35, (PANEL_WIDTH/2)-50, 30); 

        JTextField amountField = new JTextField();
        amountField.setBounds(200, yCoord, PANEL_WIDTH/2-40, 30); 

        JButton giveCashBut = new JButton("Add / Deduck");
        giveCashBut.setFont(btnFont);
        giveCashBut.setBounds(100, yCoord+=40, 200, 35); 
        giveCashBut.setHorizontalAlignment(SwingConstants.CENTER);
        giveCashBut.addActionListener(new ActionListener() {
			@Override
            // TODO: giveCash logic
			public void actionPerformed(ActionEvent e) {
                try {
                    Player player = controller.getBoard().findPlayer(cashPlayerBox.getSelectedItem().toString());
                    int amount = Integer.parseInt(amountField.getText());
                    controller.changeBalanceBut(player, amount);
                } catch (NumberFormatException ex) {
                    showEditorMessage("Please enter a valid integer for the amount.", "Invalid Input","error");
                }
            }});

        // move player to other slot
        JLabel movePlayerTitle = new JLabel("Move Player To Other Slot");
        movePlayerTitle.setForeground(Color.BLACK);
        movePlayerTitle.setFont(titleFont);
        movePlayerTitle.setBounds(TITLE_XCOORD, yCoord+=40, PANEL_WIDTH-50, 30); 
        //movePlayerTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel movePlayerLabel = new JLabel("Player :");
        movePlayerLabel.setForeground(Color.BLACK);
        movePlayerLabel.setFont(labelFont);
        movePlayerLabel.setHorizontalAlignment(JLabel.RIGHT);
        movePlayerLabel.setBounds(LABEL_XCOORD, yCoord+=40, (PANEL_WIDTH/2)-50, 30); 

        JComboBox movePlayerBox = new JComboBox<>(player);
        movePlayerBox.setBounds(200, yCoord, PANEL_WIDTH/2-40, 30);

        JLabel moveSlotLabel = new JLabel("Move to :");
        moveSlotLabel.setForeground(Color.BLACK);
        moveSlotLabel.setFont(labelFont);
        moveSlotLabel.setHorizontalAlignment(JLabel.RIGHT);
        moveSlotLabel.setBounds(LABEL_XCOORD, yCoord+=35, (PANEL_WIDTH/2)-50, 30); 

        JComboBox moveSlotBox = new JComboBox<>(propertyNames.toArray(new String[0]));
        moveSlotBox.setBounds(200, yCoord, PANEL_WIDTH/2-40, 30);

        JButton movePlayerBut = new JButton("Move Player");
        movePlayerBut.setFont(btnFont);
        movePlayerBut.setBounds(100, yCoord+=40, 200, 35); 
        movePlayerBut.setHorizontalAlignment(SwingConstants.CENTER);
        movePlayerBut.addActionListener(new ActionListener() {
			@Override
            // movePlayerBut logic
			public void actionPerformed(ActionEvent e) {
                Player player = controller.getBoard().findPlayer(movePlayerBox.getSelectedItem().toString());
                int slot = moveSlotBox.getSelectedIndex();
                controller.changeLocationBut(player, slot);
            }});
        
        // change player status
        JLabel changeStatusTitle = new JLabel("Change Player Status");
        changeStatusTitle.setForeground(Color.BLACK);
        changeStatusTitle.setFont(titleFont);
        changeStatusTitle.setBounds(TITLE_XCOORD, yCoord+=40, PANEL_WIDTH-50, 30); 

        JLabel statusPlayerLabel = new JLabel("Player :");
        statusPlayerLabel.setForeground(Color.BLACK);
        statusPlayerLabel.setFont(labelFont);
        statusPlayerLabel.setHorizontalAlignment(JLabel.RIGHT);
        statusPlayerLabel.setBounds(LABEL_XCOORD, yCoord+=40, (PANEL_WIDTH/2)-50, 30);
        
        JComboBox statusPlayerBox = new JComboBox<>(player);
        statusPlayerBox.setBounds(200, yCoord, PANEL_WIDTH/2-40, 30);

        JLabel statusLabel = new JLabel("Status :");
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setFont(labelFont);
        statusLabel.setHorizontalAlignment(JLabel.RIGHT);
        statusLabel.setBounds(LABEL_XCOORD, yCoord+=35, (PANEL_WIDTH/2)-50, 30); 

        JComboBox statusBox = new JComboBox<>(status);
        statusBox.setBounds(200, yCoord, PANEL_WIDTH/2-40, 30);

        JButton statusBut = new JButton("Change Status");
        statusBut.setFont(btnFont);
        statusBut.setBounds(100, yCoord+=40, 200, 35); 
        statusBut.setHorizontalAlignment(SwingConstants.CENTER);
        statusBut.addActionListener(new ActionListener() {
			@Override
            // statusBut logic
			public void actionPerformed(ActionEvent e) {
                Player player = controller.getBoard().findPlayer(statusPlayerBox.getSelectedItem().toString());
                boolean isBankrupt = "Bankrupt".equalsIgnoreCase(statusBox.getSelectedItem().toString());
                controller.changeStatusBut(player, isBankrupt);

            }});

        // Change current player
        JLabel currentPlayerTitle = new JLabel("Change Cuurent Player");
        currentPlayerTitle.setForeground(Color.BLACK);
        currentPlayerTitle.setFont(titleFont);
        currentPlayerTitle.setBounds(TITLE_XCOORD, yCoord+=40, PANEL_WIDTH-50, 30); 
        //currentPlayerTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel currPlayerLabel = new JLabel("Set Current Player :");
        currPlayerLabel.setForeground(Color.BLACK);
        currPlayerLabel.setFont(labelFont);
        currPlayerLabel.setHorizontalAlignment(JLabel.RIGHT);
        currPlayerLabel.setBounds(LABEL_XCOORD, yCoord+=40, (PANEL_WIDTH/2)-50, 30); 

        JComboBox currPlayerBox = new JComboBox<>(player);
        currPlayerBox.setBounds(200, yCoord, PANEL_WIDTH/2-40, 30);

        JButton currPlayerBut = new JButton("Change Current Player");
        currPlayerBut.setFont(btnFont);
        currPlayerBut.setBounds(100, yCoord+=40, 200, 35); 
        currPlayerBut.setHorizontalAlignment(SwingConstants.CENTER);
        currPlayerBut.addActionListener(new ActionListener() {
			@Override
            //currPlayerBut logic
			public void actionPerformed(ActionEvent e) {
                Player player = controller.getBoard().findPlayer(currPlayerBox.getSelectedItem().toString());
                if (player == null) {
                    showEditorMessage("Player not found", "Change Current Player Error", "information");
                    return;
                }
                controller.changeCurrentPlayerBut(player);
                return;
            }});
        
        editorJPanel.add(changeOwnerTitle);
        editorJPanel.add(ownerPlayerLabel);
        editorJPanel.add(ownerPlayerBox);
        editorJPanel.add(ownerSlotLabel);
        editorJPanel.add(ownerSlotBox);
        editorJPanel.add(changeOwnerBut);

        editorJPanel.add(giveCashTitle);
        editorJPanel.add(cashPlayerLabel);
        editorJPanel.add(amountLabel);
        editorJPanel.add(cashPlayerBox);
        editorJPanel.add(amountField);
        editorJPanel.add(giveCashBut);

        
        editorJPanel.add(movePlayerTitle);
        editorJPanel.add(movePlayerLabel);
        editorJPanel.add(movePlayerBox);
        editorJPanel.add(moveSlotLabel);
        editorJPanel.add(moveSlotBox);
        editorJPanel.add(movePlayerBut);


        editorJPanel.add(changeStatusTitle);
        editorJPanel.add(statusPlayerLabel);
        editorJPanel.add(statusPlayerBox);
        editorJPanel.add(statusLabel);
        editorJPanel.add(statusBox);
        editorJPanel.add(statusBut);

        editorJPanel.add(currentPlayerTitle);
        editorJPanel.add(currPlayerLabel);
        editorJPanel.add(currPlayerBox);
        editorJPanel.add(currPlayerBut);

        this.add(editorJPanel);

        pack();
        setLocationRelativeTo(null); 
    }
    
    public void showEditorMessage(String message, String title, String messageType){
        switch (messageType) {
            case "error": JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE); break;
            case "information": JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE); break;
            default:
                System.out.println(messageType+" message not available");
        }
    }
}
