package view;

import model.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import javax.swing.border.LineBorder;

public class SlotSquare extends javax.swing.JPanel{
    private JLayeredPane slotPanel;
    private JLabel nameLabel;
    private JLabel priceLabel;

    // TODO: Set Property to Label
    private Property property;
    
    private String[] slotNames = java.util.stream.IntStream.rangeClosed(1, 44).mapToObj(i -> "Name" + i).toArray(String[]::new);
    private String[] slotPrices = new String[44];
    private double slotPrice;
    private String slotName;
    private double rent;
    /**
     * Creates new form Square
     */
    public SlotSquare(int xCoord, int yCoord, int width, int height,int slotNum, int rotationDegrees) {
        setBorder(new LineBorder(new Color(0,0,0)));
        setBounds(xCoord,yCoord,width,height);
        setBackground(new Color(255, 255, 255));
        // testing slotPrice
        Arrays.fill(slotPrices,"100");
        //
        this.setLayout(null);
        this.slotName = slotNames[slotNum];
        try {
            this.slotPrice = Double.parseDouble(slotNames[slotNum]);
            this.rent =  Math.ceil(slotPrice*0.1 * 100)/100;
            
        } catch (NumberFormatException e) {
            System.out.println(slotNames[slotNum]+" is not a valid double value");
        }
        
        if(rotationDegrees == 135 || rotationDegrees == -135 || rotationDegrees == -45 || rotationDegrees == 45) {
            nameLabel = setupLabel(slotName,rotationDegrees);
            nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            nameLabel.setBounds(0,0,this.getWidth(),this.getHeight());
            this.add(nameLabel);
	} else {	
						
		nameLabel = setupLabel(slotName,rotationDegrees);
                priceLabel = setupLabel(slotPrices[slotNum],rotationDegrees);

		
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
		}}


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
}
