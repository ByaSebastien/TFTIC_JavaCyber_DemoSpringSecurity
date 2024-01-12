package be.tftic.spring.demo.api.model.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class ValidationErrorDTO extends ErrorDTO {

    private final Map<String, Set<String>> fieldErrors;

    public ValidationErrorDTO(HttpStatus status, String message, BindingResult bindingResult) {
        super(status, message);
        this.fieldErrors = generateFieldErrorsMap(bindingResult);
    }


    private Map<String, Set<String>> generateFieldErrorsMap(BindingResult br) {
        Map<String, Set<String>> fieldErrorsMap = new HashMap<>();

        br.getFieldErrors().forEach(
                fieldError -> {
                    String fieldName = fieldError.getField();

                    if( fieldErrorsMap.containsKey( fieldName ) )
                        fieldErrorsMap.get(fieldName).add( fieldError.getDefaultMessage() );
                    else {
                        Set<String> errorsSet = new HashSet<>();
                        errorsSet.add( fieldError.getDefaultMessage() );
                        fieldErrorsMap.put( fieldName, errorsSet );
                    }
                }
        );

        return fieldErrorsMap;
    }
}
