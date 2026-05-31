package frames;
import gameConfig.GameConfig;
import gameConfig.ConfigManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private JPanel mainMenu;
    private GameConfig config;

    public MainFrame() {
        config = ConfigManager.load();

        setTitle("Tic-Tac-Toe");
        setSize(1200, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainMenu = new JPanel();
        mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Tic-Tac-Toe");
        label.setFont(new Font("Arial", Font.BOLD, 80));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenu.add(label);
        mainMenu.add(Box.createVerticalStrut(80));

        JButton play = new JButton("PLAY");
        play.setFont(new Font("Arial", Font.BOLD, 20));
        play.setFocusPainted(false);
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        play.setMaximumSize(new Dimension(350, 70));
        play.setPreferredSize(new Dimension(350, 70));
        play.addActionListener(e -> showPlay());
        mainMenu.add(play);
        mainMenu.add(Box.createVerticalStrut(25));

        JButton settings = new JButton("SETTINGS");
        settings.setFont(new Font("Arial", Font.BOLD, 20));
        settings.setFocusPainted(false);
        settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        settings.setMaximumSize(new Dimension(350, 70));
        settings.setPreferredSize(new Dimension(350, 70));
        settings.addActionListener(e -> showSettings());
        mainMenu.add(settings);
        mainMenu.add(Box.createVerticalStrut(25));

        JButton stats = new JButton("STATISTICS");
        stats.setFont(new Font("Arial", Font.BOLD, 20));
        stats.setFocusPainted(false);
        stats.setAlignmentX(Component.CENTER_ALIGNMENT);
        stats.setMaximumSize(new Dimension(350, 70));
        stats.setPreferredSize(new Dimension(350, 70));
        stats.addActionListener(e -> showStatistics());
        mainMenu.add(stats);
        mainMenu.add(Box.createVerticalStrut(25));

        JButton exit = new JButton("EXIT");
        exit.setFont(new Font("Arial", Font.BOLD, 20));
        exit.setFocusPainted(false);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setMaximumSize(new Dimension(350, 70));
        exit.setPreferredSize(new Dimension(350, 70));
        exit.addActionListener(e -> dispose());
        mainMenu.add(exit);

        mainMenu.setVisible(true);
        add(mainMenu);

        setVisible(true);
    }

    public void showPlay() {
        new Play(config);
        revalidate();
        repaint();
    }

    public void showSettings() {
        new Settings(this);
        revalidate();
        repaint();
    }

    public void showStatistics() {
        new Statistics(this);
        revalidate();
        repaint();
    }

    public GameConfig getConfig() {
        return config;
    }
}