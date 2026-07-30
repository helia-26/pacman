package ui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import engine.GameEngine;
import model.Ghost;

public class GamePanel extends JPanel {

    public static final int TILE_SIZE = 32;

    public static final int ROWS = 21;
    public static final int COLS = 19;

    private GameEngine engine;

    public GamePanel() {

        setPreferredSize(
                new Dimension(
                        COLS * TILE_SIZE,
                        ROWS * TILE_SIZE
                )
        );

        setBackground(Color.BLACK);
        setFocusable(true);

        engine = new GameEngine();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Draw the maze
        engine.getMaze().draw(g);

        // Draw Pacman
        engine.getPacman().draw(g);

        // Draw ghosts
        for (Ghost ghost : engine.getGhosts()) {
            ghost.draw(g);
        }
    }
}