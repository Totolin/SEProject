package ro.ucv.ace.exception;

/**
 * This exception is thrown from the service layer when a searched entity's foreign key is not found.
 *
 * @author Georgian Vladutu
 */
public class ServiceForeignKeyNotFoundException extends ServiceException {

    public ServiceForeignKeyNotFoundException() {
    }

    public ServiceForeignKeyNotFoundException(String message) {
        super(message);
    }

    public ServiceForeignKeyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceForeignKeyNotFoundException(Throwable cause) {
        super(cause);
    }

    public ServiceForeignKeyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
