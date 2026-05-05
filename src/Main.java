import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        showMainMenu();
        setVisible(true);
    }

    public void showMainMenu() {
        setContentPane(new MainMenu(this));
        revalidate();
    }

    public void showPlay() {
        setContentPane(new Play(this));
        revalidate();
    }

    public static void main(String[] args) {
        new Main();
    }
}