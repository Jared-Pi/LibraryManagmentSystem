/* Jared Pilewski, CEN-3024C, 01/27/24
 * Book
 * The book class contains methods to create and edit a book
 * The book method will be able to create books with a unique ID number, a tittle, and an author
 */
public class Book {
    private int bookID;
    private String bookTitle;
    private String bookAuthor;

    public Book(int id, String title, String author) {
        this.bookID = id;
        this.bookTitle = title;
        this.bookAuthor = author;
    }

    public int getID() {
        return bookID;
    }

    public String getTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return bookAuthor;
    }
}
