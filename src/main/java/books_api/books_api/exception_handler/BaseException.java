package books_api.books_api.exception_handler;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException{

    private final HttpStatus status;

    public BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}