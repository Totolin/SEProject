package ro.ucv.ace.exception;

/**
 * This exception is thrown from the rest module when an entity already exists.
 *
 * @author Georgian Vladutu
 */
public class RestEntityAlreadyExistsException extends RestException {

    public RestEntityAlreadyExistsException(String message) {
        super(message);
    }
}
