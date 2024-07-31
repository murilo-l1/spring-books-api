package books_api.books_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sempre é auto-incrementada
    @Column(name = "id")
    private Integer bookId;

    @JsonProperty("title")
    @Column(name = "title")
    private String bookTitle;

    @JsonProperty("author")
    @Column(name = "author")
    private String bookAuthor;

    @JsonProperty("num_pages")
    @Column(name = "num_pages")
    private Integer bookNumPages;

    @JsonProperty("release_date")
    @Column(name = "release_date")
    private Date bookReleaseDate;

    @JsonProperty("status")
    @Column(name = "status")
    private String bookStatus;

    @JsonProperty("category")
    @Column(name = "category")
    private String bookCategory;

    public Book() {
    }

    public Book(String bookTitle, String bookAuthor, Integer bookNumPages, Date bookReleaseDate, String bookStatus, String bookCategory) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookNumPages = bookNumPages;
        this.bookReleaseDate = bookReleaseDate;
        this.bookStatus = bookStatus;
        this.bookCategory = bookCategory;
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

    public String getBookStatus() {
        return bookStatus;
    }

    public String getBookCategory() {
        return bookCategory;
    }
}
