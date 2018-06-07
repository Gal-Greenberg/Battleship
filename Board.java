package com.example.galzilca.battleship;

import java.util.Random;

enum Difficulty {
    EASY, NORMAL, HARD;
}

public class Board {

    public static final int SIZE = 100;

    private Difficulty difficulty;
    private Tile[] tiles;
    private Ship[] ships;

    public Board() {
        tiles = new Tile[SIZE];
        for (int i = 0; i < SIZE; i++) {
            tiles[i] = new Tile();
        }
    }

    public void setTile(int position) {
        switch (tiles[position].getStatus()) {
            case SHIP:
                tiles[position].setStatus(TileState.SHIP_DEAD);
                break;
            case NONE:
                tiles[position].setStatus(TileState.MARKED);
                break;
        }
    }

    public void setBoard(String difficulty) {
        int count = 0;
        switch (difficulty) {
            case "Easy":
                setDifficulty(Difficulty.EASY);
                ships = new Ship[6];
                easyShips(count);
                break;
            case "Normal":
                setDifficulty(Difficulty.NORMAL);
                ships = new Ship[8];
                count = easyShips(count);
                normalShips(count);
                break;
            case "Hard":
                setDifficulty(Difficulty.HARD);
                ships = new Ship[10];
                count = easyShips(count);
                count = normalShips(count);
                hardShips(count);
                break;
        }
    }

    public int easyShips(int count) {
        int randomTileStart = new Random().nextInt(SIZE);
        Direction direction = setRandomDirection();
        for (int i = 0; i < 2; i++) {
            while (!isShipPlacedCorrect(randomTileStart, direction, 3)) {
                direction = setRandomDirection();
                randomTileStart = new Random().nextInt(SIZE);
            }
            ships[count] = new Ship(3, randomTileStart, direction);
            setTilesWithShip(randomTileStart, direction, 3);
            count++;
        }
        for (int i = 0; i < 2; i++) {
            while (!isShipPlacedCorrect(randomTileStart, direction, 4)) {
                direction = setRandomDirection();
                randomTileStart = new Random().nextInt(SIZE);
            }
            ships[count] = new Ship(4, randomTileStart, direction);
            setTilesWithShip(randomTileStart, direction, 4);
            count++;
        }
        for (int i = 0; i < 2; i++) {
            while (!isShipPlacedCorrect(randomTileStart, direction, 5)) {
                direction = setRandomDirection();
                randomTileStart = new Random().nextInt(SIZE);
            }
            ships[count] = new Ship(5, randomTileStart, direction);
            setTilesWithShip(randomTileStart, direction, 5);
            count++;
        }
        return count;
    }

    public int normalShips(int count) {
        int randomTileStart = new Random().nextInt(SIZE);
        Direction direction = setRandomDirection();
        for (int i = 0; i < 2; i++) {
            while (!isShipPlacedCorrect(randomTileStart, direction, 2)) {
                direction = setRandomDirection();
                randomTileStart = new Random().nextInt(SIZE);
            }
            ships[count] = new Ship(2, randomTileStart, direction);
            setTilesWithShip(randomTileStart, direction, 2);
            count++;
        }
        return count;
    }

    public int hardShips(int count) {
        int randomTileStart = new Random().nextInt(SIZE);;
        Direction direction = setRandomDirection();
        for (int i = 0; i < 2; i++) {
            while (!isShipPlacedCorrect(randomTileStart, direction, 1)) {
                direction = setRandomDirection();
                randomTileStart = new Random().nextInt(SIZE);
            }
            ships[count] = new Ship(1, randomTileStart, direction);
            setTilesWithShip(randomTileStart, direction, 1);
            count++;
        }
        return count;
    }

    public Direction setRandomDirection() {
        int randomDirection = (new Random()).nextInt(2);
        Direction direction = null;
        switch (randomDirection) {
            case 0:
                direction = Direction.HORIZONTAL;
                break;
            case 1:
                direction = Direction.VERTICAL;
                break;
        }
        return direction;
    }

    public boolean isShipPlacedCorrect(int tileStart, Direction direction, int shipSize) {
        int shipInEdge;
        int jump;
        if (direction == Direction.HORIZONTAL) {
            jump = 1;
            for (int i = SIZE - shipSize; i >= 10 - shipSize; i -= 10) {
                if (i < tileStart)
                    return false;
            }
        } else {
            jump = 10;
            shipInEdge = SIZE-((shipSize-1)*10);
            if (tileStart > shipInEdge)
                return false;
        }
        if (tileStart+shipSize*jump > SIZE)
            return false;
        for (int i = tileStart; i < tileStart+shipSize*jump; i += jump) {
            if (tiles[i].getStatus() == TileState.SHIP)
                return false;
        }
        return true;
    }

    public void setTilesWithShip(int tileStart, Direction direction, int shipSize) {
        int jump = 1;
        if (direction == Direction.VERTICAL)
            jump = 10;
        for (int i = tileStart; i < tileStart+shipSize*jump; i += jump) {
            tiles[i].setStatus(TileState.SHIP);
        }
    }

    public void checkShips() {
        int jump = 1;
        for (int i = 0; i < ships.length; i++) {
            boolean isDead = true;
            Direction dire = ships[i].getShipDirection();
            switch (dire) {
                case VERTICAL:
                    jump = 10;
                    break;
                case HORIZONTAL:
                    jump = 1;
                    break;
            }
            int start = ships[i].getTileStart();
            for (int j = 0; j < ships[i].getShipSize(); j++) {
                if (tiles[start+jump*j].getStatus() != TileState.SHIP_DEAD) {
                    isDead = false;
                    break;
                }
            }
            if (isDead) {
                ships[i].setIsDead(isDead);
                drawShipIsDead(start, dire, ships[i].getShipSize(), jump);
            }
        }
    }

    public void drawShipIsDead(int tileStart, Direction direction, int shipSize, int jump) {
        for (int i = tileStart; i < tileStart+shipSize*jump; i += jump) {
            tiles[i].setStatus(TileState.ALL_SHIP_DEAD);
        }
    }

    public boolean isWon() {
        for (int i = 0; i < ships.length; i++) {
            if (!ships[i].getIsDead())
                return false;
        }
        return true;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty selectedDifficult) {
        difficulty = selectedDifficult;
    }

    public Tile getTile(int position) {
        return tiles[position];
    }

    public int getBoardSize() {
        return SIZE;
    }

}
