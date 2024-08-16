package books_api.books_api.controller.pvt;

import books_api.books_api.dto.BookDto;
import books_api.books_api.entity.Book;
import books_api.books_api.exception_handler.BookNotFoundException;
import books_api.books_api.exception_handler.QueryFailedException;
import books_api.books_api.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private final BookServiceImpl services;

    @Autowired
    public BookRestController(BookServiceImpl services) {
        this.services = services;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> retrieveAll() {
        List<BookDto> retrievedBookList = BookDto.toListDto(services.findAll());
        return new ResponseEntity<>(retrievedBookList, HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookDto> retrieveBookById(@PathVariable Integer bookId) throws BookNotFoundException {
        Book book = services.getBookById(bookId);
        BookDto response = BookDto.toDto(book);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/books/category/{bookCategory}")
    public ResponseEntity<List<BookDto>> retrieveBooksByCategory(@PathVariable String bookCategory) throws QueryFailedException{
        List<BookDto> retrievedBooks = BookDto.toListDto(services.queryFromCategory(bookCategory));
        return new ResponseEntity<>(retrievedBooks, HttpStatus.OK);
    }

    @GetMapping("/books/status/{bookStatus}")
    public ResponseEntity<List<BookDto>> retrieveBooksByStatus(@PathVariable String bookStatus) throws QueryFailedException {
        List<BookDto> retrievedBooks = BookDto.toListDto(services.queryFromStatus(bookStatus));
        return new ResponseEntity<>(retrievedBooks, HttpStatus.OK);
    }

    @GetMapping("/books/author/{bookAuthor}")
    public ResponseEntity<List<BookDto>> retrieveBooksByAuthor(@PathVariable String bookAuthor) throws QueryFailedException {
        List<BookDto> retrievedBooks = BookDto.toListDto(services.queryFromAuthor(bookAuthor));
        return new ResponseEntity<>(retrievedBooks, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> saveNewBook(@RequestBody Book newBook){
        Book tempBook = services.addBook(newBook);
        return new ResponseEntity<>(tempBook, HttpStatus.CREATED);
    }

    @PutMapping("/books/{toUpdateId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book updateBook, @PathVariable Integer toUpdateId) throws BookNotFoundException{
        Book tempBook = services.updateBookById(updateBook, toUpdateId);
        return new ResponseEntity<>(tempBook, HttpStatus.OK);
    }

    @DeleteMapping("/books/{toDeleteId}")
    public ResponseEntity<?> deleteBookById(@PathVariable Integer toDeleteId) throws BookNotFoundException{
        services.deleteBookById(toDeleteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}