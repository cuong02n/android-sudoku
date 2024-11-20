package com.cuong02n.sudoku2905;

public class infomation {
    private int x;
    private int y;
    private int current;

    public infomation(int x, int y, int current) {
        this.x = x;
        this.y = y;
        this.current = current;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
