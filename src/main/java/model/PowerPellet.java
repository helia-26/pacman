package model;

import java.awt.Color;
import java.awt.Graphics;

public class PowerPellet extends Pellet {

    public PowerPellet(Position position) {
        super(position, 50);
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(Color.WHITE);

        g.fillOval(
                position.getX() + 8,
                position.getY() + 8,
                16,
                16
        );
    }
}