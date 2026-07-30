package model;

import util.ImageLoader;

public class Pacman extends Entity {

    private Direction direction;
    private Direction nextDirection;

    private static final int SPEED = 4;
    private static final int TILE_SIZE = 32;

    public Pacman(int x, int y) {

        super(
                new Position(x, y),
                ImageLoader.load("/image/pacmanRight.png")
        );

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

        // اگر دقیقاً روی مرکز یک خانه هستیم،
        // اول درخواست تغییر جهت را بررسی کن.
        if (isCentered()) {

            if (canMove(nextDirection, maze)) {
                direction = nextDirection;
            }
        }

        // اگر مسیر فعلی باز است، همیشه ادامه بده.
        if (canMove(direction, maze)) {

            move();

        } else {

            // به مرکز آخرین خانه برگرد
            snapToGrid();
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

        return maze.canMove(x, y, 28, 28);
    }

    private boolean isCentered() {

        return position.getX() % TILE_SIZE == 0
                && position.getY() % TILE_SIZE == 0;
    }

    private void snapToGrid() {

        int x = Math.round(
                position.getX() / (float) TILE_SIZE
        ) * TILE_SIZE;

        int y = Math.round(
                position.getY() / (float) TILE_SIZE
        ) * TILE_SIZE;

        position = new Position(x, y);
    }

    private void updateImage() {

        switch (direction) {

            case UP:
                image = ImageLoader.load(
                        "/image/pacmanUp.png"
                );
                break;

            case DOWN:
                image = ImageLoader.load(
                        "/image/pacmanDown.png"
                );
                break;

            case LEFT:
                image = ImageLoader.load(
                        "/image/pacmanLeft.png"
                );
                break;

            case RIGHT:
                image = ImageLoader.load(
                        "/image/pacmanRight.png"
                );
                break;
        }
    }
}