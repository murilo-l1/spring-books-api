package books_api.books_api.services;

import books_api.books_api.entity.Book;
import books_api.books_api.exception_handler.BookNotFoundException;
import books_api.books_api.exception_handler.QueryFailedException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {

    List<Book> findAll();
    List<Book> queryFromCategory(String category) throws QueryFailedException;
    List<Book> queryFromStatus(String status) throws QueryFailedException;
    List<Book> queryFromAuthor(String author) throws QueryFailedException;
    Book getBookById(Integer bookId) throws BookNotFoundException;
    Book updateBookById(Book toUpdateContent, Integer toUpdateId) throws BookNotFoundException;
    void deleteBookById(Integer bookId) throws BookNotFoundException;

}