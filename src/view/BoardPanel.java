package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


import view.SlotSquare;


public class BoardPanel extends javax.swing.JPanel {

    /**
     * Creates new form BoardPanel
     */
    // set required var
    private int panelWidth = 800;
    private int panelHeight = 800;
    int squareWidth;
    int squareHeight;
    int xOffset = 0;
    int yOffset = 0;
    int squareCount = 44;
    SlotSquare[] squares = new SlotSquare[squareCount];
    private int[] rotationAngles = {135,180,180,180,180,180,180,180,180,180,180,-135,
                                        -90,-90,-90,-90,-90,-90,-90,-90,-90,-90,-45,
                                        0,0,0,0,0,0,0,0,0,0,45,
                                        90,90,90,90,90,90,90,90,90,90};
    
    
    public BoardPanel(int xCoord, int yCoord) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(xCoord, yCoord, panelWidth, panelHeight);
        this.setLayout(null);
        //setPreferredSize(new Dimension(panelWidth, panelHeight));
        //setSlotTexts();
        
        squareWidth = (int) Math.round(panelWidth/12);
        squareHeight = (int) Math.round(panelHeight/12);
        /**
        Square square0 = new Square(0,0,squareWidth,squareHeight,0,135);
        this.add(square0);
        Square square1 = new Square(0+squareWidth,0+squareHeight,squareWidth,squareHeight,1,180);
        this.add(square1);*/
        // add slot square to board
        for(int i=0;i<squareCount;i++){
            int x,y;
            //Top row
            if (i <12) {
                x = xOffset + i * squareWidth;
                y = yOffset ;
            } 
            //Right column
            else if (i <22) {
                x = xOffset + 11 * squareWidth;
                y = yOffset + (i-11) * squareHeight;
            } 
            // Bottom row
            else if (i < 33) {
                x = xOffset + (11-(i-22))*squareWidth;
                y = yOffset + 11 *squareHeight;
            }
            //Left colums y11 34
            else{
                x = xOffset;
                y = yOffset + ((44-i))*squareHeight;
            }
            // create square
            squares[i] = new SlotSquare(x, y, squareWidth, squareHeight, i, rotationAngles[i]);
            this.add(squares[i]);
        }
    }

}