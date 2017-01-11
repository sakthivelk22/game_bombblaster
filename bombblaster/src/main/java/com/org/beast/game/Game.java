package com.org.beast.game;

import com.org.beast.unit.Board;

/**
 * Created by User on 08-01-2017.
 */
public class Game {
    private Board board;
    public Game(Integer grid)
    {
        board = new Board(grid);
    }

    public boolean isBomb(Integer row,Integer col){
        return board.isBomb(row,col);
    }

    public boolean isFlagged(Integer row,Integer col){
        return board.isFlagged(row,col);
    }

    public void setFlagged(Integer row,Integer col,boolean flagged){  board.setFlagged(row,col,flagged);   }

    public boolean isSelected(Integer row,Integer col){
        return board.isSelected(row,col);
    }

    public void setSelected(Integer row,Integer col){  board.setSelected(row,col,true);   }

    public Integer getValue(Integer row,Integer col){ return board.getValue(row,col); }

    public boolean isLost(){ return board.isBoardVoid();  }

    public boolean isWon(){
        return board.isBoardComplete();
    }

    public boolean isGameOver(){
        return isLost() || isWon();
    }
}
