package model;

import java.awt.Graphics;
import java.util.*;
//نقشه بازی
public class Maze {

    public static final int TILE_SIZE = 32;

    private static final String[] MAP = {

            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "X    XXXX XXXX    X",
            "X XX X       X XX X",
            "X XX X XX XX X XX X",
            "X       bpro      X",
            "X    X XXXXX X    X",
            "X XX X       X XX X",
            "X XX X XXXXX X XX X",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
    };

    private List<Wall> walls;
    private List<Pellet> pellets;

    public Maze() {

        walls = new ArrayList<>();
        pellets = new ArrayList<>();

        createMaze();
    }

    private void createMaze() {

        for (int row = 0; row < MAP.length; row++) {

            for (int col = 0; col < MAP[row].length(); col++) {

                char tile = MAP[row].charAt(col);

                Position position =
                        new Position(
                                col * TILE_SIZE,
                                row * TILE_SIZE
                        );

                switch (tile) {

                    case 'X':
                        walls.add(new Wall(position));
                        break;

                    case ' ':
                        pellets.add(new NormalPellet(position));
                        break;
                }
            }
        }
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Pellet> getPellets() {
        return pellets;
    }

    public void draw(Graphics g) {

        for (Wall wall : walls) {
            wall.draw(g);
        }

        for (Pellet pellet : pellets) {
            pellet.draw(g);
        }
    }

    public boolean canMove(int x, int y, int width, int height) {

        int left = x;
        int right = x + width - 1;
        int top = y;
        int bottom = y + height - 1;
        //چک کردن چهار گوشه
        return isFree(left, top)
                && isFree(right, top)
                && isFree(left, bottom)
                && isFree(right, bottom);
    }

    private boolean isFree(int x, int y) {
        //ستون
        int col = x / TILE_SIZE;
        //سطر
        int row = y / TILE_SIZE;

        if (row < 0 || row >= MAP.length || col < 0 || col >= MAP[0].length())
        {
            return false;
        }
        return MAP[row].charAt(col) != 'X';
    }

    public Pellet eatPellet(int x, int y) {
        //موقعیت پکمن برای خوردن پلت
        int pacmanCol = (x + 16) / TILE_SIZE;
        int pacmanRow = (y + 16) / TILE_SIZE;

        for (int i = 0; i < pellets.size(); i++) {
            Pellet pellet = pellets.get(i);
            int pelletCol = pellet.getPosition().getX() / TILE_SIZE;
            int pelletRow = pellet.getPosition().getY() / TILE_SIZE;
            if (pacmanCol == pelletCol && pacmanRow == pelletRow) {
                pellets.remove(i);
                return pellet;
            }
        }

        return null;
    }
}