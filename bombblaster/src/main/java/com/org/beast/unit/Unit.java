package com.org.beast.unit;

/**
 * Created by User on 08-01-2017.
 */
public class Unit {
    Unit(boolean selected,boolean bomb)
    {
           this.selected=selected;
           this.bomb=bomb;
           this.value=0;
    }
    Unit()
    {
        this(false,false);
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean equals(Object obj)
    {
        Unit unit = (Unit) obj;
        return (this.selected==unit.isSelected() &&  this.bomb==unit.isBomb());
    }
    private boolean selected;
    private boolean flagged;
    private boolean bomb;

    public int getValue() {
        return value;
    }

    public void addValue() {
        this.value++;
    }

    private int value;
}
