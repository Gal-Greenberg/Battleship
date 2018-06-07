package com.example.galzilca.battleship;

enum Turn {
    PLAYER1, PLAYER2;
}

public class Game {

    private Board boardPlayer1;
    private Board boardPlayer2;
    private ComputerPlayer mComputerPlayer;
    private Turn turn;

    public Game() {
        boardPlayer1 = new Board();
        boardPlayer2 = new Board();
        mComputerPlayer = new ComputerPlayer();
        turn =  Turn.PLAYER1;
    }

    public void playTile(int position) {
        switch (turn) {
            case PLAYER1:
                boardPlayer1.setTile(position);
                boardPlayer1.checkShips();
                break;
            case PLAYER2:
                boardPlayer2.setTile(position);
                boardPlayer2.checkShips();
                break;
        }
    }

    public void playComputer() {
        int positionToPlay = mComputerPlayer.playTurn(boardPlayer2);
        playTile(positionToPlay);
    }

    public boolean isWon() {
        switch (turn) {
            case PLAYER1:
                if (!boardPlayer1.isWon()) {
                    turn = Turn.PLAYER2;
                    return false;
                }
                return true;
            case PLAYER2:
                if (!boardPlayer2.isWon()) {
                    turn = Turn.PLAYER1;
                    return false;
                }
                return true;
        }
        return true;
    }

    public void setDifficulty(String difficulty) {
        boardPlayer1.setBoard(difficulty);
        boardPlayer2.setBoard(difficulty);
    }

    public Board getBoardPlayer1() {
        return boardPlayer1;
    }

    public Board getBoardPlayer2() {
        return boardPlayer2;
    }

}
