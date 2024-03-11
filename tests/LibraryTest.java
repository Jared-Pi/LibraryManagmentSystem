import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    void addBook() {
        Library library = new Library();
        library.loadLibraryFromFile(library,"books.txt");
        library.listAllBooks();
        library.addBook(5, "Temp Title", "Author Test", "Testing");
        library.listAllBooks();
    }

    @Test
    void removeBookID() {
        Library library = new Library();
        library.loadLibraryFromFile(library,"books.txt");
        library.listAllBooks();
        library.removeBook(3);
        library.listAllBooks();
    }

    @Test
    void RemoveBookTitle() {
        Library library = new Library();
        library.loadLibraryFromFile(library,"books.txt");
        library.listAllBooks();
        library.removeBook("Warriors");
        library.listAllBooks();
    }

    @Test
    void checkInBookID() {
        Library library = new Library();
        library.loadLibraryFromFile(library,"books.txt");
        library.checkOutBook(3);
        library.listAllBooks();
        library.checkInBook(3);
        library.listAllBooks();
    }

    @Test
    void CheckInBookTitle() {
        Library library = new Library();
        library.loadLibraryFromFile(library,"books.txt");
        library.checkOutBook("Warriors");
        library.listAllBooks();
        library.checkInBook("Warriors");
        library.listAllBooks();
    }

    @Test
    void checkOutBookID() {
        Library library = new Library();
        library.loadLibraryFromFile(library,"books.txt");
        library.listAllBooks();
        library.checkOutBook(3);
        library.listAllBooks();
    }

    @Test
    void CheckOutBookTitle() {
        Library library = new Library();
        library.loadLibraryFromFile(library,"books.txt");
        library.listAllBooks();
        library.checkOutBook("Warriors");
        library.listAllBooks();
    }

}