package model;

import util.ImageLoader;

public class Ghost extends Entity {

    private GhostType type;
    private Direction direction;

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

}