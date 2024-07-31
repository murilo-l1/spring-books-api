package books_api.books_api.exception_handler;

public class InvalidDateFormatException extends Exception{

    private String details;

    public InvalidDateFormatException(String message) {
        super(message);
        this.details = "Provide a date in format YYYY-MM-DD to solve this issue";
    }

    public String getDetails() {
        return details;
    }
}
