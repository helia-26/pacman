package Main;

import javax.swing.SwingUtilities;
import ui.WelcomeFrame;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new WelcomeFrame();
        });
    }
}