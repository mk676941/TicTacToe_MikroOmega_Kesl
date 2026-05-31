package frames;
import gameConfig.ConfigManager;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

/**
 * NameChange frame class
 * used for settings up the NameChange frame
 * used for changing player names
 * contains constructor for setting up the frame and its content
 * @author Matej Kesl
 */
public class NameChange extends JFrame {
    private JTextField nameField1;
    private JTextField nameField2;
    private JButton applyNames;
    private JButton exit;
    private Settings settings;

    /**
     * NameChange constructor
     * contains 2 text field for changing player names
     * contains button for applying changed names
     * contains button for exiting the frame
     * @param settings - Settings frame
     */
    public NameChange(Settings settings) {
        this.settings = settings;

        //frame
        setTitle("Name change");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //main panel
        JPanel panel = new JPanel();
        panel.setLayout((new BoxLayout(panel, BoxLayout.Y_AXIS)));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50 ,50));

        //top panel
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        top.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 47));
        top.setAlignmentX(Component.CENTER_ALIGNMENT);

        //text field for player 1
        nameField1 = new JTextField(settings.getMainFrame().getConfig().getPlayer1Name());
        nameField1.setPreferredSize(new Dimension(350, 70));
        nameField1.setFont(new Font("Arial", Font.BOLD, 20));

        //external code
        //@Microsoft Copilot
        //used for limiting the character count in text field 1
        ((AbstractDocument) nameField1.getDocument())
                .setDocumentFilter(new DocumentFilter() {

                    private final int MAX_LENGTH = 8;

                    @Override
                    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                            throws BadLocationException {
                        if (fb.getDocument().getLength() + string.length() <= MAX_LENGTH) {
                            super.insertString(fb, offset, string, attr);
                        }
                    }

                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                            throws BadLocationException {
                        if (fb.getDocument().getLength() - length + text.length() <= MAX_LENGTH) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                });
        //

        nameField1.addActionListener(e -> {
            nameField1.transferFocus();
        });

        //X label
        JLabel p1 = new JLabel("X");
        p1.setFont(new Font("Arial", Font.BOLD, 30));

        top.add(p1);
        top.add(nameField1);
        top.setVisible(true);

        panel.add(top);
        panel.add(Box.createVerticalStrut(5));

        //bottom panel
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
        top.setAlignmentX(Component.CENTER_ALIGNMENT);

        //text field for player 2
        nameField2 = new JTextField(settings.getMainFrame().getConfig().getPlayer2Name());
        nameField2.setPreferredSize(new Dimension(350, 70));
        nameField2.setFont(new Font("Arial", Font.BOLD, 20));

        //external code
        //@Microsoft Copilot
        //used for limiting the character count in text field 2
        ((AbstractDocument) nameField2.getDocument())
                .setDocumentFilter(new DocumentFilter() {

                    private final int MAX_LENGTH = 8;

                    @Override
                    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                            throws BadLocationException {
                        if (fb.getDocument().getLength() + string.length() <= MAX_LENGTH) {
                            super.insertString(fb, offset, string, attr);
                        }
                    }

                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                            throws BadLocationException {
                        if (fb.getDocument().getLength() - length + text.length() <= MAX_LENGTH) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                });
        //

        nameField2.addActionListener(e -> {
            nameField2.transferFocus();
        });

        //O label
        JLabel p2 = new JLabel("O");
        p2.setFont(new Font("Arial", Font.BOLD, 30));

        bottom.add(p2);
        bottom.add(nameField2);
        bottom.setVisible(true);

        panel.add(bottom);
        panel.add(Box.createVerticalStrut(25));

        //applyNames button
        applyNames = new JButton("APPLY NAMES");
        applyNames.setFont(new Font("Arial", Font.BOLD, 20));
        applyNames.setFocusPainted(false);
        applyNames.setAlignmentX(Component.CENTER_ALIGNMENT);
        applyNames.setMaximumSize(new Dimension(350, 70));
        applyNames.setPreferredSize(new Dimension(350, 70));

        applyNames.addActionListener(e -> {
            settings.getMainFrame().getConfig().setPlayer1Name(nameField1.getText());
            settings.getMainFrame().getConfig().setPlayer2Name(nameField2.getText());
            settings.getColor1().setText("CHOOSE X COLOR - " + settings.getMainFrame().getConfig().getPlayer1Name());
            settings.getColor2().setText("CHOOSE O COLOR - " + settings.getMainFrame().getConfig().getPlayer2Name());
            ConfigManager.save(settings.getMainFrame().getConfig());
            dispose();
        });

        panel.add(applyNames);
        panel.add(Box.createVerticalStrut(25));

        //exit button
        exit = new JButton("EXIT");
        exit.setFont(new Font("Arial", Font.BOLD, 20));
        exit.setFocusPainted(false);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setMaximumSize(new Dimension(350, 70));
        exit.setPreferredSize(new Dimension(350, 70));

        exit.addActionListener(e -> dispose());
        panel.add(exit);
        panel.setVisible(true);

        add(panel);
        setVisible(true);
    }
}
