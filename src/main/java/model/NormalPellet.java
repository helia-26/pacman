package model;

import java.awt.*;
//کشیدن پلت ها
public class NormalPellet extends Pellet {

    public NormalPellet(Position position) {
        super(position, 10);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(position.getX() + 13, position.getY() + 13, 6, 6);
    }
}