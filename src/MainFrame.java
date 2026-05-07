import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setSize(1200, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        showMainMenu();
        setVisible(true);
    }

    public void showMainMenu() {
        setContentPane(new MainMenu(this));
        revalidate();
        repaint();
    }

    public void showPlay() {
        setContentPane(new Play(this));
        revalidate();
        repaint();
    }
}
