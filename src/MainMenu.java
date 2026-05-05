import javax.swing.*;

public class MainMenu extends JPanel {

    public MainMenu(Main frame) {
        setLayout(null);

        JButton play = new JButton("PLAY");
        play.setBounds(200, 200, 50, 30);

        play.addActionListener(e -> frame.showPlay());

        add(play);
    }
}
