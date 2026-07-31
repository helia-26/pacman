package engine;

import model.*;
import util.ScoreManager;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private Pacman pacman;

    private List<Ghost> ghosts;

    private Maze maze;

    private ScoreManager scoreManager;

    public GameEngine() {

        pacman = new Pacman(9 * 32, 15 * 32);

        ghosts = new ArrayList<>();

        maze = new Maze();

        scoreManager = new ScoreManager();

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

    public void update() {

        pacman.update(maze);

        for (Ghost ghost : ghosts) {

            ghost.update(maze);
        }

        Pellet pellet = maze.eatPellet(
                pacman.getPosition().getX(),
                pacman.getPosition().getY()
        );

        if (pellet != null) {
            scoreManager.addScore(pellet.getScore());
        }
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

}