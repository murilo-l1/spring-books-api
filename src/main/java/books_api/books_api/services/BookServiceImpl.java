package books_api.books_api.services;

import books_api.books_api.entity.Book;
import books_api.books_api.exception_handler.BookNotFoundException;
import books_api.books_api.exception_handler.QueryFailedException;
import books_api.books_api.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository repository;

    @Autowired
    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public List<Book> queryFromCategory(String category) throws QueryFailedException{
        List<Book> queriedBooks = repository.queryBooksByCategory(category);
        if(queriedBooks.isEmpty()){
            throw new QueryFailedException("No such a list of books within: " + category);
        }
        return queriedBooks;
    }

    public List<Book> queryFromStatus(String status) throws QueryFailedException{
        List<Book> queriedBooks = repository.queryBooksByStatus(status);
        if(queriedBooks.isEmpty()){
            throw new QueryFailedException("No such a list of books with this status: " + status);
        }
        return queriedBooks;
    }

    public List<Book> queryFromAuthor(String author) throws QueryFailedException{
        List<Book> queriedBooks = repository.queryBooksByAuthor(author);
        if(queriedBooks.isEmpty()){
            throw new QueryFailedException("No such a list of books of this author: " + author);
        }
        return queriedBooks;
    }

    public Book getBookById(Integer bookId) throws BookNotFoundException {
        Optional<Book> tempBook = repository.findById(bookId);
        if(tempBook.isEmpty()){
            throw new BookNotFoundException("Couldn't find a book with this id: " + bookId);
        }
        return tempBook.get();
    }

    public Book addBook(Book newBook) {
        newBook.setBookId(null); // Ensure the ID is null so it will be auto-generated
        return repository.save(newBook);
    }

    public Book updateBookById(Book toUpdateBookContent, Integer toUpdateId) throws BookNotFoundException{
        Optional<Book> checkBook = repository.findById(toUpdateId);
        if(checkBook.isEmpty()){
            throw new BookNotFoundException("Book with id: " + toUpdateId + " couldn't be found");
        }
        toUpdateBookContent.setBookId(toUpdateId);
        return repository.save(toUpdateBookContent);
    }

    public void deleteBookById(Integer id) throws BookNotFoundException {
        if (!repository.existsById(id)) {
            throw new BookNotFoundException("A book with this id couldn't be found to delete");
        }
        repository.deleteById(id);
    }

}