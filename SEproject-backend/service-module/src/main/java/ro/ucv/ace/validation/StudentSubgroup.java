package ro.ucv.ace.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation express that a student subgroup is valid.
 *
 * @author Georgian Vladutu
 */
@Constraint(validatedBy = {StudentSubgroupValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface StudentSubgroup {

    String message() default "{student.subgroup}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
