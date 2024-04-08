import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import static java.lang.Integer.parseInt;

/* Jared Pilewski, CEN-3024C, 03/24/24
 * oldLibraryGUI
 * Runs the program and displays a GUI that can utilize all functions of the Library Management System
 * !!Now Depreciated for "dbLibraryGUI"!!
 */
public class oldLibraryGUI extends JFrame {
    private JLabel formTitle;
    private JPanel MainPanel;
    private JButton buttImport;
    private JTextField inputImportFile;
    private JTextPane libList;
    private JButton buttList;
    private JButton buttRemove;
    private JTextField inputRemove;
    private JComboBox combRemove;
    private JButton buttCheckOut;
    private JTextField inputCheckOut;
    private JButton buttCheckIn;
    private JTextField inputCheckIn;
    private JButton buttExit;

    public oldLibraryGUI() {
        Library library = new Library();
        ConsoleOutputCapturer capOut = new ConsoleOutputCapturer();

        setContentPane(MainPanel);
        setTitle("Library Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 768);
        setLocationRelativeTo(null);
        setVisible(true);

        buttImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capOut.start();
                library.loadLibraryFromFile(library, inputImportFile.getText());
                JOptionPane.showMessageDialog(oldLibraryGUI.this, capOut.stop());
            }
        });

        buttList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capOut.start();
                library.listAllBooks();
                libList.setText(capOut.stop());
            }
        });
        inputRemove.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                inputRemove.setText("");
            }
        });
        buttRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (combRemove.getSelectedItem().equals("ID")) {
                    int tempRem;
                    try {
                        tempRem = parseInt(inputRemove.getText());
                        capOut.start();
                        library.removeBook(tempRem);
                        JOptionPane.showMessageDialog(oldLibraryGUI.this, capOut.stop());
                    }

                    catch (NumberFormatException err) {
                        tempRem = -1;
                        JOptionPane.showMessageDialog(oldLibraryGUI.this,"Error, Input a number when removing by ID");
                    }

                }
                else {
                    capOut.start();
                    library.removeBook(inputRemove.getText());
                    JOptionPane.showMessageDialog(oldLibraryGUI.this, capOut.stop());
                }

            }
        });
        buttCheckOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capOut.start();
                library.checkOutBook(inputCheckOut.getText());
                JOptionPane.showMessageDialog(oldLibraryGUI.this, capOut.stop());
            }
        });
        buttCheckIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capOut.start();
                library.checkInBook(inputCheckIn.getText());
                JOptionPane.showMessageDialog(oldLibraryGUI.this, capOut.stop());
            }
        });
        buttExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new oldLibraryGUI();
    }
}
