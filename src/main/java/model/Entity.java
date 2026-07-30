package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected Position position;
    protected BufferedImage image;

    public Entity(Position position, BufferedImage image) {
        this.position = position;
        this.image = image;
    }

    public void draw(Graphics g) {

        if (image != null) {
            g.drawImage(
                    image,
                    position.getX(),
                    position.getY(),
                    32,
                    32,
                    null
            );
        }
    }

    public Position getPosition() {
        return position;
    }
}