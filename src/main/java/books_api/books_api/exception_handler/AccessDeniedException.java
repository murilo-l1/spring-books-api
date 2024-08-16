package books_api.books_api.exception_handler;

import books_api.books_api.constants.ReasonConstants;
import org.springframework.http.HttpStatus;

public class AccessDeniedException extends BaseException{

    public AccessDeniedException() {
        super(ReasonConstants.ACCESS_DENIED, HttpStatus.FORBIDDEN);
    }

    public AccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }

}



