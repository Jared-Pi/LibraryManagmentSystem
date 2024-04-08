import DBHelper.Books;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/* Jared Pilewski, CEN-3024C, 04/07/24
 * dbLibraryGUI
 * Runs the program and displays a GUI that can utilize all functions of the Library Management System
 *
 */
public class dbLibraryGUI extends JFrame {
    private JLabel formTitle;
    private JPanel MainPanel;
    private JTextPane libList;
    private JButton buttRemove;
    private JTextField inputRemove;
    private JComboBox combRemove;
    private JButton buttCheckOut;
    private JButton buttCheckIn;
    private JTextField inputCheckInOut;
    private JButton buttExit;
    private JComboBox combCheck;

    public dbLibraryGUI() {
        ConsoleOutputCapturer capOut = new ConsoleOutputCapturer();
        Books dbBooks = new Books();
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

        setContentPane(MainPanel);
        setTitle("Library Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 768);
        setLocationRelativeTo(null);
        setVisible(true);

        final boolean[] ffREM = {true};
        final boolean[] ffCIO = {true};

        capOut.start();
        printDatabase(data);
        libList.setText(capOut.stop());


        buttCheckOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dbBooks.getExecuteResult("select * from Books where Title='"+inputCheckInOut.getText()+"';").isEmpty()) {
                    JOptionPane.showMessageDialog(dbLibraryGUI.this, "ERROR!\nBook with Title " + inputCheckInOut.getText() + " could not be found");
                }
                else if (dbBooks.getExecuteResult("select * from Books where Status='Checked In' and Title='" + inputCheckInOut.getText() + "';").isEmpty()) {
                    JOptionPane.showMessageDialog(dbLibraryGUI.this, "ERROR!\nBook with Title " + inputCheckInOut.getText() + " is already Checked Out");
                }
                else {
                    LocalDate today = LocalDate.now();
                    LocalDate theDueDate = today.plusWeeks(4);
                    dbBooks.update(Books.dueDate,theDueDate.toString(),Books.Title,inputCheckInOut.getText());
                    dbBooks.update(Books.Status,"Checked Out",Books.Title,inputCheckInOut.getText());
                    JOptionPane.showMessageDialog(dbLibraryGUI.this, "Book with Title " + inputCheckInOut.getText() + " has been Checked Out");
                }
                capOut.start();
                printDatabase(data);
                libList.setText(capOut.stop());
            }
        });
        buttCheckIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dbBooks.getExecuteResult("select * from Books where Title='"+inputCheckInOut.getText()+"';").isEmpty()) {
                    JOptionPane.showMessageDialog(dbLibraryGUI.this, "ERROR!\nBook with Title " + inputCheckInOut.getText() + " could not be found");
                }
                else if (dbBooks.getExecuteResult("select * from Books where Status='Checked Out' and Title='" + inputCheckInOut.getText() + "';").isEmpty()) {
                    JOptionPane.showMessageDialog(dbLibraryGUI.this, "ERROR!\nBook with Title " + inputCheckInOut.getText() + " is already Checked In");
                }
                else {
                    dbBooks.update(Books.dueDate,null,Books.Title,inputCheckInOut.getText());
                    dbBooks.update(Books.Status,"Checked In",Books.Title,inputCheckInOut.getText());
                    JOptionPane.showMessageDialog(dbLibraryGUI.this, "Book with Title " + inputCheckInOut.getText() + " has been Checked In");
                }
                capOut.start();
                printDatabase(data);
                libList.setText(capOut.stop());
            }
        });
        buttRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (combRemove.getSelectedItem().equals("ID")) {
                    if (dbBooks.getExecuteResult("select * from Books where bookID="+inputRemove.getText()+";").isEmpty()) {
                        JOptionPane.showMessageDialog(dbLibraryGUI.this, "ERROR!\nBook with ID " + inputRemove.getText() + " could not be found");
                    }
                    else {
                        dbBooks.delete("bookID", inputRemove.getText());
                        JOptionPane.showMessageDialog(dbLibraryGUI.this, "Book with ID " + inputRemove.getText() + " has been removed");
                    }

                }
                else {
                    if (dbBooks.getExecuteResult("select * from Books where Title='"+inputRemove.getText()+"';").isEmpty()) {
                        JOptionPane.showMessageDialog(dbLibraryGUI.this, "ERROR!\nBook with Title " + inputRemove.getText() + " could not be found");
                    }
                    else {
                        dbBooks.delete("Title", "'" + inputRemove.getText() + "'");
                        JOptionPane.showMessageDialog(dbLibraryGUI.this, "Book with Title " + inputRemove.getText() + " has been removed");
                    }

                }
                capOut.start();
                printDatabase(data);
                libList.setText(capOut.stop());
            }
        });

        buttExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        inputRemove.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (ffREM[0]) {
                    inputRemove.setText("");
                    ffREM[0] = false;
                }
            }
        });
        inputCheckInOut.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (ffCIO[0]) {
                    inputCheckInOut.setText("");
                    ffCIO[0] = false;
                }
            }
        });
    }

    public static void main(String[] args) {
        new dbLibraryGUI();
    }

    //Updates the list of books in the database
    public static void printDatabase(ArrayList<ArrayList<Object>> data) {
        Books dbBooks = new Books();
        data = dbBooks.getExecuteResult("select * from Books;");
        for (List<Object> record : data) {
            System.out.println(record.toString());
        }
    }
}
