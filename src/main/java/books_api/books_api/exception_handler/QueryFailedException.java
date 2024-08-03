package books_api.books_api.exception_handler;

public class QueryFailedException extends Exception{
    public QueryFailedException(String message) {
        super(message);
    }
}
