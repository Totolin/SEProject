package ro.ucv.ace.exception;

import ro.ucv.ace.exception.MyApplicationException;

/**
 * This is a generic exception that the rest module can throw.
 *
 * @author Georgian Vladutu
 */
public class RestException extends MyApplicationException {

    public RestException() {
        super();
    }

    public RestException(String message) {
        super(message);
    }
}
