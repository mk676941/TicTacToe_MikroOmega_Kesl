import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainMenu;

    public MainFrame() {
        setTitle("Tic-Tac-Toe");
        setSize(1200, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        JPanel MainMenu = new JPanel();
        MainMenu.setLayout(new BoxLayout(MainMenu, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Tic-Tac-Toe");
        label.setFont(new Font("Arial", Font.BOLD, 80));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        MainMenu.add(label);

        JButton play = new JButton("PLAY");
        play.setFont(new Font("Arial", Font.BOLD, 20));
        play.setFocusPainted(false);
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        play.setMaximumSize(new Dimension(230, 60));
        play.setPreferredSize(new Dimension(230, 60));
        play.addActionListener(e -> showPlay());
        MainMenu.add(play);
        MainMenu.setVisible(true);

        add(MainMenu);
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        JLabel label = new JLabel("Tic-Tac-Toe");
//        label.setFont(new Font("Arial", Font.BOLD, 80));
//        label.setAlignmentX(Component.CENTER_ALIGNMENT);
//        add(label);
//
//        JButton play = new JButton("PLAY");
//        play.setFont(new Font("Arial", Font.BOLD, 20));
//        play.setFocusPainted(false);
//        play.setAlignmentX(Component.CENTER_ALIGNMENT);
//        play.setMaximumSize(new Dimension(230, 60));
//        play.setPreferredSize(new Dimension(230, 60));
//        play.addActionListener(e -> this.showPlay());
//        add(play);


    }

//    public void showMainMenu() {
//        setContentPane(new MainMenu(this));
//        revalidate();
//        repaint();
//    }

    public void showPlay() {
        new Play();
        revalidate();
        repaint();
    }
}
