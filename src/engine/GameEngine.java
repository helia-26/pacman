package engine;

import ui.MainFrame;

public class GameEngine {

    private MainFrame gameFrame;

    public GameEngine(String playerName) {

        gameFrame = new MainFrame(playerName);

    }

    public void start() {

        gameFrame.setVisible(true);

    }

}