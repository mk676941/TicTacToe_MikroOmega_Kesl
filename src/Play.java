import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Play extends JPanel {
    private Game game;
    private JButton[][] buttons;
    private JPanel overlay;
    private JPanel resultLabel;

    public Play(Main frame) {
        game = new Game();
        buttons = new JButton[3][3];

        setLayout(new BorderLayout());

        JPanel gameGrid = new JPanel(new GridLayout(3, 3, 10, 10));

        for (int i = 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {

                int row = i;
                int col = j;

                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));

                buttons[i][j].addActionListener(e -> {

                    int value = game.getCurrentPlayer().getValue();
                    String result = game.play(row, col);

                    if (result.equals("Invalid move")) return;

                    buttons[row][col].setText(String.valueOf(value));
                    buttons[row][col].setEnabled(false);

                    if (result.equals("Win")) {
                        showResult(game.getCurrentPlayer().getName() + " won the game.");
                        disableAllButtons();

                    } else if (result.equals("Draw")) {
                        showResult("The game resulted in a draw.");
                        disableAllButtons();
                    }
                });

                gameGrid.add(buttons[i][j]);
            }
        }
        add(gameGrid, BorderLayout.CENTER);
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}
