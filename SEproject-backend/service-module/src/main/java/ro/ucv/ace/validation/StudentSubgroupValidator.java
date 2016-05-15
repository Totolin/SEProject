package ro.ucv.ace.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is a validator for @{@link StudentSubgroup} annotation.
 *
 * @author Georgian Vladutu
 */
public class StudentSubgroupValidator implements ConstraintValidator<StudentSubgroup, String> {

    private static final String[] SUBGROUPS = {"A", "B", "C", "D"};

    @Override
    public void initialize(StudentSubgroup constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String subgroup : SUBGROUPS) {
            if (subgroup.equals(value)) {
                return true;
            }
        }

        return false;
    }
}
