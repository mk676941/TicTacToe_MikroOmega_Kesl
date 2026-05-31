package frames;
import gameConfig.GameConfig;
import gameBoard.BoardConfig;
import gameBoard.GamePresets;
import gameConfig.ConfigManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class Settings extends JFrame {
    private JPanel settingsMenu;
    private JPanel titles;
    private JComboBox<BoardConfig> difficultyBox;
    private JTextField nameField;
    private JButton setDifficulty;
    private MainFrame mainFrame;
    private JLabel color1;
    private JLabel color2;

    public Settings(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setTitle("Settings");
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        settingsMenu = new JPanel();
        settingsMenu.setLayout((new BoxLayout(settingsMenu, BoxLayout.Y_AXIS)));
        settingsMenu.setBorder(BorderFactory.createEmptyBorder(50, 50, 50 ,150));

        BoardConfig[] difficulties = {GamePresets.DEFAULT, GamePresets.EASY, GamePresets.MEDIUM, GamePresets.HARD};

        difficultyBox = new JComboBox<>(difficulties);

        difficultyBox.setMaximumSize(new Dimension(450, 70));
        for (BoardConfig preset : difficulties) {
            if (preset.getName().equals(mainFrame.getConfig().getBoard().getName())) {
                difficultyBox.setSelectedItem(preset);
            }
        }

        difficultyBox.setPreferredSize(new Dimension(450, 70));
        difficultyBox.setFont(new Font("Arial", Font.BOLD, 20));
        difficultyBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        difficultyBox.addActionListener(e -> {
            if (mainFrame.getConfig().getBoard().equals(difficultyBox.getSelectedItem())) {
                setDifficulty.setEnabled(false);
            } else {
                setDifficulty.setEnabled(true);
            }
        });

        setDifficulty = new JButton("SET DIFFICULTY");
        setDifficulty.setFont(new Font("Arial", Font.BOLD, 20));
        setDifficulty.setFocusPainted(false);
        setDifficulty.setAlignmentX(Component.CENTER_ALIGNMENT);
        setDifficulty.setMaximumSize(new Dimension(450, 70));
        setDifficulty.setPreferredSize(new Dimension(450, 70));
        setDifficulty.setEnabled(false);

        setDifficulty.addActionListener(e -> {
            mainFrame.getConfig().setBoard((BoardConfig) difficultyBox.getSelectedItem());
            setDifficulty.setEnabled(false);
            ConfigManager.save(mainFrame.getConfig());
        });

        settingsMenu.add(difficultyBox);
        settingsMenu.add(setDifficulty);
        settingsMenu.add(Box.createVerticalStrut(25));

        JButton changeNames = new JButton("CHANGE PLAYER NAMES");
        changeNames.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeNames.setFont(new Font("Arial", Font.BOLD, 20));
        changeNames.setMaximumSize(new Dimension(450, 70));
        changeNames.setPreferredSize(new Dimension(450, 70));
        changeNames.setFocusPainted(false);

        changeNames.addActionListener(e -> {
            new NameChange(this);
        });

        settingsMenu.add(changeNames);
        settingsMenu.add(Box.createVerticalStrut(25));

        JButton pickColor1 = new JButton();
        pickColor1.setAlignmentX(Component.CENTER_ALIGNMENT);
        pickColor1.setFont(new Font("Arial", Font.BOLD, 20));
        pickColor1.setMaximumSize(new Dimension(450, 70));
        pickColor1.setPreferredSize(new Dimension(450, 70));
        pickColor1.setFocusPainted(false);
        pickColor1.setBackground(ColorHex.hexToColor(mainFrame.getConfig().getColor1()));

        pickColor1.addActionListener(e -> {
            Color color1 = JColorChooser.showDialog(this, "CHOOSE COLOR", Color.BLACK);

            if (color1 != null) {
                pickColor1.setBackground(color1);
                mainFrame.getConfig().setColor1(ColorHex.colorToHex(color1));
                ConfigManager.save(mainFrame.getConfig());
            }
        });
        settingsMenu.add(pickColor1);

        JButton pickColor2 = new JButton();
        pickColor2.setAlignmentX(Component.CENTER_ALIGNMENT);
        pickColor2.setFont(new Font("Arial", Font.BOLD, 20));
        pickColor2.setMaximumSize(new Dimension(450, 70));
        pickColor2.setPreferredSize(new Dimension(450, 70));
        pickColor2.setFocusPainted(false);
        pickColor2.setBackground(ColorHex.hexToColor(mainFrame.getConfig().getColor2()));

        pickColor2.addActionListener(e -> {
            Color color2 = JColorChooser.showDialog(this, "CHOOSE COLOR", Color.BLACK);

            if (color2 != null) {
                pickColor2.setBackground(color2);
                mainFrame.getConfig().setColor2(ColorHex.colorToHex(color2));
                ConfigManager.save(mainFrame.getConfig());
            }
        });
        settingsMenu.add(pickColor2);
        settingsMenu.add(Box.createVerticalStrut(25));

        JButton resetStats = new JButton("RESET STATS");
        resetStats.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetStats.setFont(new Font("Arial", Font.BOLD, 20));
        resetStats.setMaximumSize(new Dimension(450, 70));
        resetStats.setPreferredSize(new Dimension(450, 70));
        resetStats.setFocusPainted(false);

        resetStats.addActionListener(e -> {
            resetStats.setText("DONE");

            new javax.swing.Timer(1000, ev -> {
                resetStats.setText("RESET STATS");
            }) {{
                setRepeats(false);
                start();
            }};

            mainFrame.getConfig().resetStats();
            ConfigManager.save(mainFrame.getConfig());
        });
        settingsMenu.add(resetStats);
        settingsMenu.add(Box.createVerticalStrut(25));

        JButton saveStats = new JButton("EXPORT STATS");
        saveStats.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveStats.setFont(new Font("Arial", Font.BOLD, 20));
        saveStats.setMaximumSize(new Dimension(450, 70));
        saveStats.setPreferredSize(new Dimension(450, 70));
        saveStats.setFocusPainted(false);

        saveStats.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("EXPORT STATISTICS");

            //external code - @Microsoft Copilot
            fileChooser.setSelectedFile(new File("stats.txt"));

            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files (*.txt)", "txt"));

            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                if (!file.getName().toLowerCase().endsWith(".txt")) {
                    file = new File(file.getAbsolutePath() + ".txt");
                }

                try (FileWriter writer = new FileWriter(file)) {

                    GameConfig config = mainFrame.getConfig();

                    writer.write("=== Tic-Tac-Toe Stats ===\n\n");

                    writer.write("Games played: " + config.getGamesPlayed() + "\n");
                    writer.write(config.getPlayer1Name() + " wins: " + config.getP1Wins() + "\n");
                    writer.write(config.getPlayer2Name() + " wins: " + config.getP2Wins() + "\n");
                    writer.write("Draws: " + config.getDraws() + "\n");

                    writer.write("\nExported at: " + java.time.LocalDateTime.now());

                    JOptionPane.showMessageDialog(this, "Statistics saved.");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error ocurred wile exporting.");
                }
            }
        });
        //
        settingsMenu.add(saveStats);
        settingsMenu.add(Box.createVerticalStrut(25));

        JButton resetSettings = new JButton("DEFAULT SETTINGS");
        resetSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetSettings.setFont(new Font("Arial", Font.BOLD, 20));
        resetSettings.setMaximumSize(new Dimension(450, 70));
        resetSettings.setPreferredSize(new Dimension(450, 70));
        resetSettings.setFocusPainted(false);

        resetSettings.addActionListener(e -> {
            resetSettings.setText("DONE");

            new javax.swing.Timer(1000, ev -> {
                resetSettings.setText("DEFAULT SETTINGS");
            }) {{
                setRepeats(false);
                start();
            }};


            mainFrame.getConfig().setBoard(GamePresets.DEFAULT);
            mainFrame.getConfig().setPlayer1Name(GameConfig.defaultp1);
            mainFrame.getConfig().setPlayer2Name(GameConfig.defaultp2);
            mainFrame.getConfig().setColor1(GameConfig.defaultc1);
            mainFrame.getConfig().setColor2(GameConfig.defaultc2);
            difficultyBox.setSelectedItem(GamePresets.DEFAULT);
            pickColor1.setBackground(ColorHex.hexToColor(GameConfig.defaultc1));
            pickColor2.setBackground(ColorHex.hexToColor(GameConfig.defaultc2));
            color1.setText("CHOOSE X COLOR - " + mainFrame.getConfig().getPlayer1Name());
            color2.setText("CHOOSE O COLOR - " + mainFrame.getConfig().getPlayer2Name());
            ConfigManager.save(mainFrame.getConfig());
        });
        settingsMenu.add(resetSettings);
        settingsMenu.add(Box.createVerticalStrut(25));

        JButton exitSettings = new JButton("EXIT");
        exitSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitSettings.setFont(new Font("Arial", Font.BOLD, 20));
        exitSettings.setMaximumSize(new Dimension(450, 70));
        exitSettings.setPreferredSize(new Dimension(450, 70));
        exitSettings.setFocusPainted(false);

        exitSettings.addActionListener(e -> dispose());
        settingsMenu.add(exitSettings);

        add(settingsMenu, BorderLayout.EAST);

        titles = new JPanel();
        titles.setLayout(new BoxLayout(titles, BoxLayout.Y_AXIS));
        titles.setBorder(BorderFactory.createEmptyBorder(105, 80, 50 ,50));

        JLabel difficultyLabel = new JLabel("CHANGE DIFFICULTY");
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 30));
        difficultyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        color1 = new JLabel("CHOOSE X COLOR - " + mainFrame.getConfig().getPlayer1Name());
        color1.setFont(new Font("Arial", Font.BOLD, 30));
        color1.setAlignmentX(Component.CENTER_ALIGNMENT);

        color2 = new JLabel("CHOOSE O COLOR - " + mainFrame.getConfig().getPlayer2Name());
        color2.setFont(new Font("Arial", Font.BOLD, 30));
        color2.setAlignmentX(Component.CENTER_ALIGNMENT);

        titles.add(difficultyLabel);
        titles.add(Box.createVerticalStrut(185));
        titles.add(color1);
        titles.add(Box.createVerticalStrut(30));
        titles.add(color2);
        titles.setVisible(true);

        add(titles, BorderLayout.WEST);
        setVisible(true);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public JLabel getColor1() {
        return color1;
    }

    public void setColor1(JLabel color1) {
        this.color1 = color1;
    }

    public JLabel getColor2() {
        return color2;
    }

    public void setColor2(JLabel color2) {
        this.color2 = color2;
    }
}