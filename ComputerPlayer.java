package com.example.galzilca.battleship;

import java.util.Random;

public class ComputerPlayer {

    public int playTurn(Board board) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        int positionToPlay = random.nextInt(99);
        while(board.getTile(positionToPlay).getStatus() != TileState.NONE &&
                board.getTile(positionToPlay).getStatus() != TileState.SHIP) {
            positionToPlay = random.nextInt(99);
        }
        return positionToPlay;
    }

}
