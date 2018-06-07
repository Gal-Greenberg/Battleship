package com.example.galzilca.battleship;

enum Direction {
    VERTICAL, HORIZONTAL;
}

public class Ship {

    private boolean isDead;
    private int shipSize;
    private int tileStart;
    private Direction shipDirection;

    public Ship(int size, int start, Direction direction) {
        isDead = false;
        shipSize = size;
        tileStart = start;
        shipDirection = direction;
    }

    public Direction getShipDirection() {
        return shipDirection;
    }

    public int getShipSize() {
        return shipSize;
    }

    public int getTileStart() {
        return tileStart;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void setIsDead(boolean shipStatus) {
        isDead = shipStatus;
    }

}
