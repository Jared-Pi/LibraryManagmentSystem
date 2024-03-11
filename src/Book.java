/* Jared Pilewski, CEN-3024C, 01/27/24
 * Book
 * The book class contains methods to create and edit a book
 * The book method will be able to create books with a unique ID number, a tittle, and an author
 */
public class Book {
    private int bookID;
    private String bookTitle;
    private String bookAuthor;
    private String bookGenre;
    private String bookStatus;
    private String dueDate;

    public Book(int id, String title, String author, String bookGenre) {
        this.bookID = id;
        this.bookTitle = title;
        this.bookAuthor = author;
        this.bookGenre = bookGenre;
        this.bookStatus = "In";
        this.dueDate = null;
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

    public String getBookGenre() {
        return bookGenre;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
