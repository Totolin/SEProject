package ro.ucv.ace.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is a validator for @{@link UserType} annotation.
 *
 * @author Georgian Vladutu
 */
public class UserTypeValidator implements ConstraintValidator<UserType, String> {

    private static final String[] TYPES = {"STUDENT", "ADMIN", "SECRETARY", "PROFESSOR"};

    @Override
    public void initialize(UserType userType) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String type : TYPES) {
            if (type.equals(value)) {
                return true;
            }
        }

        return false;
    }
}
