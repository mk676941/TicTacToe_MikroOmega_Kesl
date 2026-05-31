package frames;

import javax.swing.*;
import java.awt.*;

public class Statistics extends JFrame {
    private MainFrame frame;

    public Statistics(MainFrame frame) {
        setTitle("Statistcs");
        setSize(new Dimension(800, 454)); //319
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel stats = new JPanel();;
        stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        stats.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        String[] columns = {"STAT", "VALUE"};
        Object[][] data = {
                {frame.getConfig().getPlayer1Name() + " wins", frame.getConfig().getP1Wins()},
                {frame.getConfig().getPlayer2Name() + " wins", frame.getConfig().getP2Wins()},
                {"Draws", frame.getConfig().getDraws()},
                {"Total games", frame.getConfig().getGamesPlayed()}
        };

        JTable table = new JTable(data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 30));
        table.setFont(new Font("Arial", Font.BOLD, 40));
        table.setRowHeight(60);

        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setMaximumSize(new Dimension(800, 319));

        stats.add(scroll);
        stats.add(Box.createVerticalStrut(30));

        JButton exit = new JButton("EXIT");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setFont(new Font("Arial", Font.BOLD, 20));
        exit.setMaximumSize(new Dimension(220, 100));
        exit.setPreferredSize(new Dimension(220, 100));
        exit.setFocusPainted(false);

        exit.addActionListener(e -> dispose());
        stats.add(exit);

        add(stats, BorderLayout.CENTER);
        setVisible(true);

    }
}
