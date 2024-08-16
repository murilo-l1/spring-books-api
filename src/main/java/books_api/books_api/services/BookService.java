package books_api.books_api.services;

import books_api.books_api.entity.Book;
import books_api.books_api.exception_handler.BookNotFoundException;
import books_api.books_api.exception_handler.QueryFailedException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {

    List<Book> findAll();
    List<Book> queryFromCategory(String category);
    List<Book> queryFromStatus(String status);
    List<Book> queryFromAuthor(String author);
    Book getBookById(Integer bookId);
    Book addBook(Book book);
    Book updateBookById(Book toUpdateContent, Integer toUpdateId);
    void deleteBookById(Integer bookId) ;

}