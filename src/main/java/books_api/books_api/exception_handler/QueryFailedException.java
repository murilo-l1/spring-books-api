package books_api.books_api.exception_handler;

import books_api.books_api.constants.ReasonConstants;

public class QueryFailedException extends NotFoundException{

    public QueryFailedException() {
        super(ReasonConstants.QUERY_FAILED);
    }

}