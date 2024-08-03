package books_api.books_api.exception_handler;


public class BookNotFoundException extends Exception{

    public BookNotFoundException(String message) {
        super(message);
    }

}
