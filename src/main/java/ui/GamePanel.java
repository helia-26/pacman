package ui;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import engine.GameEngine;
import model.*;

public class GamePanel extends JPanel {

    public static final int TILE_SIZE = 32;
    public static final int ROWS = 21;
    public static final int COLS = 19;
    private GameEngine engine;
    private Timer timer;

    public GamePanel() {

        setPreferredSize(new Dimension(COLS * TILE_SIZE, ROWS * TILE_SIZE + 45));
        setBackground(Color.BLACK);
        setFocusable(true);
        engine = new GameEngine();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (engine.isGameOver()) {
                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        engine.resetGame();
                        requestFocusInWindow();
                        repaint();
                    }

                    else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        System.exit(0);
                    }
                    return;
                }

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

        timer = new Timer(30, e -> {
            engine.update();
            repaint();
        });
        timer.start();
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        engine.getMaze().draw(g);
        engine.getPacman().draw(g);

        for (Ghost ghost : engine.getGhosts()) {
            ghost.draw(g);
        }

        drawHUD(g);

        if (engine.isGameOver()) {
            drawGameOver(g);
        }
    }

    private void drawHUD(Graphics g) {
        int hudY = ROWS * TILE_SIZE;
        g.setColor(Color.BLACK);
        g.fillRect(0, hudY, getWidth(), 45);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("SCORE: " + engine.getScoreManager().getScore(), 10, hudY + 27);
        g.drawString("LIVES:", 180, hudY + 27);
        drawLives(g, 235, hudY + 8);
    }

    private void drawLives(Graphics g, int startX, int startY) {
        int lives = engine.getLives();
        for (int i = 0; i < lives; i++) {
            drawMiniPacman(g, startX + i * 28, startY);
        }
    }

    private void drawMiniPacman(Graphics g, int x, int y) {
        g.setColor(Color.YELLOW);
        g.fillArc(x, y, 22, 22, 40, 280);
    }

    private void drawGameOver(Graphics g) {
        int width = COLS * TILE_SIZE;
        int height = ROWS * TILE_SIZE;

        g.fillRect(0, 0, width, height);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 38));
        String gameOverText = "GAME OVER";

        int textWidth = g.getFontMetrics().stringWidth(gameOverText);

        g.drawString(gameOverText, (width - textWidth) / 2, height / 2 - 45);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        String retryText = "Press R to Retry";

        int retryWidth = g.getFontMetrics().stringWidth(retryText);

        g.drawString(retryText, (width - retryWidth) / 2, height / 2 + 5);

        String exitText = "Press ESC to Exit";

        int exitWidth = g.getFontMetrics().stringWidth(exitText);

        g.drawString(exitText, (width - exitWidth) / 2, height / 2 + 38);
    }
}