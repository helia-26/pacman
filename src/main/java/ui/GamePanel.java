package ui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import engine.GameEngine;
import model.Ghost;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.Direction;
import javax.swing.Timer;

public class GamePanel extends JPanel {

    public static final int TILE_SIZE = 32;

    public static final int ROWS = 21;
    public static final int COLS = 19;

    private GameEngine engine;

    public GamePanel() {

        setPreferredSize(new Dimension(COLS * TILE_SIZE, ROWS * TILE_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();
        engine = new GameEngine();

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {

                    case KeyEvent.VK_UP:
                        engine.getPacman().setNextDirection(Direction.UP);
                        break;

                    case KeyEvent.VK_DOWN:
                        engine.getPacman().setNextDirection(Direction.DOWN);
                        break;

                    case KeyEvent.VK_LEFT:
                        engine.getPacman().setNextDirection(Direction.LEFT);
                        break;

                    case KeyEvent.VK_RIGHT:
                        engine.getPacman().setNextDirection(Direction.RIGHT);
                        break;
                }
            }
        });

        Timer timer = new Timer(30, e -> {
            engine.update();
            repaint();
        });
        timer.start();
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Draw the maze
        engine.getMaze().draw(g);
        engine.getPacman().draw(g);
        for (Ghost ghost : engine.getGhosts()) {
            ghost.draw(g);
        }

        g.setColor(Color.WHITE);
        g.drawString("SCORE: " + engine.getScoreManager().getScore(), 10, 20);
    }
}