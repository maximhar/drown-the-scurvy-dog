package uk.ac.aber.dcs.msh4.cs124.gui;

import uk.ac.aber.dcs.msh4.cs124.GameView;

import javax.swing.*;

public class GraphicalView implements GameView {
    @Override
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame frame = new GameFrame();
                frame.setVisible(true);
            }
        });
    }
}
