package constraints.custom;

import constraints.custom.annotations.CheckCase;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/* @@@ SIMPLE CUSTOM VALIDATOR */
public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private CaseMode caseMode;

    @Override
    public void initialize(CheckCase constraintAnnotation) {
        this.caseMode = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext) {
        if ( value == null ) {
            return true;
        }

        boolean isValid;
        if ( caseMode == CaseMode.UPPER ) {
            isValid = value.equals( value.toUpperCase() );
        }
        else {
            isValid = value.equals( value.toLowerCase() );
        }

        if ( !isValid ) {
            /* @@@ 6.1.2.1. The ConstraintValidatorContext */
            constraintContext.disableDefaultConstraintViolation();
            // Be aware that the custom message template is passed directly to the Expression Language engine.
            // Thus, you should be very careful when integrating user input in a custom message template as
            // it will be interpreted by the Expression Language engine, which is usually not the behavior
            // you want and could allow malicious users to leak sensitive data or even execute arbitrary code.

            constraintContext.buildConstraintViolationWithTemplate(
                    "{constraints.custom.annotations.CheckCase.message}"
            ).addConstraintViolation();

            // so we can use the following code to avoid this problem
            // @@@ SAFE VALIDATOR
            HibernateConstraintValidatorContext hibernateContext = constraintContext.unwrap(
                    HibernateConstraintValidatorContext.class );
            hibernateContext.disableDefaultConstraintViolation();
            hibernateContext
                    .addExpressionVariable( "validatedValue", value )
                    .buildConstraintViolationWithTemplate( "${validatedValue} is not a valid ZIP code" )
                    .addConstraintViolation();
        }

        return isValid;
    }
}
