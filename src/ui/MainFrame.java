package ui;
import util.Constants;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle(Constants.GAME_TITLE);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(Constants.RESIZABLE);
        setLocationRelativeTo(null);
    }
}
