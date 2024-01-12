package be.tftic.spring.demo.api.validation.constraint;

import be.tftic.spring.demo.api.validation.validator.EvenValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {EvenValidator.class})
public @interface EvenOrOdd {

    String message() default "should be even";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean shouldBeEven();

}
