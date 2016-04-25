package ro.ucv.ace.exception;

/**
 * This exception is thrown from the rest module when an entity is not found.
 *
 * @author Georgian Vladutu
 */
public class RestEntityNotFoundException extends RestException {

    public RestEntityNotFoundException(String message) {
        super(message);
    }
}
