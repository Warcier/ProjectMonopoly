package view;

import controller.*;
import model.Property;
import model.test;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.swing.border.LineBorder;

public class SlotSquare extends javax.swing.JLayeredPane{

    GameController controller;
    private JLabel nameLabel;
    private JLabel priceLabel;

    private List<Property> properties = test.initProperty();
    
    private ArrayList<String> slotNames = new ArrayList<>();
    private ArrayList<Integer> slotPrices = new ArrayList<>();
    private int slotPrice;
    private String slotName;
    private double rent;
    private int slotNum;
    /**
     * Creates new form Square
     */
    public SlotSquare(int xCoord, int yCoord, int width, int height,int slotNum, int rotationDegrees ) {
        setBorder(new LineBorder(new Color(0,0,0)));
        setBounds(xCoord,yCoord,width,height);
        setOpaque(true);
        setBackground(Color.WHITE);
        this.setLayout(null);
        this.slotNum = slotNum;
        // when mouse click it displayDetails
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                displayDetails();
            }
        });

        // test if property is empty
        if (slotNames == null || slotPrices == null || slotNames.size() <= slotNum || slotPrices.size() <= slotNum) {
            initializeSlots();
        }
        // set slot name, price, rent
        try {
            this.slotName = slotNames.get(slotNum);
            this.slotPrice = slotPrices.get(slotNum);
            this.rent =  Math.ceil(slotPrice*0.1 * 100)/100;
            
        } catch (NumberFormatException e) {
            System.out.println(slotNum+" is empty");
        }
        
        if(rotationDegrees == 135 || rotationDegrees == -135 || rotationDegrees == -45 || rotationDegrees == 45) {
            nameLabel = setupLabel(slotName,rotationDegrees);
            nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            nameLabel.setBounds(0,0,this.getWidth(),this.getHeight());
            this.add(nameLabel);
	} else {	
						
		nameLabel = setupLabel(slotName,rotationDegrees);
                priceLabel = setupLabel(String.valueOf(slotPrice),rotationDegrees);

		
		if(rotationDegrees == 90) {
                    nameLabel.setBounds(10, 0, this.getWidth(), this.getHeight());
                    priceLabel.setBounds(-10, 0, this.getWidth(), this.getHeight());
                }
		if(rotationDegrees == -90) {
                    nameLabel.setBounds(-10, 0, this.getWidth(), this.getHeight());
                    priceLabel.setBounds(10, 0, this.getWidth(), this.getHeight());
		}
		if(rotationDegrees == 180) {
                    nameLabel.setBounds(0, 10, this.getWidth(), this.getHeight());
                    priceLabel.setBounds(0, -10, this.getWidth(), this.getHeight());
		}
		if(rotationDegrees == 0) {
                    nameLabel.setBounds(0, -10, this.getWidth(), this.getHeight());
                    priceLabel.setBounds(0, 10, this.getWidth(), this.getHeight());
		}
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
                priceLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
		this.add(nameLabel);
                this.add(priceLabel);
    }
        
    }


    private JLabel setupLabel(String text, int rotationDegrees) {
        JLabel label = new JLabel(text) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                AffineTransform aT = g2.getTransform();
                Shape oldShape = g2.getClip();
                double x = getWidth() / 2.0;
                double y = getHeight() / 2.0;
                aT.rotate(Math.toRadians(rotationDegrees), x, y);
                g2.setTransform(aT);
                g2.setClip(oldShape);
                super.paintComponent(g);
            }
            };

        // Common label properties
        label.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Label bounds will be adjusted outside this method, as they may differ
        return label;
    }

    private void initializeSlots(){
        for (int i=0; i<properties.size();i++){
            Property slot = properties.get(i);
            slotNames.add(slot.getLandName());
            slotPrices.add(slot.getLandPrice());
        }
    }

    private void displayDetails(){
        JDialog detailDialog = new JDialog();
        detailDialog.setTitle("Slot "+ slotNum+ " Details");
        detailDialog.setSize(300, 200);
        detailDialog.setLocationRelativeTo(null);
        detailDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        detailDialog.setLayout(new BorderLayout());
        

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.setBackground(new Color(102,102,102));

        JLabel detailSlotName = new JLabel("Slot Name: "+slotName);
        detailSlotName.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailSlotName.setFont(new Font("Arial", Font.BOLD, 14));
        detailSlotName.setForeground(Color.WHITE);

        JLabel detailSlotPrice = new JLabel("Price: "+String.valueOf(slotPrice));    
        detailSlotPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailSlotPrice.setFont(new Font("Arial", Font.BOLD, 14));
        detailSlotPrice.setForeground(Color.WHITE);

        JLabel detailSlotRent = new JLabel("Rent Price: "+String.valueOf(rent));
        detailSlotRent.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailSlotRent.setFont(new Font("Arial", Font.BOLD, 14));
        detailSlotRent.setForeground(Color.WHITE);

        // get slot owner ??
        JLabel detailSlotOwner = new JLabel("Owner: NA");
        detailSlotOwner.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailSlotOwner.setFont(new Font("Arial", Font.BOLD, 14));
        detailSlotOwner.setForeground(Color.WHITE);

         // Pushes all items to the center
        dialogPanel.add(Box.createVerticalGlue());
        dialogPanel.add(detailSlotName);
        dialogPanel.add(Box.createVerticalStrut(10)); 
        dialogPanel.add(detailSlotPrice);
        dialogPanel.add(Box.createVerticalStrut(10));
        dialogPanel.add(detailSlotRent);
        dialogPanel.add(Box.createVerticalStrut(10));
        dialogPanel.add(detailSlotOwner);
        dialogPanel.add(Box.createVerticalGlue());

        detailDialog.add(dialogPanel, BorderLayout.CENTER);
        detailDialog.setVisible(true);

    }
}
