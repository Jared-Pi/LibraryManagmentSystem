import java.io.*;
import java.nio.file.*;
import java.util.*;

/* Jared Pilewski, CEN-3024C, 01/27/24
 * Library
 * The library class contains methods to create a library and books.
 * The library method will be able to create an array, add books, remove books, and list the books in the collection
 */
public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    //Adds books to the library arraylist
    public void addBook(int id, String title, String author) {
        for (int i=0; i<books.size();i++) {
            Book book = books.get(i);
            if (book.getID() == id) {
                System.out.println("ID already exists, please use a unique ID.");
                return;
            }
        }
        Book newBook = new Book(id, title, author);
        books.add(newBook);
        System.out.println("'" + newBook.getTitle() + "' has been added!");
    }

    //Removes books from the library arraylist
    public void removeBook(int id) {
        for (int i=0; i<books.size(); i++) {
            Book book = books.get(i);
            if (book.getID() == id) {
                books.remove(i);
                System.out.println("'" + book.getTitle() + "' Has been removed!");
                return;
            }
        }
        System.out.println("Book with ID " + id + " not found.");
    }

    //lists books from the library arraylist
    public void listAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        }
        else {
            System.out.println("List of all books in the library:");
            for (int i=0; i<books.size(); i++) {
                Book book = books.get(i);
                System.out.println(book.getID() + "," + book.getTitle() + "," + book.getAuthor());
            }
        }
    }

    //saves the books in the library array list into the 'books.txt' file
    public void saveBooks(String filename) {
        books.sort(Comparator.comparing(Book::getID));
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of("src\\books.txt"), StandardOpenOption.TRUNCATE_EXISTING)) {
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                writer.write(book.getID() + "," + book.getTitle() + "," + book.getAuthor());
                writer.newLine();
            }
            System.out.println("Books have been saved to file!");
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    //Copy of 'addBook' method so that when the program loads the library from the text file, program won't start with a bunch of 'book has been added' text
    public void loadBook(int id, String title, String author) {
        for (int i=0; i<books.size();i++) {
            Book book = books.get(i);
            if (book.getID() == id) {
                System.out.println("A book on file was not added because of a non-unique ID!");
                return;
            }
        }
        Book newBook = new Book(id, title, author);
        books.add(newBook);
    }

    //Loads the books from the text file into the library array list
    public void loadLibraryFromFile(Library library) {
        File file = new File("src\\books.txt");
        try (Scanner scan = new Scanner(new File("src\\books.txt"))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String author = parts[2].trim();
                library.loadBook(id, title, author);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
        }
    }
}
