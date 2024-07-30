package books_api.books_api.services;

import books_api.books_api.entity.Book;
import books_api.books_api.exception.BookNotFoundException;
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


    public List<Book> findAll(){
        return repository.findAll();
    }

    public Optional<Book> getBookById(Integer bookId) throws BookNotFoundException {
        if(repository.existsById(bookId)){
            return repository.findById(bookId);
        }else{
            throw new BookNotFoundException("This book id is incorrect!");
        }
    }


    public Book addBook(Book newBook) {
        if(repository.existsById(newBook.getBookId())){
            newBook.setBookId(0);
        }
        return repository.save(newBook);
    }


    public String deleteBookById(Integer id) throws BookNotFoundException{
        if(!(repository.existsById(id))){
            throw new BookNotFoundException("Can't delete this book");
        }
        return "book with id: " + id + " deleted from db";
    }

}
