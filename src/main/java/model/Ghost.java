package model;

import util.ImageLoader;

import java.util.Random;

public class Ghost extends Entity {

    private GhostType type;
    private Direction direction;
    private static final int SPEED = 2;
    private static final int TILE_SIZE = 32;
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

    public GhostType getType() {
        return type;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

        if (isCentered()) {

            chooseRandomDirection(maze);
        }
    }

    private void changeDirection(Maze maze) {

        chooseRandomDirection(maze);
    }

    private void chooseRandomDirection(Maze maze) {

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

    private boolean isCentered() {
        return position.getX() % TILE_SIZE == 0 && position.getY() % TILE_SIZE == 0;
    }
}