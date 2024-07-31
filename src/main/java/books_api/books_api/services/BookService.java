package books_api.books_api.services;

import books_api.books_api.entity.Book;
import books_api.books_api.exception_handler.BookNotFoundException;
import books_api.books_api.exception_handler.EmptyDataBaseException;
import books_api.books_api.exception_handler.InvalidDateFormatException;
import books_api.books_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll() throws EmptyDataBaseException {
        List<Book> books = repository.findAll();
        if (books.isEmpty()) {
            throw new EmptyDataBaseException("No content was found in this database");
        }
        return books;
    }

    public Book getBookById(Integer bookId) throws BookNotFoundException {
        return repository.findById(bookId).orElseThrow(() ->
                new BookNotFoundException("This book couldn't be found in our database"));
    }

    public Book addBook(Book newBook) throws InvalidDateFormatException {
        if (newBook.getBookReleaseDate() == null) {
            throw new InvalidDateFormatException("Your request failed due to the date format being passed");
        }
        newBook.setBookId(null); // Ensure the ID is null so it will be auto-generated
        return repository.save(newBook);
    }

    public Book updateBook(Book toUpdateBookContent, Integer toUpdateId) throws BookNotFoundException{
        Optional<Book> checkBook = repository.findById(toUpdateId);
        if(checkBook.isEmpty()){
            throw new BookNotFoundException("Book with id: " + toUpdateId + " couldn't be found");
        }
        toUpdateBookContent.setBookId(toUpdateId);
        return repository.save(toUpdateBookContent);
    }

    public String deleteBookById(Integer id) throws BookNotFoundException {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException("A book with this id couldn't be found to delete");
        }
        repository.deleteById(id);
        return "Book with id: " + id + " deleted from db";
    }
}
