import java.util.Scanner;

/* Jared Pilewski, CEN-3024C, 01/27/24
 * Main
 * Runs the program using constructors and getters from the Library and Book classes to prompt the user to either add, remove, or list
 *  The books in the library
 */
public class Main { //now depreciated, program runs main on "LibraryGUI.java"
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scan = new Scanner(System.in);
        boolean contRun = true;
        System.out.println("Welcome to the Library Management System!");
        System.out.println("Input file 'name.type' (default: 'books.txt'): ");
        String fileName = scan.nextLine();
        library.loadLibraryFromFile(library, fileName);
        System.out.println("""
                List of Commands:
                    Add (add a new book to the library)
                    Remove (remove a book from the library)
                    List (list all books in the library)
                    In (check in a book)
                    Out (check out a book)
                    End (stops the program from running)
                    Save (Save current library to file)
                    Help (list of commands)""");
        while (contRun) {
            System.out.println("\nAwaiting next input:");
            String scanCommand = scan.nextLine();
            switch (scanCommand) {
                case "add", "Add" -> {
                    System.out.println("Input a unique ID:");
                    int scanID = scan.nextInt();
                    scan.nextLine(); //consumes extra /n from scan.nextInt();
                    System.out.println("Input a Title:");
                    String scanTitle = scan.nextLine();
                    System.out.println("Input an Author:");
                    String scanAuthor = scan.nextLine();
                    System.out.println("Input a Genre:");
                    String scanGenre = scan.nextLine();
                    library.addBook(scanID, scanTitle, scanAuthor, scanGenre);
                }
                case "remove", "Remove" -> {
                    System.out.println("Type 'ID' to remove by ID or skip to remove by Title: ");
                    String scanRemov = scan.nextLine();
                    if (scanRemov.equals("id")||scanRemov.equals("ID")) {
                        System.out.println("Input the ID of the book you want to remove:");
                        int scanID = scan.nextInt();
                        scan.nextLine(); //consumes extra /n from scan.nextInt();
                        library.removeBook(scanID);
                    }
                    else {
                        System.out.println("Input the Title of the book you want to remove:");
                        String scanTitle = scan.nextLine();
                        library.removeBook(scanTitle);
                    }
                }
                case "in", "In" -> {
                    System.out.println("Type 'ID' to Check In by ID or skip to Check In by Title: ");
                    String scanIDT = scan.nextLine();
                    if (scanIDT.equals("id")||scanIDT.equals("ID")) {
                        System.out.println("Input the ID of the book you want to Check In:");
                        int scanID = scan.nextInt();
                        scan.nextLine(); //consumes extra /n from scan.nextInt();
                        library.checkInBook(scanID);
                    }
                    else {
                        System.out.println("Input the Title of the book you want to Check In:");
                        String scanTitle = scan.nextLine();
                        library.checkInBook(scanTitle);
                    }
                }
                case "out", "Out" -> {
                    System.out.println("Type 'ID' to Check Out by ID or skip to Check Out by Title: ");
                    String scanIDT = scan.nextLine();
                    if (scanIDT.equals("id")||scanIDT.equals("ID")) {
                        System.out.println("Input the ID of the book you want to Check Out:");
                        int scanID = scan.nextInt();
                        scan.nextLine(); //consumes extra /n from scan.nextInt();
                        library.checkOutBook(scanID);
                    }
                    else {
                        System.out.println("Input the Title of the book you want to Check Out:");
                        String scanTitle = scan.nextLine();
                        library.checkOutBook(scanTitle);
                    }
                }
                case "list", "List" -> library.listAllBooks();
                case "save", "Save" -> library.saveBooks("src\\books.txt");
                case "end", "End" -> contRun = false;
                case "help", "Help" -> System.out.println("""
                        List of Commands:
                            Add (add a new book to the library)
                            Remove (remove a book from the library)
                            List (list all books in the library)
                            In (check in a book)
                            Out (check out a book)
                            End (stops the program from running)
                            Save (save current library to file)
                            Help (list of commands)""");
                default -> System.out.println("Command '" + scanCommand + "' not recognized.");
            }
        }
    }
}