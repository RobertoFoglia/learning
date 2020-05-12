package constraints.custom.annotations;

import constraints.custom.CaseMode;
import constraints.custom.CheckCaseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckCaseValidator.class)  // here it is defined the validator class
@Documented
@Repeatable(CheckCase.List.class)
public @interface CheckCase {
    /**
     * an attribute message that returns the default key for
     * creating error messages in case the constraint is violated
     */
    String message() default "{constraints.custom.annotations.CheckCase.message}"; // ValidationMessages.properties

    /**
     * an attribute groups that allows the specification of validation groups, to which this constraint belongs
     * (see Chapter 5, Grouping constraints). This must default to an empty array of type Class<?>.
     */
    Class<?>[] groups() default {};

    /**
     * an attribute payload that can be used by clients of the Jakarta Bean Validation API to assign custom
     * payload objects to a constraint. This attribute is not used by the API itself
     */
    // TODO to make an example, with dynamic payload (12.8. Dynamic payload as part of ConstraintViolation)
    Class<? extends Payload>[] payload() default {};

    CaseMode value();

    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        CheckCase[] value();
    }
}
