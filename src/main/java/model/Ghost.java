package model;

import util.ImageLoader;

import java.util.Random;

public class Ghost extends Entity {

    private static final int SPEED = 2;
    private static final int TILE_SIZE = 32;
    private GhostType type;
    private Direction direction;
    private Random random = new Random();

    public Ghost(int x, int y, GhostType type) {

        super(new Position(x, y), null);
        this.type = type;
        this.direction = Direction.LEFT;

        switch (type) {

            case RED:
                image = ImageLoader.load("/image/redGhost.png");
                break;

            case PINK:
                image = ImageLoader.load("/image/pinkGhost.png");
                break;

            case BLUE:
                image = ImageLoader.load("/image/blueGhost.png");
                break;

            case ORANGE:
                image = ImageLoader.load("/image/orangeGhost.png");
                break;
        }
    }

    public void update(Maze maze) {

        int x = position.getX();
        int y = position.getY();

        int newX = x;
        int newY = y;

        switch (direction) {

            case UP:
                newY -= SPEED;
                break;

            case DOWN:
                newY += SPEED;
                break;

            case LEFT:
                newX -= SPEED;
                break;

            case RIGHT:
                newX += SPEED;
                break;
        }

        if (maze.canMove(newX, newY, 28, 28)) {
            position = new Position(newX, newY);
        } else {
            changeDirection(maze);
        }
    }

    private void changeDirection(Maze maze) {
        Direction[] directions = {
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT
        };

        for (int i = 0; i < directions.length; i++) {
            Direction newDirection = directions[random.nextInt(directions.length)];
            if (canMove(newDirection, maze)) {
                direction = newDirection;
                return;
            }
        }
    }

    private boolean canMove(Direction direction, Maze maze) {

        int x = position.getX();
        int y = position.getY();

        switch (direction) {

            case UP:
                y -= SPEED;
                break;

            case DOWN:
                y += SPEED;
                break;

            case LEFT:
                x -= SPEED;
                break;

            case RIGHT:
                x += SPEED;
                break;
        }
        return maze.canMove(x, y, 28, 28);
    }
}