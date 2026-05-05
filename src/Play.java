import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Play extends JPanel {

    public Play(int size) {
        setLayout(new GridLayout(3, 3, 10, 10));
        setSize(400, 400);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton[][] buttons = new JButton[size][size];
        for (int i = 0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j] = new JButton();
                add(buttons[i][j]);
            }
        }

        Player player1 = new Player(1);
        Player player2 = new Player(2);
        GameBoard board = new GameBoard(3, 3, 3);
        Game game = new Game(board, player1, player2);
    }
}
