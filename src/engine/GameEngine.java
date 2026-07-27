package engine;
import ui.MainFrame;

public class GameEngine {

    private MainFrame frame;

    public GameEngine() {
        frame = new MainFrame();
    }

    public void start() {
        frame.setVisible(true);
    }
}