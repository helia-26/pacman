package model;

import util.ImageLoader;

public class Pacman extends Entity {

    private static final int SPEED = 4;
    private static final int TILE_SIZE = 32;
    private static final int SIZE = 32;

    private Direction direction;
    private Direction nextDirection;

    public Pacman(int x, int y) {

        super(new Position(x, y), ImageLoader.load("/image/pacmanRight.png"));

        direction = Direction.NONE;
        nextDirection = Direction.NONE;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setNextDirection(Direction direction) {
        this.nextDirection = direction;
    }

    public void update(Maze maze) {

        if (canMove(nextDirection, maze)) {
            direction = nextDirection;
        }

        if (canMove(direction, maze)) {
            move();
        }

        updateImage();
    }

    private void move() {

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

        position = new Position(x, y);
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

        return maze.canMove(x, y, SIZE, SIZE);
    }

    private void updateImage() {

        switch (direction) {

            case UP:
                image = ImageLoader.load("/image/pacmanUp.png");
                break;

            case DOWN:
                image = ImageLoader.load("/image/pacmanDown.png");
                break;

            case LEFT:
                image = ImageLoader.load("/image/pacmanLeft.png");
                break;

            case RIGHT:
                image = ImageLoader.load("/image/pacmanRight.png");
                break;
        }
    }
}