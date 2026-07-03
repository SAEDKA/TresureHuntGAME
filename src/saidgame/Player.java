package saidgame;

public class Player {
    public String name;
    public int score;
    public int position;
//    public boolean skipTurn = false; // لحالة الدوامة

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.position = 0;
    }

    public void move(int steps, int boardSize) {

        position += steps;
        if (position < 0) position = 0;
        if (position > boardSize) position = boardSize;
    }
}
