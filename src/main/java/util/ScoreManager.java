package util;

public class ScoreManager {

    private int score;

    public ScoreManager() {
        score = 0;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        score = 0;
    }
}