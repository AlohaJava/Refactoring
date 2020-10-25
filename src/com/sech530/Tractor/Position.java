package com.sech530.Tractor;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        x = 0;
        y = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(Orientation orientation) {
        switch (orientation) {
            case EAST:
                x++;
                break;
            case WEST:
                x--;
                break;
            case NORTH:
                y++;
                break;
            case SOUTH:
                y--;
                break;
        }
    }

}
