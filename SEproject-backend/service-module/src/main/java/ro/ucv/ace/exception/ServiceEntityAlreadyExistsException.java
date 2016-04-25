package ro.ucv.ace.exception;

/**
 * This exception is thrown from the service layer when a searched entity already exists.
 *
 * @author Georgian Vladutu
 */
public class ServiceEntityAlreadyExistsException extends ServiceException {

    public ServiceEntityAlreadyExistsException() {
    }

    public ServiceEntityAlreadyExistsException(String message) {
        super(message);
    }

    public ServiceEntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceEntityAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public ServiceEntityAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
