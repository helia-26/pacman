package ui;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame(String playerName) {

        setTitle("Pac-Man");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        add(new GamePanel(playerName));

        pack();

        setLocationRelativeTo(null);

        setVisible(true);
    }

}