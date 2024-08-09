package books_api.books_api.controller;

import books_api.books_api.dto.BookResponseDTO;
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
public class BookRestController {

    private final BookServiceImpl services;
    private final BookResponseDTO responseDTO;

    @Autowired
    public BookRestController(BookServiceImpl services, BookResponseDTO dto) {
        this.services = services;
        this.responseDTO = dto;
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
    public ResponseEntity<List<BookResponseDTO>> retrieveBooksByCategory(@PathVariable String bookCategory) throws QueryFailedException{
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
        Book tempBook = services.updateBookById(updateBook, toUpdateId);
        return new ResponseEntity<>(tempBook, HttpStatus.OK);
    }

    @DeleteMapping("/books/{toDeleteId}")
    public ResponseEntity<?> deleteBookById(@PathVariable Integer toDeleteId) throws BookNotFoundException{
        services.deleteBookById(toDeleteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}