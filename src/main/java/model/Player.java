package model;
//حساب کردن امتیاز و ... بازیکن
public class Player {

    private String name;
    private int score;
    private int lives;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.lives = 3;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void addScore(int value) {
        score += value;
    }

    public void loseLife() {
        lives--;
    }

}