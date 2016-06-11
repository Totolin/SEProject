package ro.ucv.ace.exception;

/**
 * This exception is thrown from the rest layer when a user password is invalid.
 *
 * @author Georgian Vladutu
 */
public class RestInvalidPasswordException extends RestException {

    public RestInvalidPasswordException(String message) {
        super(message);
    }
}
