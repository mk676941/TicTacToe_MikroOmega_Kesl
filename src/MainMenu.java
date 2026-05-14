import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {



    public MainMenu(MainFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Shrews");
        label.setFont(new Font("Arial", Font.BOLD, 80));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        JButton play = new JButton("PLAY");
        play.setFont(new Font("Arial", Font.BOLD, 20));
        play.setFocusPainted(false);
        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        play.setMaximumSize(new Dimension(230, 60));
        play.setPreferredSize(new Dimension(230, 60));
        play.addActionListener(e -> frame.showPlay());
        add(play);
    }
}
