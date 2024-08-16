package books_api.books_api.exception_handler;


import books_api.books_api.constants.ReasonConstants;

public class BookNotFoundException extends NotFoundException{

    public BookNotFoundException() {
        super(ReasonConstants.BOOK_NOT_FOUND);
    }
}