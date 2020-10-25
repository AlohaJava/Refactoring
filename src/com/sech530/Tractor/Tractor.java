package com.sech530.Tractor;

public class Tractor {

    Position tractorPosition = new Position(0,0);
    Field field = new Field(5,5);

    Orientation tractorOrientation = Orientation.NORTH;

    public void move(MoveCommand command) {
        if(command.equals(MoveCommand.FORWARD)){
            moveForwards();
        } else if (command.equals(MoveCommand.TURNCLOCKWISE)){
            turnClockwise();
        }

    }

    public void moveForwards() {
        tractorPosition.move(tractorOrientation);
        if (!field.isCorrectPositionForObject(tractorPosition)) {
            throw new TractorInDitchException();
        }
    }

    public void turnClockwise() {
        tractorOrientation = tractorOrientation.turnClockwise(tractorOrientation);
    }

    public int getPositionX() {
        return tractorPosition.getX();
    }

    public int getPositionY() {
        return tractorPosition.getY();
    }

    public Orientation getTractorOrientation() {
        return tractorOrientation;
    }
}