package uk.ac.aber.dcs.msh4.cs124.gui;

import javax.swing.*;
import java.awt.*;

public class PiratePanel extends JPanel {
    private int piratePositions = 10;
    private int piratePosition = 0;

    @Override
    public void paintComponent(Graphics g){
        drawOcean(g);
    }

    private void drawOcean(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(scaleX(0), scaleY(0.5f), scaleX(1), scaleY(0.5f));
    }

    private int scaleX(float x){
        return (int)(x * this.getWidth());
    }

    private int scaleY(float y){
        return (int)(y * this.getHeight());
    }
}
