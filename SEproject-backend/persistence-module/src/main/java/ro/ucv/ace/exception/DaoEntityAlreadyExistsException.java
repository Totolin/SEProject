package ro.ucv.ace.exception;

/**
 * This exception is thrown when a searched entity already exists.
 *
 * @author Georgian Vladutu
 */
public class DaoEntityAlreadyExistsException extends DaoException {

    public DaoEntityAlreadyExistsException() {
    }

    public DaoEntityAlreadyExistsException(String message) {
        super(message);
    }

    public DaoEntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoEntityAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public DaoEntityAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
