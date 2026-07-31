package model;

import util.ImageLoader;

public class Ghost extends Entity {

    private GhostType type;
    private Direction direction;

    private static final int SPEED = 2;
    private static final int TILE_SIZE = 32;

    public Ghost(int x, int y, GhostType type) {

        super(new Position(x, y), null);

        this.type = type;
        this.direction = Direction.LEFT;

        switch (type) {

            case RED ->
                    image = ImageLoader.load("/image/redGhost.png");

            case PINK ->
                    image = ImageLoader.load("/image/pinkGhost.png");

            case BLUE ->
                    image = ImageLoader.load("/image/blueGhost.png");

            case ORANGE ->
                    image = ImageLoader.load("/image/orangeGhost.png");
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

        if (isCentered()) {

            chooseDirection(maze);
        }

        move(maze);
    }

    private void move(Maze maze) {

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

        if (maze.canMove(x, y, 28, 28)) {

            position = new Position(x, y);
        }
    }

    private void chooseDirection(Maze maze) {

        Direction[] directions = Direction.values();

        Direction newDirection;

        do {

            newDirection =
                    directions[(int) (Math.random() * directions.length)];

        } while (
                newDirection == getOpposite(direction)
                        || !canMove(newDirection, maze)
        );

        direction = newDirection;
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

    private Direction getOpposite(Direction direction) {

        return switch (direction) {

            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
        };
    }
}