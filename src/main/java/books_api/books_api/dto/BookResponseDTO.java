package books_api.books_api.dto;

import books_api.books_api.entity.Book;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookResponseDTO {

    private Integer id;
    private String title;
    private String author;
    private Integer numPages;
    private String releaseDate; // This remains a String to represent formatted date
    private String status;
    private String category;

    // Constructor to initialize the DTO with formatted release date
    public BookResponseDTO(Integer id, String title, String author, Integer numPages, LocalDate releaseDate, String status, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numPages = numPages;
        this.releaseDate = (releaseDate != null) ? releaseDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")) : null;
        this.status = status;
        this.category = category;
    }

    public BookResponseDTO() {
    }

    // Method to create a DTO from a Book entity
    public BookResponseDTO createResponseFromBook(Book book) {
        return new BookResponseDTO(
                book.getBookId(),
                book.getBookTitle(),
                book.getBookAuthor(),
                book.getBookNumPages(),
                book.getBookReleaseDate(), // Pass LocalDate to the constructor
                book.getBookStatus(),
                book.getBookCategory()
        );
    }

    // Method to create a list of DTOs from a list of Book entities
    public List<BookResponseDTO> createResponseFromList(List<Book> tempList) {
        List<BookResponseDTO> dtoList = new ArrayList<>();

        if (tempList != null) {
            for (Book tempBook : tempList) {
                BookResponseDTO parsedBook = createResponseFromBook(tempBook);
                dtoList.add(parsedBook);
            }
        }

        return dtoList;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
