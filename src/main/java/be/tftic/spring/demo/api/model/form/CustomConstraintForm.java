package be.tftic.spring.demo.api.model.form;

import be.tftic.spring.demo.api.validation.constraint.EvenOrOdd;
import lombok.Data;

@Data
public class CustomConstraintForm {

    @EvenOrOdd(shouldBeEven = false)
    private int shouldBeEven;

}
