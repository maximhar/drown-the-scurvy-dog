package uk.ac.aber.dcs.msh4.cs124.gui;

import javax.swing.*;
import java.awt.*;

public class PiratePanel extends JPanel {
    private int piratePositions = 10;
    private int piratePosition = 0;
    private float shipH = 0.3f;
    private float shipW = 0.2f;
    private float shipX = 0.7f;
    private float shipY = 0.3f;
    private float plankLen = 0.5f;
    private float pirateHeight = 0.2f;
    private float pirateWidth = 0.1f;


    @Override
    public void paintComponent(Graphics g){
        // clear the whole panel
        g.setColor(Color.WHITE);
        g.fillRect(0,0,scaleX(1), scaleY(1));
        // then draw the scene
        drawOcean(g);
        drawShip(g);
        drawPlank(g);
        drawPirate(g);
    }

    private void drawPirate(Graphics g) {
        g.setColor(Color.RED);
        float pirateX =  + shipX - ((float) getPiratePosition() / (float) getPiratePositions()) * plankLen;
        float pirateY = shipY + shipH/2 - pirateHeight;
        g.fillOval(scaleX(pirateX), scaleY(pirateY), scaleX(pirateWidth), scaleY(pirateHeight));
        g.drawString("P", scaleX(pirateX), scaleY(pirateY));
    }

    private void drawPlank(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(scaleX(shipX), scaleY(shipY + shipH/2), scaleX(shipX - plankLen), scaleY(shipY + shipH/2));
    }

    private void drawOcean(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(scaleX(0), scaleY(0.5f), scaleX(1), scaleY(0.5f));
    }

    private void drawShip(Graphics g){
        g.setColor(Color.BLACK);
        g.fillPolygon(new int[] { scaleX(shipX), scaleX(shipX + shipW/2), scaleX(shipX + shipW)},
                new int[] { scaleY(shipY + shipH/2), scaleY(shipY + shipH), scaleY(shipY  + shipH/2)}, 3);
        g.drawLine(scaleX(shipX + shipW/2), scaleY(shipY), scaleX(shipX + shipW/2), scaleY(shipY + shipH/2));
    }
    /// Scales a relative x coordinate to an absolute x coordinate
    private int scaleX(float x){
        return (int)(x * this.getWidth());
    }
    /// Scales a relative y coordinate to an absolute y coordinate
    private int scaleY(float y){
        return (int)(y * this.getHeight());
    }
    /// The count of positions the pirate may take between the start of the plank and him drowning
    public int getPiratePositions() {
        return piratePositions;
    }
    /// Sets the count of positions the pirate may take between the start of the plank and him drowning
    public void setPiratePositions(int piratePositions) {
        this.piratePositions = piratePositions;
    }
    /// Gets the current position of the pirate
    public int getPiratePosition() {
        return piratePosition;
    }
    /// Sets the position of the pirate on the plank
    public void setPiratePosition(int piratePosition) {
        this.piratePosition = piratePosition;
    }
    /// Sets the vertical offset of the ship
    public void setShipVerticalOffset(float f){
        this.shipY += f;
    }
}
