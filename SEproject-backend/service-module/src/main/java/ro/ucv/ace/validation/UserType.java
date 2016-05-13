package ro.ucv.ace.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation express that a user type is valid.
 *
 * @author Georgian Vladutu
 */
@Constraint(validatedBy = {UserTypeValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface UserType {

    String message() default "Type is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}