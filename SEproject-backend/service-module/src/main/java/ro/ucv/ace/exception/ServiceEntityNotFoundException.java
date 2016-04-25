package ro.ucv.ace.exception;

/**
 * This exception is thrown from the service layer when a searched entity is not found.
 *
 * @author Georgian Vladutu
 */
public class ServiceEntityNotFoundException extends ServiceException {

    public ServiceEntityNotFoundException() {
    }

    public ServiceEntityNotFoundException(String message) {
        super(message);
    }

    public ServiceEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceEntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public ServiceEntityNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
