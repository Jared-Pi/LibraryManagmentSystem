import java.util.Scanner;

/* Jared Pilewski, CEN-3024C, 01/27/24
 * Main
 * Runs the program using constructors and getters from the Library and Book classes to prompt the user to either add, remove, or list
 *  The books in the library
 */
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.loadLibraryFromFile(library);
        Scanner scan = new Scanner(System.in);
        boolean contRun = true;
        System.out.println("Welcome to the Library Management System!");
        System.out.println("""
                List of Commands:
                    Add (add a new book to the library)
                    Remove (remove a book from the library)
                    List (list all books in the library)
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
                    library.addBook(scanID, scanTitle, scanAuthor);
                }
                case "remove", "Remove" -> {
                    System.out.println("Input the ID of the book you want to remove:");
                    int scanID = scan.nextInt();
                    scan.nextLine(); //consumes extra /n from scan.nextInt();
                    library.removeBook(scanID);
                }
                case "list", "List" -> library.listAllBooks();
                case "save", "Save" -> library.saveBooks("src\\books.txt");
                case "end", "End" -> contRun = false;
                case "help", "Help" -> System.out.println("""
                        List of Commands:
                            Add (add a new book to the library)
                            Remove (remove a book from the library)
                            List (list all books in the library)
                            End (stops the program from running)
                            Save (Save current library to file)
                            Help (list of commands)""");
                default -> System.out.println("Command '" + scanCommand + "' not recognized.");
            }
        }
    }
}