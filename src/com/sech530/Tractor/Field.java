package com.sech530.Tractor;

public class Field {
    private int x;
    private int y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isCorrectPositionForObject(Position tractorPos) {
        return (tractorPos.getX() >= 0 && tractorPos.getX() <= x) && (tractorPos.getY() >= 0 && tractorPos.getY() <= y);
    }
}
