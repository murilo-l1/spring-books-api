package books_api.books_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sempre é auto-incrementada
    @Column(name = "id")
    private Integer bookId;

    @NotBlank(message = "Field required!")
    @JsonProperty("title")
    @Column(name = "title")
    private String bookTitle;

    @NotBlank(message = "Field required!")
    @JsonProperty("author")
    @Column(name = "author")
    private String bookAuthor;

    @NotNull(message = "Field is required!")
    @Min(value = 5, message = "Invalid Number!")
    @JsonProperty("num_pages")
    @Column(name = "num_pages")
    private Integer bookNumPages;


    @JsonProperty("release_date")
    //@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "release_date")
    private LocalDate bookReleaseDate;

    @NotBlank(message = "Provide a status!")
    @JsonProperty("status")
    @Column(name = "status")
    private String bookStatus;

    @NotBlank(message = "Field is required!")
    @JsonProperty("category")
    @Column(name = "category")
    private String bookCategory;

    public Book() {
    }

    public Book(String bookTitle, String bookAuthor, Integer bookNumPages, LocalDate bookReleaseDate, String bookStatus, String bookCategory) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookNumPages = bookNumPages;
        this.bookReleaseDate = bookReleaseDate;
        this.bookStatus = bookStatus;
        this.bookCategory = bookCategory;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookNumPages() {
        return bookNumPages;
    }

    public void setBookNumPages(Integer bookNumPages) {
        this.bookNumPages = bookNumPages;
    }

    public LocalDate getBookReleaseDate() {
        return bookReleaseDate;
    }

    public void setBookReleaseDate(LocalDate bookReleaseDate) {
        this.bookReleaseDate = bookReleaseDate;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }
}
