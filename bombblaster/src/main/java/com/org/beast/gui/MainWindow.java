package com.org.beast.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 08-01-2017.
 */
public class MainWindow {
    private Integer index=3;
    private JComboBox cb;
    private JFrame frame;
    private JLabel label;
    private JButton button;

    public MainWindow() {
        frame = new JFrame();
        label = new JLabel("Select your Grid Size");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(200,50);
        String grids[]={"3X3","5X5","7X7","9X9"};
        cb = new JComboBox(grids);
        cb.setSize(200,50);
        cb.setBounds(200,0,200,50);
        button = new JButton("Create Game");
        button.setBounds(0,50,400,50);
        button.setSize(400,50);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.add(label);
        frame.add(cb);
        frame.add(button);
        frame.setSize(420,140);
        frame.setResizable(false);
        frame.setLayout(null);
    }

    private class CreateGame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch(cb.getSelectedIndex()) {
                case 1:
                    index = 5;
                    break;
                case 2:
                    index = 7;
                    break;
                case 3:
                    index = 9;
                    break;
                case 0:
                default:
                    index = 3;
            }
            frame.dispose();
            GameWindow gameWindow = new GameWindow(index);
            gameWindow.run();
        }
    }


    public void run() {
        frame.setVisible(true);
        button.addActionListener(new CreateGame());
    }
}
