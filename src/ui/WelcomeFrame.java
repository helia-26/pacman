package ui;
import util.Constants;
import javax.swing.*;
import java.awt.*;
import engine.GameEngine;
import javax.swing.JOptionPane;

public class WelcomeFrame extends JFrame {

    private JLabel titleLabel;
    private JLabel nameLabel;

    private JTextField nameField;

    private JButton startButton;
    private JButton exitButton;

    public WelcomeFrame() {

        initializeComponents();

        configureFrame();

        addComponents();

        setVisible(true);

        registerEvents();

    }

    private void initializeComponents() {

        titleLabel = new JLabel(Constants.GAME_TITLE);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameLabel = new JLabel("Player Name:");

        nameField = new JTextField(20);

        startButton = new JButton("Start Game");

        exitButton = new JButton("Exit");

    }

    private void configureFrame() {

        setTitle(Constants.GAME_TITLE);

        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        setLayout(new GridBagLayout());

    }

    private void addComponents() {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10,10,10,10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        gbc.gridy++;
        add(nameLabel, gbc);

        gbc.gridy++;
        add(nameField, gbc);

        gbc.gridy++;
        add(startButton, gbc);

        gbc.gridy++;
        add(exitButton, gbc);

    }

    private void registerEvents() {

        startButton.addActionListener(e -> {

            String playerName = nameField.getText().trim();

            if(playerName.isEmpty()){

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter your name."
                );

                return;
            }

            GameEngine game = new GameEngine(playerName);

            game.start();

            dispose();

        });

        exitButton.addActionListener(e -> System.exit(0));

    }

}