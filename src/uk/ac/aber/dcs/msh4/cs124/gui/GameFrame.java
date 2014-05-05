package uk.ac.aber.dcs.msh4.cs124.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private ActionListener wordListener;
    private ActionListener symbolListener;
    private JLabel statusLabel;
    private JPanel animationPanel;

    public GameFrame(){
        init();
    }

    private void init() {
        setTitle("Drown the Scurvy Dog");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createListeners();
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
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

        JTextField wordField = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 2;
        mainPanel.add(wordField, c);

        JTextField symbolField = new JTextField();
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
        mainPanel.add(animationPanel, c);

        getContentPane().add(mainPanel);
    }

    private void createListeners() {
        this.wordListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        this.symbolListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }
}
