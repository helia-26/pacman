package ui;

import util.Constants;

import javax.swing.*;

public class MainFrame extends JFrame {

    private GamePanel gamePanel;

    public MainFrame(String playerName) {

        setTitle(Constants.GAME_TITLE + " - " + playerName);

        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        gamePanel = new GamePanel();

        add(gamePanel);

    }

}