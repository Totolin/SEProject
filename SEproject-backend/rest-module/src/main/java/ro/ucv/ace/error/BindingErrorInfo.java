package ro.ucv.ace.error;

import java.util.List;

/**
 * This class contains information about a binding error.
 *
 * @author Georgian Alexnadru
 */
public class BindingErrorInfo extends ErrorInfo {

    private List<FieldErrorInfo> fieldErrors;

    public BindingErrorInfo(int statusCode, String message, List<FieldErrorInfo> fieldErrors) {
        super(statusCode, message);
        this.fieldErrors = fieldErrors;
    }

    public List<FieldErrorInfo> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorInfo> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
