package model;

import util.ImageLoader;

public class Pacman extends Entity {

    public Pacman(int x, int y) {
        super(
                new Position(x, y),
                ImageLoader.load("/image/pacmanRight.png")
        );
    }
}