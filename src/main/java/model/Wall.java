package model;

import util.ImageLoader;

public class Wall extends Entity {

    public Wall(Position position) {
        super(
                position,
                ImageLoader.load("/image/wall.png")
        );
    }
}