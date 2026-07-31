package engine;

import model.*;
import util.ScoreManager;
import java.util.*;

public class GameEngine {

    private Pacman pacman;
    private List<Ghost> ghosts;
    private Maze maze;
    private ScoreManager scoreManager;
    private int lives;
    private boolean gameOver;
    private static final int MAX_LIVES = 3;
    private static final int PACMAN_START_X = 9 * 32;
    private static final int PACMAN_START_Y = 15 * 32;

    public GameEngine() {
        pacman = new Pacman(
                PACMAN_START_X,
                PACMAN_START_Y
        );
        ghosts = new ArrayList<>();
        maze = new Maze();
        scoreManager = new ScoreManager();
        lives = MAX_LIVES;
        gameOver = false;
        ghosts.add(new Ghost(8 * 32, 9 * 32, GhostType.RED));
        ghosts.add(new Ghost(9 * 32, 9 * 32, GhostType.PINK));
        ghosts.add(new Ghost(10 * 32, 9 * 32, GhostType.BLUE));
        ghosts.add(new Ghost(11 * 32, 9 * 32, GhostType.ORANGE));
    }

    public Pacman getPacman() {
        return pacman;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public Maze getMaze() {
        return maze;
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public int getLives() {
        return lives;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void update() {
        if (gameOver) {
            return;
        }
        pacman.update(maze);
        for (Ghost ghost : ghosts) {
            ghost.update(maze);
        }
        for (Ghost ghost : ghosts) {
            if (isCollision(pacman, ghost)) {
                lives--;
                System.out.println("Lives: " + lives);
                if (lives <= 0) {
                    gameOver = true;
                    System.out.println("GAME OVER!");
                    return;
                }
                resetPositions();
                break;
            }
        }
        Pellet pellet = maze.eatPellet(pacman.getPosition().getX(), pacman.getPosition().getY());
        if (pellet != null) {
            scoreManager.addScore(pellet.getScore());
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

    private void resetPositions() {
        pacman.setPosition(new Position(PACMAN_START_X, PACMAN_START_Y));
        ghosts.get(0).setPosition(new Position(8 * 32, 9 * 32));
        ghosts.get(1).setPosition(new Position(9 * 32, 9 * 32));
        ghosts.get(2).setPosition(new Position(10 * 32, 9 * 32));
        ghosts.get(3).setPosition(new Position(11 * 32, 9 * 32));
    }

    public void resetGame() {
        lives = MAX_LIVES;
        gameOver = false;
        resetPositions();
    }
}