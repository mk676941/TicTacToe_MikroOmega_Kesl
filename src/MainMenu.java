import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu(MainFrame frame) {
        setLayout(null);
        JLabel label = new JLabel("Shrews");
        label.setFont(new Font("Arial", Font.BOLD, 80));
        label.setLocation(600, 200);

        add(label);
        JButton play = new JButton();
        play.setBounds(200, 200, 50, 30);

        play.addActionListener(e -> frame.showPlay());

        add(play);
    }
}
