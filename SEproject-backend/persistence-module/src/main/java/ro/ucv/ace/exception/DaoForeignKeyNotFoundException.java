package ro.ucv.ace.exception;

/**
 * This exception is thrown when a searched entity's foreign key does not exists.
 *
 * @author Georgian Vladutu
 */
public class DaoForeignKeyNotFoundException extends DaoException {

    public DaoForeignKeyNotFoundException() {
    }

    public DaoForeignKeyNotFoundException(String message) {
        super(message);
    }

    public DaoForeignKeyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoForeignKeyNotFoundException(Throwable cause) {
        super(cause);
    }

    public DaoForeignKeyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
