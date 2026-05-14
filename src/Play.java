import javax.swing.*;
import java.awt.*;

public class Play extends JFrame {
    private Game game;
    private JButton[][] buttons;
    private JPanel lowerPanel;
    private JButton exit;
    private JButton reset;

    public void showResult(String text) {
        JLabel txt = new JLabel(text);

        txt.setFont(new Font("Arial", Font.BOLD, 60));
        txt.setForeground(Color.RED);

        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setVerticalAlignment(SwingConstants.CENTER);

        JPanel overlay = new JPanel(new BorderLayout());
        overlay.setOpaque(false);

        overlay.add(txt, BorderLayout.CENTER);

        setGlassPane(overlay);
        overlay.setVisible(true);

        reset.setVisible(false);

        revalidate();
        repaint();
    }

    public Play() {
        setTitle("Shrews");
        setSize(1000, 1000);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);

        game = new Game();
        buttons = new JButton[3][3];


        JPanel gameGrid = new JPanel(new GridLayout(3, 3, 30, 30));
        gameGrid.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        for (int i = 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {

                int row = i;
                int col = j;

                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 80));

                buttons[i][j].addActionListener(e -> {

                    int value = game.getCurrentPlayer().getValue();
                    String result = game.play(row, col);

                    if (result.equals("Invalid move.")) return;

                    buttons[row][col].setText(String.valueOf(value));
                    buttons[row][col].setEnabled(false);

                    if (result.equals("Win")) {
                        showResult(game.getCurrentPlayer().getName() + " won the game!");
                        disableAllButtons();

                        revalidate();
                        repaint();

                    } else if (result.equals("Draw")) {
                        showResult("The game resulted in a draw.");
                        disableAllButtons();

                        revalidate();
                        repaint();
                    }
                });
                gameGrid.add(buttons[i][j]);
            }
        }
        add(gameGrid, BorderLayout.CENTER);

        lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
        lowerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 60, 30));

        reset = new JButton("RESET");
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);
        reset.setFont(new Font("Arial", Font.BOLD, 20));
        reset.setMaximumSize(new Dimension(200, 60));
        reset.setPreferredSize(new Dimension(200, 60));
        reset.setFocusPainted(false);

        reset.addActionListener(e -> {
            game.restart();
            game.setMoves(0);
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

        exit.addActionListener(e -> this.setVisible(false));
        lowerPanel.add(exit);

        add(lowerPanel, BorderLayout.SOUTH);
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}
