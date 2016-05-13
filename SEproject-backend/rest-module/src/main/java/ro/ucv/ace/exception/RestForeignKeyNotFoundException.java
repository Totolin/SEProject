package ro.ucv.ace.exception;

/**
 * This exception is thrown from the rest layer when a searched entity's foreign key is not found.
 *
 * @author Georgian Vladutu
 */
public class RestForeignKeyNotFoundException extends RestException {

    public RestForeignKeyNotFoundException(String message) {
        super(message);
    }
}
