package frames;
import core.Game;
import core.Player;
import gameConfig.GameConfig;

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
    private boolean lineVisible = false;
    private JLabel txt;

    public void showResult(String text) {
        overlay.removeAll();
        txt = new JLabel(text);

        txt.setFont(new Font("Arial", Font.BOLD, 40));
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setForeground(Color.BLACK);

        overlay.add(Box.createVerticalStrut(30));
        overlay.add(txt, BorderLayout.NORTH);
        upperPanel.removeAll();
        upperPanel.add(Box.createVerticalStrut(138));
        overlay.setVisible(true);

        reset.setVisible(false);
        showLine.setVisible(true);

        revalidate();
        repaint();
    }

    public Play(GameConfig gameConfig) {
        setTitle("Tic-Tac-Toe");
        setLayout(new BorderLayout());
        setResizable(false);
        setPreferredSize(new Dimension(700, 1000));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        game = new Game(gameConfig.getBoard(), gameConfig.getPlayer1Name(), gameConfig.getPlayer2Name(), ColorHex.hexToColor(gameConfig.getColor1()), ColorHex.hexToColor(gameConfig.getColor2()), gameConfig);
        int rows = game.getGameBoard().getRows();
        int cols = game.getGameBoard().getColumns();

        buttons = new JButton[rows][cols];

        overlay = new JPanel(new BorderLayout()) {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (!lineVisible || game.getWinningCells().isEmpty()) return;

                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(10));
                g2.setColor(Color.BLACK);

                int[][] line = getLinePixelCoords();

                g2.drawLine(line[0][0], line[0][1], line[1][0], line[1][1]);
            }
        };
        overlay.setOpaque(false);
        overlay.setBorder(BorderFactory.createEmptyBorder(30, 0, 50, 0));
        setGlassPane(overlay);

        JPanel gameGrid = new JPanel(new GridLayout(rows, cols));
        gameGrid.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        for (int i = 0; i<rows; i++) {
            for (int j = 0; j < cols; j++) {

                int row = i;
                int col = j;

                int top = 0;
                int left = 0;
                int bottom = 0;
                int right = 0;

                if (i > 0) top = 2;
                if (j > 0) left = 2;

                buttons[i][j] = new JButton();
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setContentAreaFilled(false);
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setMaximumSize(new Dimension(90, 90));
                buttons[i][j].setPreferredSize(new Dimension(90, 90));
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));

                buttons[i][j].addActionListener(e -> {
                    Player currentPlayer = game.getCurrentPlayer();

                    String result = game.play(row, col);

                    if (result.equals("Invalid move")) return;

                    buttons[row][col].setText(game.getGameBoard().getCell(row, col));
                    buttons[row][col].setForeground(currentPlayer.getColor());

                    if (result.equals("Continue")) {
                        playereName.setText(game.getCurrentPlayer().getName());
                        playerLabel.setText(game.getCurrentPlayer().getValue());
                        playerLabel.setForeground(game.getCurrentPlayer().getColor());
                    }

                    if (result.equals("Win")) {
                        showResult(game.getCurrentPlayer().getName() + " won the game!");

                    } else if (result.equals("Draw")) {
                        showResult("The game resulted in a draw.");
                        showLine.setVisible(false);
                        lowerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 60, 30));
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
        reset.setMaximumSize(new Dimension(220, 60));
        reset.setPreferredSize(new Dimension(220, 60));
        reset.setFocusPainted(false);

        reset.addActionListener(e -> {
            game.restart();
            playereName.setText(game.getCurrentPlayer().getName());
            playerLabel.setText(game.getCurrentPlayer().getValue());
            playerLabel.setForeground(game.getCurrentPlayer().getColor());

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
        exit.setMaximumSize(new Dimension(220, 60));
        exit.setPreferredSize(new Dimension(220, 60));
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
        playerLabel.setForeground(game.getCurrentPlayer().getColor());
        playerLabel.setFont(new Font("Arial", Font.BOLD, 50));
        playerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        upperPanel.add(playerLabel);

        add(upperPanel, BorderLayout.NORTH);


        showLine = new JButton("SHOW WIN LINE");
        showLine.setAlignmentX(Component.CENTER_ALIGNMENT);
        showLine.setFont(new Font("Arial", Font.BOLD, 20));
        showLine.setMaximumSize(new Dimension(220, 60));
        showLine.setPreferredSize(new Dimension(220, 60));
        showLine.setFocusPainted(false);
        showLine.setVisible(false);

        showLine.addActionListener(e -> {
            lineVisible = !lineVisible;

            if (lineVisible) {
                showLine.setText("HIDE WIN LINE");
                upperPanel.removeAll();
                upperPanel.add(playereName);
                upperPanel.add(Box.createVerticalStrut(20));
                upperPanel.add(playerLabel);
                txt.setVisible(false);
            } else {
                showLine.setText("SHOW WIN LINE");
                upperPanel.removeAll();
                upperPanel.add(Box.createVerticalStrut(138));
                txt.setVisible(true);
            }

            overlay.repaint();
        });
        lowerPanel.add(showLine);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //external code - @Microsoft Copilot
    private int[][] getLinePixelCoords() {
        java.util.List<int[]> cells = game.getWinningCells();

        int[] first = cells.get(0);
        int[] last = cells.get(cells.size() - 1);

        JButton b1 = buttons[first[0]][first[1]];
        JButton b2 = buttons[last[0]][last[1]];

        Point p1 = SwingUtilities.convertPoint(
                b1.getParent(),
                b1.getLocation(),
                overlay
        );

        Point p2 = SwingUtilities.convertPoint(
                b2.getParent(),
                b2.getLocation(),
                overlay
        );

        int x1 = p1.x + b1.getWidth() / 2;
        int y1 = p1.y + b1.getHeight() / 2;

        int x2 = p2.x + b2.getWidth() / 2;
        int y2 = p2.y + b2.getHeight() / 2;

        return new int[][] {
                {x1, y1},
                {x2, y2}
        };
    }
    //

}