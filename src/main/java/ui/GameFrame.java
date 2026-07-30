package ui;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {

        setTitle("Pac-Man");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        add(new GamePanel());

        pack();

        setLocationRelativeTo(null);

        setVisible(true);
    }

}