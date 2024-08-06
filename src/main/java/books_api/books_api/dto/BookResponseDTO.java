package books_api.books_api.dto;

import books_api.books_api.entity.Book;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// dto to achieve a new date format on responses
@Component
public class BookResponseDTO {

    private Integer id;
    private String name;
    private String author;
    private String releaseDate;
    private String status;
    private String category;

    public BookResponseDTO(Integer id, String name, String author, LocalDateTime releaseDate, String status, String category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.releaseDate = (releaseDate != null) ? releaseDate.format(DateTimeFormatter.ofPattern("dd, MMMM yyyy")) : null;
        this.status = status;
        this.category = category;
    }

    public BookResponseDTO() {
    }

    public BookResponseDTO createResponseFromBook(Book book){
        return new BookResponseDTO(book.getBookId(), book.getBookTitle(), book.getBookAuthor(), book.getBookReleaseDate(), book.getBookStatus(), book.getBookCategory());
    }


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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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