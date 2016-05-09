package ro.ucv.ace.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.error.BindingErrorInfo;
import ro.ucv.ace.error.ErrorInfo;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.populator.ErrorInfoPopulator;

/**
 * This class is a controller advice that handles all exception thrown from the REST controllers.
 *
 * @author Georgian Alexandru
 */
@ControllerAdvice(annotations = RestController.class)
public class GlobalControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @Autowired
    private ErrorInfoPopulator errorInfoPopulator;

    /**
     * This method handles all exception that extends RestEntityBindingException.
     *
     * @param exception thrown exception
     * @return a message that contains invalid fields, exception message and the http status code
     */
    @ExceptionHandler(RestEntityBindingException.class)
    public ResponseEntity<BindingErrorInfo> bindingException(RestEntityBindingException exception) {
        BindingErrorInfo bindingErrorInfo = errorInfoPopulator.populate(exception.getErrors(), exception.getMessage(),
                HttpStatus.BAD_REQUEST);

        return new ResponseEntity<BindingErrorInfo>(bindingErrorInfo, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles all exception that extends RestEntityNotFoundException.
     *
     * @param exception thrown exception
     * @return a message that contains exception message and the http status code
     */
    @ExceptionHandler(RestEntityNotFoundException.class)
    public ResponseEntity<ErrorInfo> entityNotFoundException(RestEntityNotFoundException exception) {
        LOGGER.info("Enter method");
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles all exception that extends RestInvalidPasswordException.
     *
     * @param exception thrown exception
     * @return a message that contains exception message and the http status code
     */
    @ExceptionHandler(RestInvalidPasswordException.class)
    public ResponseEntity<ErrorInfo> invalidPasswordException(RestInvalidPasswordException exception) {
        LOGGER.info("Enter method");
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());

        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.UNAUTHORIZED);
    }

    /**
     * This method handles all exception that extends RestEntityAlreadyExistsException.
     *
     * @param exception thrown exception
     * @return a message that contains exception message and the http status code
     */
    @ExceptionHandler(RestEntityAlreadyExistsException.class)
    public ResponseEntity<ErrorInfo> entityAlreadyExistsException(RestEntityAlreadyExistsException exception) {
        LOGGER.info("Enter method");
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.CONFLICT.value(), exception.getMessage());

        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.CONFLICT);
    }

    /**
     * This method handles all exception that extends RestForeignKeyNotFoundException.
     *
     * @param exception thrown exception
     * @return a message that contains exception message and the http status code
     */
    @ExceptionHandler(RestForeignKeyNotFoundException.class)
    public ResponseEntity<ErrorInfo> entityAlreadyExistsException(RestForeignKeyNotFoundException exception) {
        LOGGER.info("Enter method");
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), createRestForeignKeyMessage(exception.getMessage()));

        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    private String createRestForeignKeyMessage(String initialMessage) {
        String[] tokens = initialMessage.split(" ");
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("The ");
        stringBuilder.append(tokens[4].split("\\.")[4]);
        stringBuilder.append(" with id ");
        stringBuilder.append(tokens[7]);
        stringBuilder.append(" does not exist");

        return stringBuilder.toString();
    }

}