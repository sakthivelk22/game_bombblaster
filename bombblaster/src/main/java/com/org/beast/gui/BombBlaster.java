package com.org.beast.gui;

/**
 * Created by User on 08-01-2017.
 */
public class BombBlaster {
    private Integer index;

    public void run(){
        MainWindow createMainWindow = new MainWindow();
        createMainWindow.run();
    }

    public static void main (String args[])
    {
        BombBlaster game = new BombBlaster();
        game.run();
    }
}
