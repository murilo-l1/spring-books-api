package books_api.books_api.exception;


public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message) {
        super(message);
    }
}
