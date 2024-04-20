package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 *
 * @author User
 */
public class DicePanel extends javax.swing.JPanel {
    /**
     Function todo
     get diceValue()
     
     */
    // var
    private int faceValue = 1;
    
    public DicePanel(int xCoord, int yCoord, int width, int height) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
	    setBounds(xCoord, yCoord, width, height);
        setOpaque(true);
    }
    
    // draw dice
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        
        if(faceValue == 1) {
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        } else if(faceValue == 2) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
        } else if(faceValue == 3) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        } else if(faceValue == 4) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
        } else if(faceValue == 5) {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 - 5/2, getHeight()/2 - 5/2, 5, 5);
        } else {
            g.fillOval(getWidth()/2 - 15, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 15, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 + 10, 5, 5);
            g.fillOval(getWidth()/2 - 15, getHeight()/2 - 5/2, 5, 5);
            g.fillOval(getWidth()/2 + 10, getHeight()/2 - 5/2, 5, 5);
        }
	
    }
}
