package view;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

import controller.GameController;
import model.*;

public class GameEditor extends JFrame{
    private GameModel model;
    private GameController controller;

    private final int PANEL_WIDTH = 500;
    private final int PANEL_HEIGHT = 750;
    private JPanel editorJPanel;

    public GameEditor(GameModel model, GameController controller) {
        this.model = model;
        this.controller = controller;
        initializeUI();
    }

    public void initializeUI(){
        setTitle("Game Editor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);

        editorJPanel = new JPanel();
        editorJPanel.setBackground(new Color(153,153,153));
        editorJPanel.setLayout(null);
        editorJPanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        // change slot owner
        JLabel changeOwnerTitle = new JLabel("Change Slot Owner");
        changeOwnerTitle.setForeground(Color.BLACK);
        changeOwnerTitle.setFont(new Font("Arial", Font.BOLD, 18));
        changeOwnerTitle.setBounds(0, 20, PANEL_WIDTH-50, 30); 
        changeOwnerTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel ownerSlotLabel = new JLabel("Slot : ");
        ownerSlotLabel.setForeground(Color.BLACK);
        ownerSlotLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ownerSlotLabel.setBounds(40, 50, PANEL_WIDTH/2-50, 30); 

        JTextField ownerSloTField = new JTextField();
        ownerSloTField.setBounds(200, 50, PANEL_WIDTH/2-50, 30); 

        JLabel ownerPlayerLabel = new JLabel("Player : ");
        ownerPlayerLabel.setForeground(Color.BLACK);
        ownerPlayerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ownerPlayerLabel.setBounds(40, 80, PANEL_WIDTH-50, 30); 

        JTextField ownerPlayerField = new JTextField();
        ownerPlayerField.setBounds(200, 80, PANEL_WIDTH/2-50, 30); 

        JButton changeOwnerBut = new JButton("Change Owner");
        changeOwnerBut.setBounds(150, 130, 150, 30); 
        changeOwnerBut.setHorizontalAlignment(SwingConstants.CENTER);
        changeOwnerBut.addActionListener(new ActionListener() {
			@Override
            // TODO: changeOwnerBut logic
			public void actionPerformed(ActionEvent e) {
                Player player = model.getBoard().findPlayer(ownerPlayerField.getText());
                int slot = Integer.parseInt(ownerSloTField.getText());
                controller.changeOwnerBut(slot, player);
            }});

        // give cash
        JLabel giveCashTitle = new JLabel("Give Money");
        giveCashTitle.setForeground(Color.BLACK);
        giveCashTitle.setFont(new Font("Arial", Font.BOLD, 18));
        giveCashTitle.setBounds(0, 160, PANEL_WIDTH-50, 30); 
        giveCashTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel cashPlayerLabel = new JLabel("Player : ");
        cashPlayerLabel.setForeground(Color.BLACK);
        cashPlayerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cashPlayerLabel.setBounds(50, 190, PANEL_WIDTH-50, 30); 

        JLabel amountLabel = new JLabel("Amount : ");
        amountLabel.setForeground(Color.BLACK);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        amountLabel.setBounds(50, 220, PANEL_WIDTH-50, 30); 

        JTextField cashPlayerField = new JTextField();
        cashPlayerField.setBounds(200, 190, PANEL_WIDTH/2-50, 30);

        JTextField amountField = new JTextField();
        amountField.setBounds(200, 220, PANEL_WIDTH/2-50, 30); 

        JButton giveCashBut = new JButton("Add / Deduck");
        giveCashBut.setBounds(150, 260, 150, 30); 
        giveCashBut.setHorizontalAlignment(SwingConstants.CENTER);
        giveCashBut.addActionListener(new ActionListener() {
			@Override
            // TODO: giveCash logic
			public void actionPerformed(ActionEvent e) {
                Player player = model.getBoard().findPlayer(cashPlayerField.getText());
                int amount = Integer.parseInt(amountField.getText());
                controller.changeBalanceBut(player, amount);

            }});

        // move player to other slot
        JLabel movePlayerTitle = new JLabel("Move Player");
        movePlayerTitle.setForeground(Color.BLACK);
        movePlayerTitle.setFont(new Font("Arial", Font.BOLD, 18));
        movePlayerTitle.setBounds(0, 290, PANEL_WIDTH-50, 30); 
        movePlayerTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel movePlayerLabel = new JLabel("Player : ");
        movePlayerLabel.setForeground(Color.BLACK);
        movePlayerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        movePlayerLabel.setBounds(50, 320, PANEL_WIDTH-50, 30); 

        JLabel moveSlotLabel = new JLabel("Move to : ");
        moveSlotLabel.setForeground(Color.BLACK);
        moveSlotLabel.setFont(new Font("Arial", Font.BOLD, 16));
        moveSlotLabel.setBounds(50, 350, PANEL_WIDTH-50, 30); 

        JTextField movePlayerField = new JTextField();
        movePlayerField.setBounds(200, 320, PANEL_WIDTH/2-50, 30);

        JTextField moveSlotField = new JTextField();
        moveSlotField.setBounds(200, 350, PANEL_WIDTH/2-50, 30);

        JButton movePlayerBut = new JButton("Move Player");
        movePlayerBut.setBounds(150, 390, 150, 30); 
        movePlayerBut.setHorizontalAlignment(SwingConstants.CENTER);
        movePlayerBut.addActionListener(new ActionListener() {
			@Override
            // TODO: movePlayerBut logic
			public void actionPerformed(ActionEvent e) {
                Player player = model.getBoard().findPlayer(movePlayerField.getText());
                int slot = Integer.parseInt(moveSlotField.getText());
                controller.changeLocationBut(player, slot);
            }});
        
        // change player status
        JLabel changeStatusTitle = new JLabel("Change Player Status");
        changeStatusTitle.setForeground(Color.BLACK);
        changeStatusTitle.setFont(new Font("Arial", Font.BOLD, 18));
        changeStatusTitle.setBounds(0, 420, PANEL_WIDTH-50, 30); 
        changeStatusTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel statusPlayerLabel = new JLabel("Player : ");
        statusPlayerLabel.setForeground(Color.BLACK);
        statusPlayerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusPlayerLabel.setBounds(50, 450, PANEL_WIDTH-50, 30); 

        JLabel statusLabel = new JLabel("Status : ");
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBounds(50, 480, PANEL_WIDTH-50, 30); 

        JTextField statusPlayerField = new JTextField();
        statusPlayerField.setBounds(200, 450, PANEL_WIDTH/2-50, 30);

        JTextField statusField = new JTextField();
        statusField.setBounds(200, 480, PANEL_WIDTH/2-50, 30);

        JButton statusBut = new JButton("Change Status");
        statusBut.setBounds(150, 520, 150, 30); 
        statusBut.setHorizontalAlignment(SwingConstants.CENTER);
        statusBut.addActionListener(new ActionListener() {
			@Override
            // TODO: statusBut logic
			public void actionPerformed(ActionEvent e) {
                Player player = model.getBoard().findPlayer(statusPlayerField.getText());
                boolean isBankrupt = Boolean.parseBoolean(statusField.getText());
                controller.changeStatusBut(player, isBankrupt);
            }});

        // Change next turn player
        JLabel nextTurnTitle = new JLabel("Change Next Player");
        nextTurnTitle.setForeground(Color.BLACK);
        nextTurnTitle.setFont(new Font("Arial", Font.BOLD, 18));
        nextTurnTitle.setBounds(0, 550, PANEL_WIDTH-50, 30); 
        nextTurnTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nextPlayerLabel = new JLabel("Next Player : ");
        nextPlayerLabel.setForeground(Color.BLACK);
        nextPlayerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nextPlayerLabel.setBounds(50, 580, PANEL_WIDTH-50, 30); 

        JTextField nextPlayerField = new JTextField();
        nextPlayerField.setBounds(200, 580, PANEL_WIDTH/2-50, 30);

        JButton nextPlayerBut = new JButton("Change Next Player");
        nextPlayerBut.setBounds(150, 630, 150, 30); 
        nextPlayerBut.setHorizontalAlignment(SwingConstants.CENTER);
        nextPlayerBut.addActionListener(new ActionListener() {
			@Override
            // TODO: nextPlayerBut logic
			public void actionPerformed(ActionEvent e) {
                Player player = model.getBoard().findPlayer(nextPlayerField.getText());
                if (player == null) {
                    System.out.println("Player not found");
                    return;
                }
                controller.changeCurrentPlayerBut(player);
                return;
            }});

        editorJPanel.add(changeOwnerTitle);
        editorJPanel.add(ownerPlayerLabel);
        editorJPanel.add(ownerSlotLabel);
        editorJPanel.add(ownerSloTField);
        editorJPanel.add(ownerPlayerField);
        editorJPanel.add(changeOwnerBut);
        
        editorJPanel.add(giveCashTitle);
        editorJPanel.add(cashPlayerLabel);
        editorJPanel.add(amountLabel);
        editorJPanel.add(cashPlayerField);
        editorJPanel.add(amountField);
        editorJPanel.add(giveCashBut);
        
        editorJPanel.add(movePlayerTitle);
        editorJPanel.add(movePlayerLabel);
        editorJPanel.add(moveSlotLabel);
        editorJPanel.add(movePlayerField);
        editorJPanel.add(moveSlotField);
        editorJPanel.add(movePlayerBut);

        editorJPanel.add(changeStatusTitle);
        editorJPanel.add(statusPlayerLabel);
        editorJPanel.add(statusLabel);
        editorJPanel.add(statusField);
        editorJPanel.add(statusPlayerField);
        editorJPanel.add(statusBut);

        editorJPanel.add(nextTurnTitle);
        editorJPanel.add(nextPlayerLabel);
        editorJPanel.add(nextPlayerField);
        editorJPanel.add(nextPlayerBut);
        add(editorJPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); 
    }
}
