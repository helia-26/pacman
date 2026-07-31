package model;

public abstract class Pellet extends Entity {

    private int score;

    public Pellet(Position position, int score) {
        super(position, null);
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}