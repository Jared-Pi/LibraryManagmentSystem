import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

/* Jared Pilewski, CEN-3024C, 01/27/24
 * Library
 * The library class contains methods to create a library and books.
 * The library method will be able to create an array, add books, remove books, and list the books in the collection
 */
public class Library {
    private List<Book> bookList;

    public Library() {
        this.bookList = new ArrayList<>();
    }

    //Adds books to the library arraylist
    public void addBook(int id, String title, String author, String genre) {
        if (id < 0) {
            System.out.println("Invalid ID, ID must be greater than 0!");
            return;
        }
        for (int i = 0; i< bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getID() == id) {
                System.out.println("ID already exists, please use a unique ID.");
                return;
            }
            else if (Objects.equals(book.getTitle(), title)) {
                System.out.println("Title already exists, please use a unique Title.");
                return;
            }
        }
        Book newBook = new Book(id, title, author, genre);
        bookList.add(newBook);
        System.out.println("'" + newBook.getTitle() + "' has been added!");
    }

    //Removes books from the library arraylist by ID
    public void removeBook(int id) {
        for (int i = 0; i< bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getID() == id) {
                bookList.remove(i);
                System.out.println("'" + book.getTitle() + "' Has been removed!");
                return;
            }
        }
        System.out.println("Book with ID " + id + " not found.");
    }

    //Removes books from the library arraylist by Title
    public void removeBook(String title) {
        for (int i = 0; i< bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getTitle().equals(title)) {
                bookList.remove(i);
                System.out.println("'" + book.getTitle() + "' Has been removed!");
                return;
            }
        }
        System.out.println("Book with Title " + title + " not found.");
    }

    //Checks a book In
    public void checkInBook(int id) {
        for (int i = 0; i< bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getID() == id) {
                if (book.getBookStatus().equals("In")) {
                    System.out.println("Book with ID " + id + " is already Checked In.");
                    return;
                }
                else {
                    book.setBookStatus("In");
                    book.setDueDate(null);
                    System.out.println("'" + book.getTitle() + "' Has been Checked In!");
                    return;
                }

            }
        }
        System.out.println("Book with ID " + id + " not found.");
    }
    public void checkInBook(String title) {
        for (int i = 0; i< bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getTitle().equals(title)) {
                if (book.getBookStatus().equals("In")) {
                    System.out.println("Book with Title " + title + " is already Checked In.");
                    return;
                }
                else {
                    book.setBookStatus("In");
                    book.setDueDate(null);
                    System.out.println("'" + book.getTitle() + "' Has been Checked In!");
                    return;
                }

            }
        }
        System.out.println("Book with Title " + title + " not found.");
    }

    //Checks a book Out
    public void checkOutBook(int id) {
        for (int i = 0; i< bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getID() == id) {
                if (book.getBookStatus().equals("Out")) {
                    System.out.println("Book with ID " + id + " has already been Check Out.");
                    return;
                }
                else {
                    book.setBookStatus("Out");
                    LocalDate setDate = LocalDate.now();
                    setDate = setDate.plusWeeks(4);
                    String setDateString = setDate.toString();
                    book.setDueDate(setDateString);
                    System.out.println("'" + book.getTitle() + "' Has been Checked Out!");
                    return;
                }

            }
        }
        System.out.println("Book with ID " + id + " not found.");
    }
    public void checkOutBook(String title) {
        for (int i = 0; i< bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getTitle().equals(title)) {
                if (book.getBookStatus().equals("Out")) {
                    System.out.println("Book with Title " + title + " has already been Check Out.");
                    return;
                }
                else {
                    book.setBookStatus("Out");
                    LocalDate setDate = LocalDate.now();
                    setDate = setDate.plusWeeks(4);
                    String setDateString = setDate.toString();
                    book.setDueDate(setDateString);
                    System.out.println("'" + book.getTitle() + "' Has been Checked Out!");
                    return;
                }

            }
        }
        System.out.println("Book with Title " + title + " not found.");
    }

    //lists books from the library arraylist
    public void listAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No books in the library.");
        }
        else {
            System.out.println("List of all books in the library:");
            for (int i = 0; i< bookList.size(); i++) {
                Book book = bookList.get(i);
                System.out.println(book.getID() + ", " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getBookGenre() + ", Checked " + book.getBookStatus() + ", " + book.getDueDate());
            }
        }
    }

    //saves the books in the library array list into the 'books.txt' file
    public void saveBooks(String filename) {
        bookList.sort(Comparator.comparing(Book::getID));
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of("src\\books.txt"), StandardOpenOption.TRUNCATE_EXISTING)) {
            for (int i = 0; i < bookList.size(); i++) {
                Book book = bookList.get(i);
                writer.write(book.getID() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getBookGenre());
                writer.newLine();
            }
            System.out.println("Books have been saved to file!");
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    //Copy of 'addBook' method so that when the program loads the library from the text file, program won't start with a bunch of 'book has been added' text
    public void loadBook(int id, String title, String author, String genre) {
        if (id < 0) {
            System.out.println("A book on file has an invalid ID, ID must be greater than 0!");
            return;
        }
        for (int i = 0; i< bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getID() == id) {
                System.out.println("A book on file was not added because of a non-unique ID!");
                return;
            }
            else if (Objects.equals(book.getTitle(), title)) {
                System.out.println("A book on file was not added because of a non-unique Title!");
                return;
            }
        }
        Book newBook = new Book(id, title, author, genre);
        bookList.add(newBook);
    }

    //Loads the books from the text file into the library array list
    public void loadLibraryFromFile(Library library, String fileName) {
        File file = new File("src\\" + fileName);
        try (Scanner scan = new Scanner(new File("src\\" + fileName))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String author = parts[2].trim();
                String genre = parts[3].trim();
                library.loadBook(id, title, author, genre);
            }
            System.out.println("Library successfully loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file);
        }
    }
}
