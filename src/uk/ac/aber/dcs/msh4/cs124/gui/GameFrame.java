package uk.ac.aber.dcs.msh4.cs124.gui;

import uk.ac.aber.dcs.msh4.cs124.model.PirateHangman;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class GameFrame extends JFrame {
    JPanel mainPanel;
    private ActionListener wordListener;
    private ActionListener symbolListener;
    private JLabel statusLabel;
    private PiratePanel animationPanel;
    private Timer wavesTimer;
    private PirateHangman model;
    private JTextField wordField;
    private JTextField symbolField;
    public GameFrame(){
        init();
    }

    private void init() {

        setTitle("Drown the Scurvy Dog");
        setLocationRelativeTo(null);
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            model = new PirateHangman(10);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load pirate dictionary, closing");
            this.setVisible(false);
            return;
        }
        wavesTimer = new Timer(500, new ActionListener() {
            boolean waveHigh = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                waveHigh = !waveHigh;
                animationPanel.setShipVerticalOffset(waveHigh ? -0.01f : 0.01f);
                animationPanel.repaint();
            }
        });
        wavesTimer.start();

        createListeners();
        
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //extremely verbose code creating the main layout...
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel label = new JLabel("Enter a word:");
        mainPanel.add(label, c);

        label = new JLabel("Enter a symbol:");
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(label, c);

        wordField = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 2;
        mainPanel.add(wordField, c);

        symbolField = new JTextField();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 2;
        mainPanel.add(symbolField, c);

        JButton buttonWord = new JButton("Confirm word");
        c.gridy = 0;
        c.gridx = 2;
        c.weightx = 0.5;
        buttonWord.addActionListener(this.wordListener);
        mainPanel.add(buttonWord, c);

        JButton buttonSymbol = new JButton("Confirm symbol");
        c.gridy = 1;
        c.gridx = 2;
        c.weightx = 0.5;
        buttonSymbol.addActionListener(this.symbolListener);
        mainPanel.add(buttonSymbol, c);

        statusLabel = new JLabel("Guesses remaining and visible word");
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 3;
        mainPanel.add(statusLabel, c);

        animationPanel = new PiratePanel();

        c.gridy = 3;
        c.gridx = 0;
        c.weighty = 2;
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        animationPanel.setPiratePositions(model.guessLeft());
        mainPanel.add(animationPanel, c);

        getContentPane().add(mainPanel);
        updateStatus();
    }
    private void reinit(){
        this.getContentPane().remove(mainPanel);
        init();
    }
    private void createListeners() {
        this.wordListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(wordField.getText().length() < 1) return;
                if(model.tryWord(wordField.getText())) {
                    tellVictory();
                } else if (model.guessLeft() <= 0){
                    tellLoss();
                } else {
                    updateStatus();
                }
            }
        };

        this.symbolListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(symbolField.getText().length() < 1) return;
                if(model.tryThis(symbolField.getText().charAt(0))) {
                    tellVictory();
                } else if (model.guessLeft() <= 0){
                    tellLoss();
                } else {
                    updateStatus();
                }
            }
        };
    }

    private void updateStatus() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                statusLabel.setText("You can see: " + model.getVisible() + "; Letters tried: " + model.getLetters() + "; Guesses left: " + model.guessLeft());
                animationPanel.setPiratePosition(animationPanel.getPiratePositions() - model.guessLeft());
                animationPanel.repaint();
                symbolField.setText("");
                wordField.setText("");
            }
        });
    }


    private void tellVictory(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "You won! The word was " + model.getHidden());
                reinit();
            }
        });
    }

    private void tellLoss(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "You lost! The word was " + model.getHidden());
                reinit();
            }
        });
    }
}
