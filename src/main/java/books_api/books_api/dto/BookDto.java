package books_api.books_api.dto;

import books_api.books_api.entity.Book;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {


    private final Integer id;
    private final String title;
    private final String author;
    private final Integer numPages;
    private final String releaseDate; // This remains a String to represent formatted date
    private final String status;
    private final String category;


    @Builder(builderMethodName = "bookDto")
    public BookDto(@NonNull final Integer id,
                   @NonNull @NotBlank final String title,
                   @NonNull @NotBlank final String author,
                   @NonNull final Integer numPages,
                   @NonNull final LocalDate releaseDate,
                   @NonNull @NotBlank final String status,
                   @NonNull @NotBlank final String category){

        this.id = id;
        this.title = title;
        this.author = author;
        this.numPages = numPages;
        this.releaseDate = releaseDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        this.status = status;
        this.category = category;
    }

    public static BookDto toDto(Book entity){
        return BookDto.bookDto()
                .id(entity.getBookId())
                .title(entity.getBookTitle())
                .author(entity.getBookAuthor())
                .numPages(entity.getBookNumPages())
                .releaseDate(entity.getBookReleaseDate())
                .status(entity.getBookStatus())
                .category(entity.getBookCategory())
                .build();
    }

    public static List<BookDto> toListDto(@NonNull final List<Book> entityList){
        List<BookDto> parsedList = entityList.stream().map(BookDto::toDto).toList();
        parsedList = sortDescOrder(parsedList);
        return parsedList;
    }

    private static List<BookDto> sortDescOrder(List<BookDto> list) {
        Collections.sort(list, (b1, b2) -> Integer.compare(b2.getId(), b1.getId())); // Descending order
        return list;
    }

}
