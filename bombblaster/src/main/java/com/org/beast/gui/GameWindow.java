package com.org.beast.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Vector;
import com.org.beast.game.Game;

/**
 * Created by User on 08-01-2017.
 */
public class GameWindow {
    private JFrame frame;
    private Vector<Vector<JButton>> table;
    private Integer index;
    Game game;

    public GameWindow(Integer index) {
        this.index = index;
        game = new Game(index);
        table = new Vector<Vector<JButton>>(index);
        frame = new JFrame();
        frame.setSize(index * 70, index * 70);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(index, index));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        for (int i = 0; i < index; i++) {
            Vector<JButton> row = new Vector<JButton>(index);
            for (int j = 0; j < index; j++) {
                String name = i + " " + j;
                row.add(j, new JButton());
                row.elementAt(j).setName(name);
                row.elementAt(j).setBounds(j * 70, i * 70, 70, 70);
                row.elementAt(j).addActionListener(new runGame());
                row.elementAt(j).addMouseListener(new rightClick());
                frame.add(row.elementAt(j));
            }
            table.add(i, row);
        }
    }

    private class runGame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton selected = (JButton) e.getSource();
            String index[] = selected.getName().split(" ");
            if (!game.isFlagged(Integer.parseInt(index[0]), Integer.parseInt(index[1])))
                displayBoard(Integer.parseInt(index[0]), Integer.parseInt(index[1]));
            else
                JOptionPane.showMessageDialog(null, "Cell is flagged. Cannot be selected", "Alert!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private class rightClick implements MouseListener {
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseClicked(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
            if(e.getButton()==3)
            {
                JButton selected = (JButton) e.getSource();
                String index[] = selected.getName().split(" ");
                game.setFlagged(Integer.parseInt(index[0]), Integer.parseInt(index[1]),!game.isFlagged(Integer.parseInt(index[0]), Integer.parseInt(index[1])));
                if(game.isFlagged(Integer.parseInt(index[0]), Integer.parseInt(index[1]))){
                    ImageIcon icon = new ImageIcon("images/flag.jpg");
                    table.elementAt(Integer.parseInt(index[0])).elementAt(Integer.parseInt(index[1])).setIcon(icon);
                }
                else
                    table.elementAt(Integer.parseInt(index[0])).elementAt(Integer.parseInt(index[1])).setIcon(null);
            }
        }
        public void mouseExited(MouseEvent e) {
        }
        public void mousePressed(MouseEvent e) {
        }
    }



    public void run() {
        frame.setVisible(true);
    }

    private void displayCell(Integer row, Integer col) {
        game.setSelected(row, col);
        table.elementAt(row).elementAt(col).setEnabled(false);
        if (game.isBomb(row, col))
            table.elementAt(row).elementAt(col).setText("");
        else
            table.elementAt(row).elementAt(col).setText(game.getValue(row, col).toString());
    }

    private void flushGame(){
        if (game.isWon()) {
            fillIcon(true);
            JOptionPane.showMessageDialog(null, "You Won!", "GAME OVER!!", JOptionPane.PLAIN_MESSAGE);
        }
        else if (game.isLost()) {
            fillIcon(false);
            JOptionPane.showMessageDialog(null, "You Lose!", "GAME OVER!!", JOptionPane.PLAIN_MESSAGE);
        }
        frame.dispose();
    }

    private void fillIcon(boolean won){
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < index; j++) {
                table.elementAt(i).elementAt(j).setIcon(null);
                displayCell(i,j);
                if (won && game.isBomb(i,j)) {
                    table.elementAt(i).elementAt(j).setText("");
                    ImageIcon icon = new ImageIcon("images/bomb.jpg");
                    table.elementAt(i).elementAt(j).setIcon(icon);
                    table.elementAt(i).elementAt(j).setDisabledIcon(icon);
                }
                else if (!(won) && game.isBomb(i,j)) {
                    table.elementAt(i).elementAt(j).setText("");
                    ImageIcon icon = new ImageIcon("images/bomb_blast.jpg");
                    table.elementAt(i).elementAt(j).setIcon(icon);
                    table.elementAt(i).elementAt(j).setDisabledIcon(icon);
                }
            }
        }
    }

    private void displayBoard(Integer row, Integer col) {
        displayCell(row, col);
        if (game.isGameOver()) {

            flushGame();
        }
        else if (game.getValue(row, col) == 0) {
            if ((row > 0) && (col > 0) && (!(game.isSelected(row - 1, col - 1))))
                    displayBoard(row - 1, col - 1);

            if ((row > 0) && (col < (index - 1)) && (!(game.isSelected(row - 1, col + 1))))
                    displayBoard(row - 1, col + 1);

            if (row > 0 && (!(game.isSelected(row - 1, col))))
                    displayBoard(row - 1, col);

            if ((row < (index - 1)) && (col > 0) && (!(game.isSelected(row + 1, col - 1))))
                    displayBoard(row + 1, col - 1);

            if ((row < (index - 1)) && (col < (index - 1)) && (!(game.isSelected(row + 1, col + 1))))
                    displayBoard(row + 1, col + 1);

            if (row < (index - 1) && (!(game.isSelected(row + 1, col))))
                    displayBoard(row + 1, col);

            if (col > 0 && (!(game.isSelected(row, col - 1))))
                    displayBoard(row, col - 1);

            if (col < (index - 1) && (!(game.isSelected(row, col + 1))))
                    displayBoard(row, col + 1);
        }
    }
}