package books_api.books_api.controller;

import books_api.books_api.dto.BookResponseDTO;
import books_api.books_api.entity.Book;
import books_api.books_api.exception_handler.BookNotFoundException;
import books_api.books_api.exception_handler.QueryFailedException;
import books_api.books_api.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookRestController {

    private final BookService services;
    private BookResponseDTO responseDTO = new BookResponseDTO();

    @Autowired
    public BookRestController(BookService services) {
        this.services = services;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDTO>> retrieveAll() {
        List<BookResponseDTO> retrievedBookList = responseDTO.createResponseFromList(services.findAll());
        return new ResponseEntity<>(retrievedBookList, HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookResponseDTO> retrieveBookById(@PathVariable Integer bookId) throws BookNotFoundException {
        Book book = services.getBookById(bookId);
        BookResponseDTO response = responseDTO.createResponseFromBook(book);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/books/category/{bookCategory}")
    public ResponseEntity<List<BookResponseDTO>> retrieveBooksByCategory(@PathVariable String bookCategory) throws QueryFailedException, BookNotFoundException {
        List<BookResponseDTO> retrievedBooks = responseDTO.createResponseFromList(services.queryFromCategory(bookCategory));
        return new ResponseEntity<>(retrievedBooks, HttpStatus.OK);
    }

    @GetMapping("/books/status/{bookStatus}")
    public ResponseEntity<List<BookResponseDTO>> retrieveBooksByStatus(@PathVariable String bookStatus) throws QueryFailedException {
        List<BookResponseDTO> retrievedBooks = responseDTO.createResponseFromList(services.queryFromStatus(bookStatus));
        return new ResponseEntity<>(retrievedBooks, HttpStatus.OK);
    }

    @GetMapping("/books/author/{bookAuthor}")
    public ResponseEntity<List<BookResponseDTO>> retrieveBooksByAuthor(@PathVariable String bookAuthor) throws QueryFailedException {
        List<BookResponseDTO> retrievedBooks = responseDTO.createResponseFromList(services.queryFromAuthor(bookAuthor));
        return new ResponseEntity<>(retrievedBooks, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> saveNewBook(@RequestBody Book newBook){
        Book tempBook = services.addBook(newBook);
        return new ResponseEntity<>(tempBook, HttpStatus.CREATED);
    }

    @PutMapping("/books/{toUpdateId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book updateBook, @PathVariable Integer toUpdateId) throws BookNotFoundException{
        Book tempBook = services.updateBook(updateBook, toUpdateId);
        return new ResponseEntity<>(tempBook, HttpStatus.OK);
    }

    @DeleteMapping("/books/{toDeleteId}")
    public ResponseEntity<String> deleteBookById(@PathVariable Integer toDeleteId) throws BookNotFoundException{
        String deleteMessage = services.deleteBookById(toDeleteId);
        return new ResponseEntity<>(deleteMessage, HttpStatus.NO_CONTENT);
    }



}