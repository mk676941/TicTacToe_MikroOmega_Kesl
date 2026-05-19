import javax.swing.*;
import java.awt.*;

public class Play extends JFrame {
    private Game game;
    private JButton[][] buttons;
    private JPanel lowerPanel;
    private JPanel upperPanel;
    private JPanel overlay;
    private JButton exit;
    private JButton reset;
    private JButton showLine;
    private JLabel playerLabel;
    private JLabel playereName;

    public void showResult(String text) {
        overlay.removeAll();
        JLabel txt = new JLabel(text);

        txt.setFont(new Font("Arial", Font.BOLD, 60));
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setForeground(Color.BLACK);

        overlay.add(txt);
        overlay.setVisible(true);

        reset.setVisible(false);
        showLine.setVisible(true);

        revalidate();
        repaint();
    }

    public Play() {
        setTitle("Tic-Tac-Toe");
        setLayout(new BorderLayout());
        setResizable(false);
        setPreferredSize(new Dimension(700, 1000));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        game = new Game(GamePresets.DEFAULT);
        int rows = game.getGameBoard().getRows();
        int cols = game.getGameBoard().getColumns();

        buttons = new JButton[rows][cols];

        overlay = new JPanel(new BorderLayout());
        overlay.setOpaque(false);
        overlay.setVisible(false);
        setGlassPane(overlay);

        JPanel gameGrid = new JPanel(new GridLayout(rows, cols, 30, 30));
        gameGrid.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        for (int i = 0; i<rows; i++) {
            for (int j = 0; j < cols; j++) {

                int row = i;
                int col = j;

                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setMaximumSize(new Dimension(90, 90));
                buttons[i][j].setPreferredSize(new Dimension(90, 90));

                buttons[i][j].addActionListener(e -> {
                    String result = game.play(row, col);

                    if (result.equals("Invalid move")) return;

                    buttons[row][col].setText(game.getGameBoard().getCell(row, col));
                    buttons[row][col].setEnabled(false);

                    if (result.equals("Continue")) {
                        playereName.setText(game.getCurrentPlayer().getName());
                        playerLabel.setText(game.getCurrentPlayer().getValue());
                    }

                    if (result.equals("Win")) {
                        showResult(game.getCurrentPlayer().getName() + " won the game!");
                        disableAllButtons();

                    } else if (result.equals("Draw")) {
                        showResult("The game resulted in a draw.");
                        disableAllButtons();
                    }

                    revalidate();
                    repaint();
                });
                gameGrid.add(buttons[i][j]);
            }
        }
        add(gameGrid, BorderLayout.CENTER);

        lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));

        reset = new JButton("RESET");
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);
        reset.setFont(new Font("Arial", Font.BOLD, 20));
        reset.setMaximumSize(new Dimension(200, 60));
        reset.setPreferredSize(new Dimension(200, 60));
        reset.setFocusPainted(false);

        reset.addActionListener(e -> {
            game.restart();
            playereName.setText(game.getCurrentPlayer().getName());
            playerLabel.setText(game.getCurrentPlayer().getValue());

            for (int i = 0; i < game.getGameBoard().getRows(); i++) {
                for (int j = 0; j < game.getGameBoard().getColumns(); j++) {
                    buttons[i][j].setText("");
                    buttons[i][j].setEnabled(true);
                }
            }

            revalidate();
            repaint();
        });
        lowerPanel.add(reset);

        exit = new JButton("EXIT");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setFont(new Font("Arial", Font.BOLD, 20));
        exit.setMaximumSize(new Dimension(200, 60));
        exit.setPreferredSize(new Dimension(200, 60));
        exit.setFocusPainted(false);

        exit.addActionListener(e -> dispose());
        lowerPanel.add(exit);

        add(lowerPanel, BorderLayout.SOUTH);

        upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 30));

        playereName = new JLabel(game.getCurrentPlayer().getName());
        playereName.setFont(new Font("Arial", Font.BOLD, 50));
        playereName.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(playereName);
        upperPanel.add(Box.createVerticalStrut(20));

        playerLabel = new JLabel(game.getCurrentPlayer().getValue());
        playerLabel.setFont(new Font("Arial", Font.BOLD, 50));
        playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(playerLabel);

        add(upperPanel, BorderLayout.NORTH);


        showLine = new JButton("SHOW WIN LINE");
        showLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        showLine.setFont(new Font("Arial", Font.BOLD, 20));
        showLine.setMaximumSize(new Dimension(200, 60));
        showLine.setPreferredSize(new Dimension(200, 60));
        showLine.setFocusPainted(false);
        showLine.setVisible(false);

        showLine.addActionListener(e -> {
            overlay.setVisible(false);
            showLine.setVisible(false);
            lowerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 60, 30));

            for (int[] cell : game.getWinningCells()) {
                buttons[cell[0]][cell[1]].setBackground(Color.GREEN);
            }
        });
        lowerPanel.add(showLine);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void disableAllButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}
