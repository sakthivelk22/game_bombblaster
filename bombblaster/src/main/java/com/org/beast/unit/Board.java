package com.org.beast.unit;

import java.util.Vector;
import java.util.Random;

/**
 * Created by User on 08-01-2017.
 */
public class Board {
    private Vector<Vector<Unit>> board;
    private Integer grid;

    public Board(Integer grid){
        this.grid=grid;
        board = new Vector<Vector<Unit>>(grid);
        for(int i=0;i<grid;i++)
        {
            Vector<Unit> a = new Vector<Unit>(grid);
            for(int j=0;j<grid;j++)
            {
                a.add(j,new Unit());
            }
            board.add(i,a);
        }
        implantBombs(grid);
    }

    private void implantBombs(Integer grid){
        Random random = new Random();
        //System.out.println("Bomb Implants");
        for (int i=0;i<grid;i++) {
            int row = random.nextInt(grid);
            int col = random.nextInt(grid);
            if (isBomb(row,col)==false) {
                board.elementAt(row).elementAt(col).setBomb(true);
                placeValues(row,col);
                //System.out.println(row+" "+col);
            }
            else
                i--;
        }
        //System.out.println("*******************************");
    }

    private void placeValues(int row,int col){
        if ((row>0)&&(col>0)){ board.elementAt(row-1).elementAt(col-1).addValue(); }
        if (row>0){ board.elementAt(row-1).elementAt(col).addValue(); }
        if ((row>0)&&(col<grid-1)) { board.elementAt(row-1).elementAt(col+1).addValue(); }
        if (col>0) { board.elementAt(row).elementAt(col-1).addValue(); }
        if (col<grid-1) { board.elementAt(row).elementAt(col+1).addValue(); }
        if ((row<grid-1)&&(col>0)){ board.elementAt(row+1).elementAt(col-1).addValue(); }
        if (row<grid-1){ board.elementAt(row+1).elementAt(col).addValue(); }
        if ((row<grid-1)&&(col<grid-1)) { board.elementAt(row+1).elementAt(col+1).addValue(); }
    }

    public boolean isBomb(Integer row,Integer col){
        return board.elementAt(row).elementAt(col).isBomb();
    }

    public boolean isFlagged(Integer row,Integer col){
        return board.elementAt(row).elementAt(col).isFlagged();
    }

    public boolean isSelected(Integer row,Integer col){
        return board.elementAt(row).elementAt(col).isSelected();
    }

    public Integer getValue(Integer row,Integer col){
        return board.elementAt(row).elementAt(col).getValue();
    }

    public void setFlagged(Integer row,Integer col,boolean flagged){
        board.elementAt(row).elementAt(col).setFlagged(flagged);
    }

    public void setSelected(Integer row,Integer col,boolean selected){
        board.elementAt(row).elementAt(col).setSelected(selected);
    }

    public boolean isBoardVoid()
    {
        Unit a = new Unit(true,true);
        for (int i=0;i<grid;i++)
        {
            if (board.elementAt(i).contains(a))
                return true;
        }
        return false;
    }

    public boolean isBoardComplete()
    {
        Unit a = new Unit(false,false);
        for (int i=0;i<grid;i++)
        {
            if (board.elementAt(i).contains(a))
                return false;
        }
        return true;
    }


}