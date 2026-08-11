package engine;

import database.PlayerDAO;
import model.*;
import util.ScoreManager;
import java.util.*;
public class GameEngine {

    private Pacman pacman;
    private List<Ghost> ghosts;
    private Maze maze;
    private ScoreManager scoreManager;
    private PlayerDAO playerDAO;
    private String playerName;

    private int savedHighScore;
    private int highScore;
    private int lives;

    private boolean gameOver;
    private boolean gameWon;

    private static final int MAX_LIVES = 3;
    private static final int PACMAN_START_X = 9 * 32;
    private static final int PACMAN_START_Y = 15 * 32;

    public GameEngine(String playerName) {

        this.playerName = playerName;

        playerDAO = new PlayerDAO();
        playerDAO.createTable();

        playerDAO.savePlayer(playerName);

        highScore = playerDAO.getHighScore(playerName);
        savedHighScore = highScore;

        System.out.println("Starting game for: " + playerName);
        System.out.println("Loaded High Score: " + highScore);

        pacman = new Pacman(PACMAN_START_X, PACMAN_START_Y);
        ghosts = new ArrayList<>();
        maze = new Maze();
        scoreManager = new ScoreManager();
        lives = MAX_LIVES;
        gameOver = false;
        gameWon = false;

        ghosts.add(new Ghost(8 * 32, 9 * 32, GhostType.RED));

        ghosts.add(new Ghost(9 * 32, 9 * 32, GhostType.PINK));

        ghosts.add(new Ghost(10 * 32, 9 * 32, GhostType.BLUE));

        ghosts.add(new Ghost(11 * 32, 9 * 32, GhostType.ORANGE));
    }

    public Pacman getPacman() {return pacman;}

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public Maze getMaze() {
        return maze;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getLives() {
        return lives;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void update() {

        if (gameOver) {
            return;
        }

        pacman.update(maze);

        if(pacman.movedTile()){
            scoreManager.addScore(-1);
        }

        for (Ghost ghost : ghosts) {
            ghost.update(maze);
        }

        for (Ghost ghost : ghosts) {

            if (isCollision(pacman, ghost)) {
                lives--;

                if (lives <= 0) {
                    gameOver = true;
                    saveScore();
                    return;
                }
                resetPositions();
                break;
            }
        }

        Pellet pellet = maze.eatPellet(pacman.getPosition().getX(), pacman.getPosition().getY());

        if (pellet != null) {
            scoreManager.addScore(pellet.getScore());

            if (scoreManager.getScore() > highScore) {
                highScore = scoreManager.getScore();
            }

            if (maze.isCompleted()) {

                scoreManager.addScore(500);

                if (scoreManager.getScore() > highScore) {
                    highScore = scoreManager.getScore();
                }
                gameWon = true;
                saveScore();
                return;
            }
        }
    }

    private boolean isCollision(Pacman pacman, Ghost ghost) {

        int pacmanX = pacman.getPosition().getX();

        int pacmanY = pacman.getPosition().getY();

        int ghostX = ghost.getPosition().getX();

        int ghostY = ghost.getPosition().getY();

        int distanceX = Math.abs(pacmanX - ghostX);

        int distanceY = Math.abs(pacmanY - ghostY);

        return distanceX < 24 && distanceY < 24;
    }

    private void saveScore() {
        int currentScore = scoreManager.getScore();

        if (currentScore > savedHighScore) {
            savedHighScore = currentScore;
            highScore = currentScore;
            playerDAO.updateHighScore(playerName, currentScore);
        }
    }

    public void resetGame() {
        lives = MAX_LIVES;
        gameOver = false;
        gameWon = false;
        scoreManager.reset();
        maze = new Maze();
        resetPositions();
    }

    private void resetPositions() {

        pacman.setPosition(new Position(PACMAN_START_X, PACMAN_START_Y));

        ghosts.get(0).setPosition(new Position(8 * 32, 9 * 32));

        ghosts.get(1).setPosition(new Position(9 * 32, 9 * 32));

        ghosts.get(2).setPosition(new Position(10 * 32, 9 * 32));

        ghosts.get(3).setPosition(new Position(11 * 32, 9 * 32));
    }
}