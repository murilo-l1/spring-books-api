package books_api.books_api.exception_handler;


public class BookNotFoundException extends Exception{

    private String details;

    public BookNotFoundException(String message) {
        super(message);
        this.details = "You should provide a valid string Book Title";
    }

    public String getDetails() {
        return details;
    }
}
