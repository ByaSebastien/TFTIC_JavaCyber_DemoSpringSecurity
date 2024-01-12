package be.tftic.spring.demo.api.validation.validator;

import be.tftic.spring.demo.api.validation.constraint.EvenOrOdd;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EvenValidator implements ConstraintValidator<EvenOrOdd, Integer> {

    private boolean shouldBeEven;

    @Override
    public void initialize(EvenOrOdd constraintAnnotation) {
        this.shouldBeEven = constraintAnnotation.shouldBeEven();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if( shouldBeEven )
            return value % 2 == 0;
        else
            return value % 2 != 0;
    }
}
