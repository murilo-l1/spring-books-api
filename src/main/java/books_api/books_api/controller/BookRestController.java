package books_api.books_api.controller;

import books_api.books_api.entity.Book;
import books_api.books_api.exception.BookNotFoundException;
import books_api.books_api.services.BookService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class BookRestController {


    private final BookService services;

    @Autowired
    public BookRestController(BookService services) {
        this.services = services;
    }

    @GetMapping("/books")
    public List<Book> retrieveAll(){
        return services.findAll();
    }

    @GetMapping("/books/{bookId}")
    public Optional<Book> retrieveBookById(@PathVariable Integer bookId) throws BookNotFoundException {
        return services.getBookById(bookId);
    }

    @PostMapping("/books")
    public Book saveNewBook(@RequestBody Book newBook) {
        return services.addBook(newBook);
    }

    @DeleteMapping("/books/{toDeleteId}")
    public String deleteBookById(@PathVariable Integer toDeleteId) throws BookNotFoundException{
        return services.deleteBookById(toDeleteId);
    }


}
