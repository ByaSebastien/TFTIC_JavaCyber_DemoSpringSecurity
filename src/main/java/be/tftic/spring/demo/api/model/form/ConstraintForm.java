package be.tftic.spring.demo.api.model.form;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


// This class exist to demonstrate the different existing non-custom @Constraint annotation
// Check all available constraint here:
// https://jakarta.ee/specifications/bean-validation/3.0/apidocs/jakarta/validation/constraints/package-summary
@Data
public class ConstraintForm {

    @AssertTrue
//    @AssertFalse
    private boolean aBoolean;

    @Min(0)
    @Max(10)
    private int number;

    @Digits(integer = 3, fraction = 3)
    private double withDigits;

    @Min(0)
    @Max(10)
    @NotNull
    private Integer numberNullable;

    @NotNull
    private Object objet;

    @NotNull
//    @Past
    @PastOrPresent
//    @Future
//    @FutureOrPresent
//    @JsonFormat(pattern = "dd/MM/yyyy") // change le format acceptable de la date
    private LocalDate time;


//    @NotNull
//    @NotEmpty
    @NotBlank
    @Size(min = 2, max = 5)
    @Pattern(regexp = "[a-zA-Z]+")
//    @Email
    private String chaine;

//    @NotNull
    @NotEmpty
    @Size(min=3, max = 3)
    private List<String> liste;

}
