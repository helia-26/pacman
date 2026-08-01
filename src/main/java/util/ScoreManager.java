package util;

public class ScoreManager {

    private int score;
    private int highScore;

    public ScoreManager() {
        score = 0;
        highScore = 0;
    }

    public void addScore(int amount) {
        score += amount;

        if (score > highScore) {
            highScore = score;
        }
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void reset() {
        score = 0;
    }
}