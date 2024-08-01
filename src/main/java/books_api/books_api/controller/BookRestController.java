package books_api.books_api.controller;

import books_api.books_api.entity.Book;
import books_api.books_api.exception_handler.BookNotFoundException;
import books_api.books_api.exception_handler.EmptyDataBaseException;
import books_api.books_api.exception_handler.QueryFailedException;
import books_api.books_api.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private final BookService services;

    @Autowired
    public BookRestController(BookService services) {
        this.services = services;
    }

    @GetMapping("/books")
    public ResponseEntity<?> retrieveAll() throws EmptyDataBaseException {
        List<Book> tempList = services.findAll();
        return new ResponseEntity<>(tempList, HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> retrieveBookById(@PathVariable Integer bookId) throws BookNotFoundException {
        Book book = services.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with id " + bookId + " not found");
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/books/category/{bookCategory}")
    public ResponseEntity<?> retrieveBooksByCategory(@PathVariable String bookCategory) throws QueryFailedException {
        List<Book> retrievedBooks = services.queryFromCategory(bookCategory);
        return new ResponseEntity<>(retrievedBooks, HttpStatus.OK);
    }

    @GetMapping("/books/status/{bookStatus}")
    public ResponseEntity<?> retrieveBooksByStatus(@PathVariable String bookStatus) throws QueryFailedException {
        List<Book> retrievedBooks = services.queryFromStatus(bookStatus);
        return new ResponseEntity<>(retrievedBooks, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<?> saveNewBook(@RequestBody Book newBook){
        Book tempBook = services.addBook(newBook);
        return new ResponseEntity<>(tempBook, HttpStatus.CREATED);
    }

    @PutMapping("/books/{toUpdateId}")
    public ResponseEntity<?> updateBook(@RequestBody Book updateBook, @PathVariable Integer toUpdateId) throws BookNotFoundException{
        Book tempBook = services.updateBook(updateBook, toUpdateId);
        return new ResponseEntity<>(tempBook, HttpStatus.OK);
    }

    @DeleteMapping("/books/{toDeleteId}")
    public ResponseEntity<?> deleteBookById(@PathVariable Integer toDeleteId) throws BookNotFoundException{
        String deleteMessage = services.deleteBookById(toDeleteId);
        return new ResponseEntity<>(deleteMessage, HttpStatus.NO_CONTENT);
    }


}
