package constraints.custom;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#validator-customconstraints
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleCustomConstraints {
    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void when_the_fields_are_null() {
        ContactDetails contactDetails = new ContactDetails();

        Set<ConstraintViolation<ContactDetails>> violations = validator.validate(contactDetails);
        Assertions.assertNotEquals(0, violations.size());
    }

    @Test
    public void when_the_fields_are_not_valid() {
        ContactDetails contactDetails = new ContactDetails("NAME", "surname");

        Set<ConstraintViolation<ContactDetails>> violations = validator.validate(contactDetails);
        Assertions.assertNotEquals(0, violations.size());
    }
}
