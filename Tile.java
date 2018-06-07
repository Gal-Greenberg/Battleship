package com.example.galzilca.battleship;

enum TileState {
    NONE, SHIP, SHIP_DEAD, MARKED, ALL_SHIP_DEAD;

    @Override
    public String toString() {
        switch(this) {
            case NONE:
            case SHIP:
            default:
                return "";
            case MARKED:
                return "X";
            case SHIP_DEAD:
                return "V";
            case ALL_SHIP_DEAD:
                return "*";
        }
    }
}

public class Tile {

    private TileState status;

    public Tile() {
        status = TileState.NONE;
    }

    public void setStatus(TileState status) {
        this.status = status;
    }

    public TileState getStatus() {
        return status;
    }

}
