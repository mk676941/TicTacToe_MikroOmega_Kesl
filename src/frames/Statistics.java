package frames;

import javax.swing.*;
import java.awt.*;

/**
 * Statistics frame class
 * used for setting up the Statistics frame
 * contains constructor for setting up the frame and its content
 * @author Matej Kesl
 */
public class Statistics extends JFrame {

    /**
     * Statistics constructor
     * contains stats panel
     * contains table with statistics
     * contains exit button
     * @param frame - MainFrame for loading game data
     */
    public Statistics(MainFrame frame) {
        //frame
        setTitle("Statistics");
        setSize(new Dimension(800, 454)); //319
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        //stats panel
        JPanel stats = new JPanel();;
        stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        stats.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        //columns
        String[] columns = {"STAT", "VALUE"};

        //data
        Object[][] data = {{frame.getConfig().getPlayer1Name() + " wins", frame.getConfig().getP1Wins()},
                {frame.getConfig().getPlayer2Name() + " wins", frame.getConfig().getP2Wins()},
                {"Draws", frame.getConfig().getDraws()},
                {"Total games", frame.getConfig().getGamesPlayed()}};

        //table
        JTable table = new JTable(data, columns) {

            //disabling editing
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 30));
        table.setFont(new Font("Arial", Font.BOLD, 40));
        table.setRowHeight(60);

        //disabling selection
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);

        //scrollPane
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setMaximumSize(new Dimension(800, 319));

        stats.add(scroll);
        stats.add(Box.createVerticalStrut(30));

        //exit button
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
