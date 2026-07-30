package engine;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private Pacman pacman;

    private List<Ghost> ghosts;

    private Maze maze;

    public GameEngine() {

        pacman = new Pacman(9 * 32, 15 * 32);

        ghosts = new ArrayList<>();

        maze = new Maze();

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

}