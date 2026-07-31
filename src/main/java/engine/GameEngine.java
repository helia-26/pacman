package engine;

import model.*;
import util.ScoreManager;
import java.util.ArrayList;
import java.util.List;
//مهندسی و مدیریت حرکات و ...
public class GameEngine {

    private Pacman pacman;
    private List<Ghost> ghosts;
    private Maze maze;
    private ScoreManager scoreManager;

    public GameEngine() {
        //موقعیت مکانی پکمن
        pacman = new Pacman(9 * 32, 15 * 32);
        ghosts = new ArrayList<>();
        maze = new Maze();
        scoreManager = new ScoreManager();
        //موقعیت مکانی گوست ها
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

    public void update() {
        //آپدیت موقعیت پکمن
        pacman.update(maze);
        //آپدیت موقعیت گوست ها
        for (Ghost ghost : ghosts) {
            ghost.update(maze);
        }

        // بررسی برخورد گوست‌ها با پکمن
        for (Ghost ghost : ghosts) {
            if (isCollision(pacman, ghost)) {
                System.out.println("GAME OVER!");
                // فعلاً بازی را متوقف می‌کنیم
                return;
            }
        }
        //خوردن پلت ها
        Pellet pellet = maze.eatPellet(pacman.getPosition().getX(), pacman.getPosition().getY());
        //آپدیت شدن امتیاز هنگامی که پکمن به پلت رسید
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
}