package books_api.books_api.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sempre é auto-incrementada
    @Column(name="id")
    private Integer bookId;

    @Column(name="book_name")
    private String bookTitle;

    @Column(name="book_author")
    private String bookAuthor;

    @Column(name="book_num_pages")
    private Integer bookNumPages;

    @Column(name="book_release_date")
    private Date bookReleaseDate;
    // se isso não for especificado, use algo como 1/1/1


    public Book() {
    }

    public Book(String bookTitle, String bookAuthor, Integer bookNumPages, Date bookReleaseDate) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookNumPages = bookNumPages;
        this.bookReleaseDate = bookReleaseDate;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public Integer getBookNumPages() {
        return bookNumPages;
    }

    public Date getBookReleaseDate() {
        return bookReleaseDate;
    }
}