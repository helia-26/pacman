package model;

import util.ImageLoader;

public class Pacman extends Entity {

    private Direction direction;
    private Direction nextDirection;
    private static final int SPEED = 3;
    private static final int TILE_SIZE = 32;

    public Pacman(int x, int y) {
        super(new Position(x, y), ImageLoader.load("/image/pacmanRight.png"));
        direction = Direction.RIGHT;
        nextDirection = Direction.RIGHT;
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
        else if (!canMove(direction, maze)) {
        }

        move(maze);

        updateImage();
    }

    private void move(Maze maze) {
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

        return maze.canMove(x, y, 30, 30);
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